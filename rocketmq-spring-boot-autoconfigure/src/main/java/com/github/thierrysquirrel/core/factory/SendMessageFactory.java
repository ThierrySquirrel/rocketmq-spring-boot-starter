/**
 * Copyright 2019 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.thierrysquirrel.core.factory;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.order.OrderProducer;
import com.aliyun.openservices.ons.api.transaction.TransactionProducer;
import com.github.thierrysquirrel.annotation.CommonMessage;
import com.github.thierrysquirrel.annotation.OrderMessage;
import com.github.thierrysquirrel.annotation.TransactionMessage;
import com.github.thierrysquirrel.core.strategy.SendMessageStrategy;
import com.github.thierrysquirrel.core.utils.ApplicationContextUtils;
import org.springframework.context.ApplicationContext;


/**
 * ClassName: SendMessageFactory
 * Description:
 * date: 2019/4/29 21:52
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SendMessageFactory {
    private SendMessageFactory() {
    }

    public static void sendMessage(Long startDeliverTime, Producer producer, CommonMessage commonMessage, byte[] bytes, ApplicationContext applicationContext) {
        Message message = MessageFactory.createMessage (commonMessage, bytes);
        if (null != startDeliverTime) {
            message.setStartDeliverTime (startDeliverTime);
        }
        SendMessageStrategy.send (commonMessage, producer, message, applicationContext);

    }

    public static void sendMessage(OrderProducer orderProducer, OrderMessage orderMessage, byte[] bytes) {
        Message message = MessageFactory.createMessage (orderMessage, bytes);
        orderProducer.send (message, orderMessage.shardingKey ());

    }

    public static void sendMessage(TransactionProducer transactionProducer, TransactionMessage transactionMessage, byte[] bytes, ApplicationContext applicationContext) {
        Message message = MessageFactory.createMessage (transactionMessage, bytes);
        transactionProducer.send (message, ApplicationContextUtils.getLocalTransactionExecuter (applicationContext, transactionMessage.executer ()), null);
    }
}
