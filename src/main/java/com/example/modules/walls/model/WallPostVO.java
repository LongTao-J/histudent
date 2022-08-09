package com.example.modules.walls.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.NonLeaked;

import java.util.List;

import java.util.Date;

/**
 * @author mushan
 * @date 9/8/2022
 * @apiNote
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WallPostVO {
    private WallPost wallPost;
    private List<String> images;
}
