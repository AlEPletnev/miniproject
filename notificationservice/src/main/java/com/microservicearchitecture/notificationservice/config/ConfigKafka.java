package com.microservicearchitecture.notificationservice.config;

import com.microservicearchitecture.notificationservice.deserializer.CustomJsonDeserializer;
import com.microservicearchitecture.notificationservice.dto.OrderCreated;
import com.microservicearchitecture.notificationservice.dto.OrderRequest1;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConfigKafka {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ConsumerFactory<String, OrderRequest1> orderConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "order-group");

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),  // Для ключа
                new CustomJsonDeserializer<>(OrderRequest1.class)  // Для значения
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderRequest1> orderKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, OrderRequest1> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(orderConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, OrderCreated> orderCreatedConsumerFactory(){
        Map<String,Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"order-group");
        return new DefaultKafkaConsumerFactory<>(props,new StringDeserializer(),new CustomJsonDeserializer<>(OrderCreated.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderCreated> orderCreatedKafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, OrderCreated> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(orderCreatedConsumerFactory());
        return factory;
    }
}
