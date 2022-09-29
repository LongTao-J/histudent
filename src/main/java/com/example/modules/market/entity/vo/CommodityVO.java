package com.example.modules.market.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityVO {

    private String id;

    private String nickname;

    private String headaddress;

    private BigDecimal price;

    private String introduce;

    //想要的个数
    private Integer want;

    //是否想要
    private Integer isWant;// 0 不想要 ， 1想要

    //上传的第一张照片
    private String TotalImage;

    //所有照片
    private List<String> allImg;

    private Date gmtCreate;

    //评论的个数
    private Integer CommentCount;

    // 是否推荐
    private Boolean isRec;

    //收藏的个数
    private Integer collectionCount;

    private String from="market";

    //当前用户是否收藏
    private Boolean isCollection;// 1 为收藏，0 为没有收藏

}
