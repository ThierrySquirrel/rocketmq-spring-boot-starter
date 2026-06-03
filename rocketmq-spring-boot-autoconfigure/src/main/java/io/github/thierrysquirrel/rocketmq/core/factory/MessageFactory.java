/**
 * Copyright 2026/6/4 ThierrySquirrel
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
 **/

package io.github.thierrysquirrel.rocketmq.core.factory;

import com.aliyun.openservices.ons.api.Message;
import io.github.thierrysquirrel.rocketmq.annotation.CommonMessage;
import io.github.thierrysquirrel.rocketmq.annotation.OrderMessage;
import io.github.thierrysquirrel.rocketmq.annotation.TransactionMessage;

/**
 * ClassName: MessageFactory
 * Description:
 * date: 2026/6/4
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class MessageFactory {
    private MessageFactory() {
    }

    public static Message createMessage(CommonMessage commonMessage, byte[] body) {
        return new Message(commonMessage.topic(),
                commonMessage.tag(),
                body);
    }

    public static Message createMessage(OrderMessage orderMessage, byte[] body) {
        return new Message(orderMessage.topic(),
                orderMessage.tag(),
                body);
    }

    public static Message createMessage(TransactionMessage transactionMessage, byte[] body) {
        return new Message(transactionMessage.topic(),
                transactionMessage.tag(),
                body);
    }
}
