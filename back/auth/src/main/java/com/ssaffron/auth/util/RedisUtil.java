package com.ssaffron.auth.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
@Slf4j
public class RedisUtil {
    private final RedisTemplate<String, String> redisTemplate;

    public Map<Object, Object> getData(String key){
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();

        return hashOperations.entries(key);
    }

    public void setDataExpire(String key,String memberEmail, String role, long duration){
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(key, "memberEmail", memberEmail);
        hashOperations.put(key, "memberRole", role);
        redisTemplate.expire(key,duration, TimeUnit.MILLISECONDS);
    }

    // 로그아웃
    public void deleteData(String key){
        redisTemplate.delete(key);
    }

}
