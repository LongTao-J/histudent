package com.example.modules.market.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityCommentVVo {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    private String content;

    private String commodityId;

    private String userId;
}
