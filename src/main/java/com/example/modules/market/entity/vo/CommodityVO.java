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

}
