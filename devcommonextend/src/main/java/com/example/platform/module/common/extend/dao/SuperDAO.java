package com.example.platform.module.common.extend.dao;

/**
 * <p>
 * 自定义 com.example.platform.module.dao 父类
 * </p>
 */
public interface SuperDAO<T> extends com.baomidou.mybatisplus.mapper.BaseMapper<T> {

    // 这里可以写自己的公共方法、注意不要让 mp 扫描到误以为是实体 Base 的操作
}
