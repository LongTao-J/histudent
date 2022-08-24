package com.example.modules.epidemic.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author mushan
 * @since 2022-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Nucleic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生核酸数据编号
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 学校编号
     */
    private String schId;

    /**
     * 学号
     */
    private String stuNum;

    /**
     * 核酸状态
     */
    private Boolean state;

    /**
     * 核酸单位
     */
    private String unit;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;


}
