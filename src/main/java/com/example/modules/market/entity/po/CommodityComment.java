package com.example.modules.market.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityComment implements Serializable {

    private static final long serialVersionUID = 1L;

    private String comment;

    private String commodityId;

    private String userId;
}
