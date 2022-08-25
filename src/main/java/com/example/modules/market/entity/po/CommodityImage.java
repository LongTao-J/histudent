package com.example.modules.market.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityImage  {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    private String commodityImg;

    private String commodityId;
}
