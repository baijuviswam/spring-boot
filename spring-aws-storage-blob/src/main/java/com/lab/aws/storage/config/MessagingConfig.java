package com.lab.aws.storage.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.PayloadMethodArgumentResolver;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sqs.AmazonSQSAsync;

import io.awspring.cloud.messaging.config.QueueMessageHandlerFactory;
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;

@Configuration
public class MessagingConfig {

    @Bean
    QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync) {
        return new QueueMessagingTemplate(amazonSQSAsync);
    }

    @Bean
    NotificationMessagingTemplate notificationMessagingTemplate(AmazonSNS amazonSNS) {
        return new NotificationMessagingTemplate(amazonSNS);
    }

    @Bean
    QueueMessageHandlerFactory queueMessageHandlerFactory() {
        QueueMessageHandlerFactory factory = new QueueMessageHandlerFactory();
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setStrictContentTypeMatch(false);
        factory.setArgumentResolvers(Collections.singletonList(new PayloadMethodArgumentResolver(messageConverter)));
        return factory;
    }
}