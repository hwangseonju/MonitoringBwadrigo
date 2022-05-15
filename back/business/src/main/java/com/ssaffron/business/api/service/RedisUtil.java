package com.ssaffron.business.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Service
public class RedisUtil {

    private final RedisTemplate<String, String> redisTemplate;

    public Map<Object, Object> getData(String key){
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();

        return hashOperations.entries(key);
    }

    public void deleteRefreshToken(String key) {
        redisTemplate.delete(key);

    }



}
