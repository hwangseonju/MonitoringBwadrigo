package com.ssaffron.auth.util;

import com.ssaffron.auth.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
@Slf4j
public class RedisUtil {

    private final StringRedisTemplate stringRedisTemplate;
    private final RedisTemplate<String, String> redisTemplate;

    public Map<Object, Object> getData(String key){
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();

        return hashOperations.entries(key);
    }

//    public String getData(String key){
//        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
//        return valueOperations.get(key);
//    }


//    public void setData(String value, String key){
//        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
//        valueOperations.set(key,value);
//    }

    public void setDataExpire(String key,String memberEmail, String role, long duration){
        HashOperations<String, Object, Object> hashOperations = stringRedisTemplate.opsForHash();
        Duration expireDuration = Duration.ofSeconds(duration);
        hashOperations.put(key, "memberEmail", memberEmail);
        hashOperations.put(key, "memberRole", role);
        hashOperations.put(key, "expireDuration", expireDuration);


    }


    public void setDataExpire(String key, MemberDto memberDto, long duration){
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        Duration expireDuration = Duration.ofSeconds(duration);
        valueOperations.set(key, String.valueOf(memberDto), expireDuration);

        log.info("log: {}", valueOperations.get(key));

    }

    // 로그아웃
    public void deleteData(String key){
        redisTemplate.delete(key);
    }

}
