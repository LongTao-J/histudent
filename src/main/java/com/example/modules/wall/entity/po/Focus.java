package com.example.modules.wall.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 关注表
 * @author mushan
 * @since 2022-08-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Focus implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关注数据编号
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 关注用户编号
     */
    private String userIdFrom;

    /**
     * 被关注用户编号
     */
    private String userIdTo;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;


}
