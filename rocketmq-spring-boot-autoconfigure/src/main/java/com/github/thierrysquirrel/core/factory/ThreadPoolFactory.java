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

import com.github.thierrysquirrel.autoconfigure.RocketProperties;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: ThreadPoolFactory
 * Description:
 * date: 2019/4/27 19:52
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ThreadPoolFactory {
	private ThreadPoolFactory() {
	}

	public static ThreadPoolExecutor createConsumeThreadPoolExecutor(RocketProperties rocketProperties) {
		Integer threadNums = rocketProperties.getCreateConsumeThreadNums();

		ThreadFactory threadFactory = new ThreadFactoryBuilder()
				.setNameFormat("InitializeConsumerListener").build();

		return new ThreadPoolExecutor(threadNums,
				threadNums,
				0,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(1024),
				threadFactory,
				new ThreadPoolExecutor.AbortPolicy()
		);
	}

	public static ThreadPoolExecutor createProducerThreadPoolExecutor(RocketProperties rocketProperties) {

		Integer threadNums = rocketProperties.getCreateProducerThreadNums();

		ThreadFactory threadFactory = new ThreadFactoryBuilder()
				.setNameFormat("InitializeProducer").build();

		return new ThreadPoolExecutor(threadNums,
				threadNums,
				0,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(1024),
				threadFactory,
				new ThreadPoolExecutor.AbortPolicy()
		);
	}

	public static ThreadPoolExecutor createSendMessageThreadPoolExecutor(RocketProperties rocketProperties) {

		Integer threadNums = rocketProperties.getSendMessageThreadNums();

		ThreadFactory threadFactory = new ThreadFactoryBuilder()
				.setNameFormat("SendMessage").build();

		return new ThreadPoolExecutor(threadNums,
				threadNums,
				0,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(),
				threadFactory,
				new ThreadPoolExecutor.AbortPolicy()
		);
	}

	public static ThreadPoolExecutor createCallbackThreadPoolExecutor(RocketProperties rocketProperties) {

		Integer threadNums = rocketProperties.getCallbackThreadNums();

		ThreadFactory threadFactory = new ThreadFactoryBuilder()
				.setNameFormat("callback").build();
		return new ThreadPoolExecutor(threadNums,
				threadNums,
				0,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(),
				threadFactory,
				new ThreadPoolExecutor.AbortPolicy()
		);
	}
}
