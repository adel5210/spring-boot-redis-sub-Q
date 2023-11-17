package com.adel.mq.redisqueuesub.service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriberService implements  MessageListener {

    @Override
    public void onMessage(Message message, @Nullable byte[] pattern) {
        System.out.println(message.toString());
    }
}
