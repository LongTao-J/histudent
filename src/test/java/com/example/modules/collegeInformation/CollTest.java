package com.example.modules.collegeInformation;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.modules.collegeInformation.mapper.DepartmentsMapper;
import com.example.modules.collegeInformation.mapper.ProfessionMapper;
import com.example.modules.collegeInformation.mapper.SchoolMapper;
import com.example.modules.collegeInformation.pojo.Departments;
import com.example.modules.collegeInformation.pojo.Profession;
import com.example.modules.collegeInformation.pojo.School;
import org.junit.jupiter.api.Test;
import org.python.jline.internal.InputStreamReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@SpringBootTest
public class CollTest {
    @Autowired
    SchoolMapper schoolMapper;

    @Autowired
    DepartmentsMapper departmentsMapper;

    @Autowired
    ProfessionMapper professionMapper;

    public String sch = "name";
    public String dep = "dep2";
    public String pro = "pro3";

    @Test
    void add(){
        School school = new School("name3");
        QueryWrapper<School> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "name5");
        School school3 = schoolMapper.selectOne(queryWrapper);
        int cnt = Math.toIntExact(schoolMapper.selectCount(queryWrapper));
        System.out.println("--------------");
        System.out.println(school3);
        System.out.println("--------------");
//        schoolMapper.insert(school);
        System.out.println("-------------------"+school.getId()+"--------------");
//        schoolService.save(school);
        School school2 = new School("37d0fdd1f46e249cf2cd1ca426e4ec3f", "name2");
//        schoolMapper.updateById(school2);
//        schoolMapper.selectList();
    }

    @Test
    void addAll() {
        Profession profession = new Profession(pro);

        QueryWrapper<School> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", sch);
        School school = schoolMapper.selectOne(queryWrapper);
        QueryWrapper<Departments> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("name", dep);
        Departments departments = departmentsMapper.selectOne(queryWrapper2);

        if(school == null){
            school = new School(sch);
            schoolMapper.insert(school);
        }

        if(departments == null) {
            departments = new Departments(dep);
            departments.setSch_id(school.getId());
            departmentsMapper.insert(departments);
        }

        profession.setDep_id(departments.getId());
        professionMapper.insert(profession);
    }

    @Test
    void delete(){
        String id = "834edabcaad229cea96a58e79c5b53ab";
        QueryWrapper<Departments> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sch_id", id);
        List<Departments> departments = departmentsMapper.selectList(queryWrapper);
        for (Departments departments1 : departments) {
            QueryWrapper<Profession> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("dep_id", departments1.getId());
            List<Profession> professions = professionMapper.selectList(queryWrapper2);
            for (Profession profession1 : professions) {
                professionMapper.deleteById(profession1.getId());
            }
            departmentsMapper.deleteById(departments1.getId());
        }
        schoolMapper.deleteById(id);
    }

    @Test
    void idAndName(){
        Profession profession =  professionMapper.queryByIdAndName("f6375062255ca63b47e0cefb8c4913ef", "pro1");
        System.out.println("==================");
        System.out.println(profession);
        System.out.println("==================");
    }

    @Test
    void runPython() {
        Process proc;
        try {
        	/*
			附加：
			String[] args1=new String[]{"/home/huan/anaconda2/bin/python","/home/huan/myfile/pythonfile/helloword.py"};
            Process pr=Runtime.getRuntime().exec(args1);
			String数组里的那一行很重要
			首先一定要设置好你所使用的python的位置，切记不要直接使用python，因为系统会默认使用自带的python，所以一定要设置好你所使用的python的位置，否则可能会出现意想不到的问题（比如说我使用的是anaconda中的python，而ubuntu系统会默认调用自带的python，而我自带的python中并没有numpy库，所以会造成相应的代码不会执行的问题，所以设置好python的位置是很重要的）。还有就是要设置好py文件的位置，使用绝对路径。在这里插入代码片

       还有就是可以看出，此方法可以满足我们python代码中调用第三方库的情况，简单实用。
			*/
            // 课表时间设置year: 学年 例:2019 cnt: 学期号 1或2或3：第一学期 4或8或12：第二学期
            String userid = "2332";
            String year = "2019";
            String cnt = "1";
            String[] args1=new String[]{"D:\\install\\conda_data\\envs\\py36\\python.exe","D:\\pycode\\sql_cource_avg.py", userid, year, cnt};
            Process pr=Runtime.getRuntime().exec(args1);
//            proc = Runtime.getRuntime().exec("python ./plus.py");
            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            pr.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
