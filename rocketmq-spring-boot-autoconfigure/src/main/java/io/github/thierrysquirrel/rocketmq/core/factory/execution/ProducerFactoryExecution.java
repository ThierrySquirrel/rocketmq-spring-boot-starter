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

package io.github.thierrysquirrel.rocketmq.core.factory.execution;

import io.github.thierrysquirrel.rocketmq.annotation.RocketMessage;
import io.github.thierrysquirrel.rocketmq.autoconfigure.RocketProperties;
import io.github.thierrysquirrel.rocketmq.core.strategy.PutProducerStrategy;
import io.github.thierrysquirrel.rocketmq.thread.AbstractProducerThread;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * ClassName: ProducerFactoryExecution
 * Description:
 * date: 2026/6/4
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class ProducerFactoryExecution extends AbstractProducerThread {

    public ProducerFactoryExecution(Map<String, Object> producerConsumer, RocketMessage rocketMessage, Object bean, RocketProperties rocketProperties, ApplicationContext applicationContext) {
        super(producerConsumer, rocketMessage, bean, rocketProperties, applicationContext);
    }

    /**
     * 开始向容器装填
     *
     * @param producerConsumer   producerConsumer
     * @param rocketMessage      rocketMessage
     * @param bean               bean
     * @param rocketProperties   rocketProperties
     * @param applicationContext applicationContext
     */
    @Override
    protected void statsPutProducer(Map<String, Object> producerConsumer, RocketMessage rocketMessage, Object bean, RocketProperties rocketProperties, ApplicationContext applicationContext) {
        PutProducerStrategy.putProducer(producerConsumer, rocketMessage, bean, rocketProperties, applicationContext);
    }
}
