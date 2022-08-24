package com.example.modules.market.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.modules.market.entity.po.CommodityComment;
import com.example.modules.market.mapper.CommodityCommentMapper;
import com.example.modules.market.service.CommodityCommentService;
import org.springframework.stereotype.Service;

@Service
public class CommodityCommentServiceImpl extends ServiceImpl<CommodityCommentMapper, CommodityComment> implements CommodityCommentService {
}
