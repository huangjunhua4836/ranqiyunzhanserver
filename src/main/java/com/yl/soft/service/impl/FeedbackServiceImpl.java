package com.yl.soft.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yl.soft.mapper.FeedbackMapper;
import com.yl.soft.po.Feedback;
import com.yl.soft.service.FeedbackService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 反馈 服务实现类
 * </p>
 *
 * @author Chanhs
 * @since 2020-09-09
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {

}
