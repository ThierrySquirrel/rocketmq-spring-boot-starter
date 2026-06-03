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

package io.github.thierrysquirrel.rocketmq.annotation;

import com.aliyun.openservices.ons.api.SendCallback;
import io.github.thierrysquirrel.rocketmq.core.producer.DefaultSendCallback;
import io.github.thierrysquirrel.rocketmq.core.producer.MessageSendType;

import java.lang.annotation.*;

/**
 * ClassName: CommonMessage
 * Description:
 * date: 2026/6/4
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CommonMessage {
    /**
     * 您在控制台创建的 Group ID
     *
     * @return String
     */
    String groupId() default "";

    /**
     * Message 所属的 Topic
     *
     * @return String
     */
    String topic() default "";

    /**
     * 订阅指定 Topic 下的 Tags：
     * 1. * 表示订阅所有消息
     * 2. TagA || TagB || TagC 表示订阅 TagA 或 TagB 或 TagC 的消息
     *
     * @return String
     */
    String tag() default "*";

    /**
     * 消息发送类型 默认异步
     *
     * @return MessageSendType
     */
    MessageSendType messageSendType() default MessageSendType.SEND_ASYNC;

    /**
     * 自定义SendCallback类
     *
     * @return callback
     */
    Class<? extends SendCallback> callback() default DefaultSendCallback.class;
}
