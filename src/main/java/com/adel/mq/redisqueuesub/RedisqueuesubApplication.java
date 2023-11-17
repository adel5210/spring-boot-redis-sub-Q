package com.adel.mq.redisqueuesub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.adel.mq.redisqueuesub.model.Cevent;
import com.adel.mq.redisqueuesub.service.SubscriberService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class RedisqueuesubApplication {

	private final LettuceConnectionFactory connectionFactory;

	public static void main(String[] args) {
		SpringApplication.run(RedisqueuesubApplication.class, args);
	}

	@Bean
	public RedisMessageListenerContainer redisContainer(){
		final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(new MessageListenerAdapter(new SubscriberService()), new ChannelTopic("topic0"));
		return container;
	}
}
