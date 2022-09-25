package com.example.modules.websocket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MegUser {

    private String username;

    private String headImg;

    private String text;//第一句话
}
