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

import com.aliyun.openservices.ons.api.PropertyKeyConst;
import io.github.thierrysquirrel.rocketmq.annotation.MessageListener;
import io.github.thierrysquirrel.rocketmq.annotation.RocketListener;
import io.github.thierrysquirrel.rocketmq.autoconfigure.RocketProperties;

import java.util.Properties;

/**
 * ClassName: ConsumerPropertiesFactory
 * Description:
 * date: 2026/6/4
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class ConsumerPropertiesFactory {
    private ConsumerPropertiesFactory() {
    }

    public static Properties createConsumerProperties(RocketProperties rocketProperties,
                                                      MessageListener consumerListener) {

        Properties properties = PropertiesFactory.createProperties(rocketProperties);

        properties.put(PropertyKeyConst.GROUP_ID, consumerListener.groupId());
        properties.put(PropertyKeyConst.MessageModel, consumerListener.messageModel());
        properties.put(PropertyKeyConst.ConsumeThreadNums, rocketProperties.getConsumeThreadNums());
        properties.put(PropertyKeyConst.MaxReconsumeTimes, rocketProperties.getMaxReconsumeTimes());
        properties.put(PropertyKeyConst.ConsumeTimeout, rocketProperties.getConsumeTimeout());


        return properties;

    }
}
