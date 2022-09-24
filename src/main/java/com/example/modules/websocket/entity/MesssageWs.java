package com.example.modules.websocket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MesssageWs {

    private String username;
    private String tousername;
    private String text;
}
