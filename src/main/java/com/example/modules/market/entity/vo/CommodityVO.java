package com.example.modules.market.entity.vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityVO {

    private String nickname;

    private String headaddress;

    private String title;

    private BigDecimal price;

    private Integer count;

    private String introduce;

    private Integer want;

    private Date gmtModified;

}
