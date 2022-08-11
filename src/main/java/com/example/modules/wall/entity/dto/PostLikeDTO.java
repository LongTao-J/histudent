package com.example.modules.wall.entity.dto;

import com.example.modules.wall.enums.LikedStatusEnum;
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
