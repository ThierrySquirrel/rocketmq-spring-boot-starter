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

import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.order.OrderProducer;
import com.aliyun.openservices.ons.api.transaction.LocalTransactionChecker;
import com.aliyun.openservices.ons.api.transaction.TransactionProducer;
import io.github.thierrysquirrel.rocketmq.annotation.RocketMessage;
import io.github.thierrysquirrel.rocketmq.autoconfigure.RocketProperties;

import java.util.Properties;

/**
 * ClassName: ProducerFactory
 * Description:
 * date: 2026/6/4
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class ProducerFactory {
    private ProducerFactory() {
    }

    public static Producer createProducer(String groupId, RocketProperties rocketProperties) {
        Properties properties = ProducerPropertiesFactory.createProducerProperties(groupId, rocketProperties);
        return ONSFactory.createProducer(properties);
    }

    public static OrderProducer createOrderProducer(String groupId, RocketProperties rocketProperties) {
        Properties properties = ProducerPropertiesFactory.createProducerProperties(groupId, rocketProperties);
        return ONSFactory.createOrderProducer(properties);
    }

    public static TransactionProducer createTransactionProducer(String groupId, RocketProperties rocketProperties, LocalTransactionChecker localTransactionChecker) {
        Properties properties = ProducerPropertiesFactory.createProducerProperties(groupId, rocketProperties);
        properties.put(PropertyKeyConst.CheckImmunityTimeInSeconds, rocketProperties.getCheckImmunityTimeInSeconds());
        return ONSFactory.createTransactionProducer(properties, localTransactionChecker);
    }
}
