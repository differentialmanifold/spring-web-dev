package com.example.platform.module.common.extend.amq;


import com.example.platform.module.common.extend.amq.entity.QMessage;

import java.util.List;

/**
 */
public interface IProducer {

    void sendMessage(QMessage qMessage) throws Exception;

    void sendMessage(QMessage qMessage, int priority) throws Exception;

    void sendMessage(List<QMessage> qMessageList, boolean isTransaction, String destincation, boolean isPersistent) throws Exception;

}	
