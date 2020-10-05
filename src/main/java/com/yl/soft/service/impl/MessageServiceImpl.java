package com.yl.soft.service.impl;

import com.yl.soft.po.Message;
import com.yl.soft.mapper.MessageMapper;
import com.yl.soft.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消息 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-10-05
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}
