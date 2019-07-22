package com.example.platform.module.common.extend.amq;


import com.example.platform.module.common.extend.amq.entity.QMessage;

/**
 */
public interface IConsumer {

    QMessage receiveMessageSync(String destination) throws Exception;

    QMessage receiveMessageSync(String destination, boolean isTransaction) throws Exception;

    QMessage receiveMessageSync(String destination, long timeout) throws Exception;

    QMessage receiveMessageSync(String destination, boolean isTransaction, long timeout) throws Exception;

}
