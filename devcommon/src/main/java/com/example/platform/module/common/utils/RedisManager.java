package com.example.platform.module.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * Redis连接工具
 *
 */
public class RedisManager {

    private final Logger logger = LoggerFactory.getLogger(RedisManager.class);

    private String host = "10.17.2.109";
    private int port = 8890;
    private int timeout = 0;  // Timeout for Jedis try to connect to redis server
    private String password = "mmeIEA%987ndAPe";

    private JedisPool jedisPool = null;

    public RedisManager(String host, int port, String password, int timeout) {
        this.host = host;
        this.port = port;
        this.password = password;
        this.timeout = timeout;
        init();
    }

    /**
     * Initializing jedis pool to connect to Jedis.
     */
    public void init() {
        logger.info("Start init jedis pool, host is {}, port is {} ", host, port, password);
        if (password != null && !"".equals(password)) {
            jedisPool = new JedisPool(new JedisPoolConfig(), host, port, timeout, password);
        } else if (timeout != 0) {
            jedisPool = new JedisPool(new JedisPoolConfig(), host, port, timeout);
        } else {
            jedisPool = new JedisPool(new JedisPoolConfig(), host, port);
        }
    }

    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * Get value from Redis
     *
     * @param key
     * @return
     */
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();

        try {
            return jedis.get(key);
        } finally {
            jedis.close();
        }
    }

    /**
     * Set value into Redis with default time to live in seconds.
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        Jedis jedis = jedisPool.getResource();

        try {
            jedis.set(key, value);
        } finally {
            jedis.close();
        }
    }

    /**
     * Set value into Redis with specified time to live in seconds.
     *
     * @param key
     * @param value
     * @param timeToLiveSeconds
     */
    public void setex(String key, String value, int timeToLiveSeconds) {
        Jedis jedis = jedisPool.getResource();

        try {
            jedis.setex(key, timeToLiveSeconds, value);
        } finally {
            jedis.close();
        }
    }

    /**
     * Delete key and its value from Jedis.
     *
     * @param key
     */
    public void del(String key) {
        Jedis jedis = jedisPool.getResource();

        try {
            jedis.del(key);
        } finally {
            jedis.close();
        }
    }

    /**
     * Get keys matches the given pattern.
     *
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.keys(pattern);
        } finally {
            jedis.close();
        }
    }

    /**
     * Get multiple values for the given keys.
     *
     * @param keys
     * @return
     */
    public Collection<String> mget(String... keys) {
        if (keys == null && keys.length == 0) {
            Collections.emptySet();
        }

        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.mget(keys);
        } finally {
            jedis.close();
        }
    }

    /**
     * Publish message to channel using subscribe and publish protocol.
     *
     * @param channel
     * @param value
     */
    public void publish(String channel, String value) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.publish(channel, value);
        } finally {
            jedis.close();
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
