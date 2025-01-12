package com.pulse.persist.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {


    RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void set(String key, Object value){
        redisTemplate.opsForValue().set(key, value);
    }

    public void delete(String key){
        redisTemplate.delete(key);
    }

    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }
}
