package com.example.modules.market.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WritCommentDTO {
    private String comment;
    private String commodityId;
}
