package com.example.modules.bbs.entity.dto;

import com.example.modules.bbs.enums.LikedStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostLikeDTO {
    String userId;
    String postId;
    Integer status = LikedStatusEnum.UNLIKE.getCode();
}
