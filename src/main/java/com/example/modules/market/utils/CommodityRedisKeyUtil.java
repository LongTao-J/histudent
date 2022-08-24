package com.example.modules.market.utils;

public class CommodityRedisKeyUtil {
    //保存用户点赞数据的key

    public static final String MAP_KEY_USER_LIKED = "MAP_LT_USER_LIKED";
    //保存用户被点赞数量的key
    public static final String MAP_KEY_USER_LIKED_COUNT = "MAP_LT_USER_LIKED_COUNT";

    /**
     * 拼接被点赞的用户id和点赞的人的id作为key。格式 222222::333333
     * @param UserId 被点赞的人id
     * @param commodityId 点赞的人的id
     * @return
     */
    public static String getLikedKey(String UserId, String commodityId){
        StringBuilder builder = new StringBuilder();
        builder.append(UserId);
        builder.append("::");
        builder.append(commodityId);
        return builder.toString();
    }
}
