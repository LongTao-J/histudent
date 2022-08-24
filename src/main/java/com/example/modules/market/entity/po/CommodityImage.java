package com.example.modules.market.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityImage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String commodityImg;

    private String commodityId;
}
