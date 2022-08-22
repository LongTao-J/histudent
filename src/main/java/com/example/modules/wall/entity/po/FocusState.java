package com.example.modules.wall.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 关注状态表
 * </p>
 *
 * @author mushan
 * @since 2022-08-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FocusState implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @TableId(value = "user_id", type = IdType.ASSIGN_UUID)
    private String userId;

    /**
     * 粉丝数量
     */
    private Integer fansCount;

    /**
     * 关注数量
     */
    private Integer focusCount;


}
