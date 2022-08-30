package com.example.modules.market.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityDTO {

    private BigDecimal price;

    private String introduce;

}
