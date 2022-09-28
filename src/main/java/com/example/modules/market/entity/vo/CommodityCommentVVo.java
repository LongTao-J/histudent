package com.example.modules.market.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityCommentVVo {

    private String id;

    private String content;

    private String commodityId;

    private String userId;

    private Date gmtCreate;

    private Date gmtModified;
}
