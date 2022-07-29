# -*- coding=utf-8 -*-
import requests
import time
from lxml import etree
from hex2b64 import HB64
import RSAJS
import pymysql
import sys

class Longin():

    def __init__(self,user,password,login_url,login_KeyUrl):
        # 初始化程序数据
        self.Username = user
        self.Password = password
        nowTime = lambda:str(round(time.time()*1000))
        self.now_time = nowTime()

        self.login_url = login_url
        self.login_Key = login_KeyUrl

    def Get_indexHtml(self):
        # 获取教务系统网站
        self.session = requests.Session()
        self.session.headers.update({
            "User-Agent": "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36",
            "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
            "Accept-Encoding": "gzip, deflate",
            "Accept-Language": "zh-CN,zh;q=0.9",
            "Cache-Control": "max-age=0",
            "Connection": "keep-alive",
            "Referer": self.login_url+ self.now_time,
            "Upgrade-Insecure-Requests": "1"
        })
        self.response = self.session.get(self.login_url+ self.now_time).content.decode("utf-8")

    def Get_csrftoken(self):
        # 获取到csrftoken
        lxml = etree.HTML(self.response)
        self.csrftoken = lxml.xpath("//input[@id='csrftoken']/@value")[0]

    def Get_PublicKey(self):
        # 获取到加密公钥
        key_html = self.session.get(self.login_Key + self.now_time)
        key_data = key_html.json()
        self.modulus = key_data["modulus"]
        self.exponent = key_data["exponent"]

    def Get_RSA_Password(self):
        # 生成RSA加密密码
        rsaKey = RSAJS.RSAKey()
        rsaKey.setPublic(HB64().b642hex(self.modulus),HB64().b642hex(self.exponent))
        self.enPassword = HB64().hex2b64(rsaKey.encrypt(self.Password))

    def Longin_Home(self):
        # 登录信息门户,成功返回session对象
        self.Get_indexHtml()
        self.Get_csrftoken()
        self.Get_PublicKey()
        self.Get_RSA_Password()
        login_data = [("csrftoken", self.csrftoken),("yhm", self.Username),("mm", self.enPassword),("mm", self.enPassword)]
        login_html = self.session.post(self.login_url + self.now_time,data=login_data)
        # 当提交的表单是正确的，url会跳转到主页，所以此处根据url有没有跳转来判断是否登录成功
        if login_html.url.find("login_slogin.html") == -1: # -1没找到，说明已经跳转到主页
            print("登录成功")
            return self.session
        else:
            print("用户名或密码不正确，登录失败")
            exit()

class TimeTable():
    def __init__(self, session, table_url, connect, year, cnt, user_id):
        # 课表时间设置xnm: 学年 例:2019 xqm 1或2或3：第一学期 4或8或12：第二学期
        data = {"xnm":year,"xqm":cnt}
        table_info = session.post(table_url,data = data).json()
        cur = connect.cursor()
        for each in table_info["kbList"]:
            # plt = r'{} | {:<8s} | {:<13s} | {:<15s} | {:<22s}'
            # print(plt.format(each["xqjmc"], each["jc"], each["cdmc"], each["zcd"], each["kcmc"])) 
            tmp = "\'" + str(each["kch_id"] + str(user_id)) + str(each["khfsmc"]) + str(each["jcs"]) + str(each["xqj"]) + "\'" + "," + "\'" + str(user_id) + "\'" +  "," + "\'" +  str(each["kcmc"]) + "\'" +  "," + "\'" +  \
                  str(each["kclb"]) + "\'" +  "," + "\'" +  str(each["khfsmc"]) + "\'" +  "," + "\'" +  str(each["cdmc"]) + "\'" +  "," + "\'" +  \
                  str(each["zcd"]) + "\'" +  "," + "\'" +  str(each["jcs"]) + "\'" +  "," + "\'" +  str(each["xqj"])+ "\'" 
            try:
                insert_sqli = "insert into user_course_scheduling values("+ tmp +");"
                cur.execute(insert_sqli)
                # for obj in zip(each["kch_id"]+user_id, user_id,\
                #     each["kcmc"], each["kclb"], each["khfsmc"],\
                #     each["cdmc"], each["zcd"], each["jcs"], each["xqj"]):
                #   cur.execute(cur.mogrify(insert_sqli, obj))
            except Exception as e:
                print("插入数据失败:", e)
            else:
                # 如果是插入数据， 一定要提交数据， 不然数据库中找不到要插入的数据;
                connect.commit()
        cur.close()

if __name__ == "__main__":
	# 登录主页url
    login_url = "http://121.251.136.136/jwglxt/xtgl/login_slogin.html?language=zh_CN&_t="
    # 请求PublicKey的URL
    login_KeyUrl = "http://121.251.136.136/jwglxt/xtgl/login_getPublicKey.html?time="
	# 登录后的课表URL
    table_url = "http://121.251.136.136/jwglxt/kbcx/xskbcx_cxXsKb.html?gnmkdm=N2151"

    user_id = sys.argv[1]
    year = sys.argv[2]
    cnt = sys.argv[3]
    userId = sys.argv[4]
    password = sys.argv[5]

    zspt = Longin(userId,password,login_url,login_KeyUrl)

    connect = pymysql.connect(host='121.41.227.139',   # 本地数据库
                          port=7004,
                          user='root',
                          password='meiyoumima123',
                          db='hi_student',
                          charset='utf8') #服务器名,账户,密码，数据库名称
    response_cookies = zspt.Longin_Home()
    
    TimeTable(response_cookies,table_url,connect, year, cnt, user_id)
    connect.close()
