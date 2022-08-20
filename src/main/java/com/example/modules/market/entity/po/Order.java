package com.example.modules.market.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    private String schId;

    private String userId;

    private String commId;

    private Integer count;

    private BigDecimal totalPrice;
}
