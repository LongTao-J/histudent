package com.example.modules.wall.utils;

public class BBSRedisKeyUtils {
    //保存用户点赞数据的key
    public static final String HASH_KEY_USER_LIKE = "HASH_USER_LIKE";
    //保存帖子被点赞数量的key
    public static final String HASH_KEY_POST_LIKED_COUNT = "HASH_POST_LIKED_COUNT";

    /**
     * 拼接点赞的用户id和被点赞的帖子id作为key。格式 222222::333333
     * @param userId
     * @param postId
     * @return
     */
    public static String getLikeKey(String userId, String postId){
        StringBuilder builder = new StringBuilder();
        builder.append(userId);
        builder.append("::");
        builder.append(postId);
        return builder.toString();
    }

    public static String getPostFileListKey(String postId){
        StringBuilder builder = new StringBuilder();
        builder.append("LIST_POST_FILE");
        builder.append("::");
        builder.append(postId);
        return builder.toString();
    }

    public static String getReleasePostFileListKey(String userId){
        StringBuilder builder = new StringBuilder();
        builder.append("LIST_RELEASE_POST_FILE_CACHE");
        builder.append("::");
        builder.append(userId);
        return builder.toString();
    }
}
