package com.ssaffron.auth.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class RedisUtil {

    private final StringRedisTemplate stringRedisTemplate;

    public Map<Object, Object> getData(String key){
        HashOperations<String, Object, Object> hashOperations = stringRedisTemplate.opsForHash();

        return hashOperations.entries(key);
    }

//    public void setData(String value, String key){
//        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
//        valueOperations.set(key,value);
//    }

    public void setDataExpire(String key,String value, String role, long duration){
        HashOperations<String, Object, Object> hashOperations = stringRedisTemplate.opsForHash();
        Duration expireDuration = Duration.ofSeconds(duration);
//        hashOperations.put(key,value, role, expireDuration);
        hashOperations.put(key, value, expireDuration);
    }

    // 로그아웃
    public void deleteData(String key){
        stringRedisTemplate.delete(key);
    }

}
