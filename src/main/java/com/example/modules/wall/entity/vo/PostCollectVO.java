package com.example.modules.wall.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author mushan
 * @date 20/9/2022
 * @apiNote
 */
@Getter
@Setter
public class PostCollectVO {
    private String nickname;
    private String title;
    private String content;
    private String image;
    private Date gmtCreate;
}
