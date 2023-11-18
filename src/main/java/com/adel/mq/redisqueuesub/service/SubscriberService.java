package com.adel.mq.redisqueuesub.service;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.adel.mq.redisqueuesub.model.Cevent;
import com.adel.mq.redisqueuesub.repository.CeventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriberService implements CommandLineRunner, MessageListener {

    private final CeventRepository ceventRepository;

    @Override
    public void run(String... args) throws Exception {
        ceventRepository.findAll()
                .forEach(e -> {
                    processData(e.toString());
                    ceventRepository.delete(e);
                });
    }

    @Override
    public void onMessage(Message message, @Nullable byte[] pattern) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            final Cevent cevent = mapper.readValue(message.toString(), Cevent.class);

            processData(cevent.toString());
            ceventRepository.findById(cevent.getId())
                    .ifPresent(c -> ceventRepository.delete(c));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    private void processData(final String data) {
        System.out.println(data);
    }

}
