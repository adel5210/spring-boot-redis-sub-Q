package com.adel.mq.redisqueuesub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import com.adel.mq.redisqueuesub.repository.CeventRepository;
import com.adel.mq.redisqueuesub.service.SubscriberService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class RedisqueuesubApplication {

	private final LettuceConnectionFactory connectionFactory;
	private final CeventRepository ceventRepository;

	public static void main(String[] args) {
		SpringApplication.run(RedisqueuesubApplication.class, args);
	}

	@Bean
	public RedisMessageListenerContainer redisContainer(){
		final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(new MessageListenerAdapter(new SubscriberService(ceventRepository)), new ChannelTopic("topic0"));
		return container;
	}
}
