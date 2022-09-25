package com.example.modules.websocket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MesssageWs {

    private String usernameHeader;
    private String tousernameHeader;
    private String text;
    private int flage;// 1为右边，0为左边
}
