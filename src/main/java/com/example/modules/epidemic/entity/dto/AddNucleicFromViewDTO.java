package com.example.modules.epidemic.entity.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author mushan
 * @date 20/8/2022
 * @apiNote
 */
@Getter
@Setter
@ToString
public class AddNucleicFromViewDTO {
    String schId;
    String stuNum;
    Boolean state;
}
