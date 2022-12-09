package com.example.demo.config;

import com.example.demo.model.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
// Follow https://www.baeldung.com/spring-data-redis-properties
public class RedisConfiguration {

    private final RedisConnectionFactory connectionFactory;

    public RedisConfiguration(RedisConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Bean
    public RedisTemplate<Long, Student> redisTemplate() {
        RedisTemplate<Long, Student> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        return template;
    }
}
