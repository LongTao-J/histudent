package com.example.modules.wall.repository;

/**
 * @author mushan
 * @date 22/8/2022
 * @apiNote
 */
public interface FocusRepository {
    void focus(String userIdFrom, String userIdTo);

    void unfocus(String userIdFrom, String userIdTo);
}
