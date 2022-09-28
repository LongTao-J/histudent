package com.example.modules.websocket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.NamedEntityGraph;

@Data
@AllArgsConstructor
@NamedEntityGraph
public class Msg {

    private String from;

    private String text;

    private String to;
}
