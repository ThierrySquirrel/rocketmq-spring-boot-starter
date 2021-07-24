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

package com.github.thierrysquirrel.aspect;

import com.github.thierrysquirrel.annotation.CommonMessage;
import com.github.thierrysquirrel.annotation.OrderMessage;
import com.github.thierrysquirrel.annotation.RocketMessage;
import com.github.thierrysquirrel.annotation.TransactionMessage;
import com.github.thierrysquirrel.autoconfigure.RocketProperties;
import com.github.thierrysquirrel.core.factory.StartDeliverTimeFactory;
import com.github.thierrysquirrel.core.factory.ThreadPoolFactory;
import com.github.thierrysquirrel.core.utils.AspectUtils;
import com.github.thierrysquirrel.core.utils.InterceptRocket;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: RocketAspect
 * Description:
 * date: 2019/4/28 21:07
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Aspect
@Slf4j
@Data
public class RocketAspect implements ApplicationContextAware {
	private Map<String, Object> consumerContainer;
	private RocketProperties rocketProperties;
	private ThreadPoolExecutor threadPoolExecutor;
	private ApplicationContext applicationContext;

	public RocketAspect(Map<String, Object> consumerContainer, RocketProperties rocketProperties) {
		this.consumerContainer = consumerContainer;
		this.rocketProperties = rocketProperties;
		this.threadPoolExecutor = ThreadPoolFactory.createSendMessageThreadPoolExecutor(rocketProperties);
	}

	@Pointcut("@annotation(com.github.thierrysquirrel.annotation.CommonMessage)")
	public void commonMessagePointcut() {
		log.debug("Start sending CommonMessage");
	}

	@Pointcut("@annotation(com.github.thierrysquirrel.annotation.OrderMessage)")
	public void orderMessagePointcut() {
		log.debug("Start sending OrderMessage");
	}

	@Pointcut("@annotation(com.github.thierrysquirrel.annotation.TransactionMessage)")
	public void transactionMessagePointcut() {
		log.debug("Start sending TransactionMessage");
	}

	@Around("commonMessagePointcut()")
	public Object rockerMessageSend(ProceedingJoinPoint point) throws Throwable {
		return InterceptRocket.intercept(
				StartDeliverTimeFactory.getStartDeliverTime(point.getArgs(),AspectUtils.getParams(point)),
				AspectUtils.getDeclaringClassAnnotation(point, RocketMessage.class),
				AspectUtils.getAnnotation(point, CommonMessage.class),
				point.proceed(),
				consumerContainer,
				threadPoolExecutor,
				applicationContext);
	}

	@Around("orderMessagePointcut()")
	public Object orderMessageSend(ProceedingJoinPoint point) throws Throwable {
		return InterceptRocket.intercept(
				StartDeliverTimeFactory.getStartDeliverTime(point.getArgs(),AspectUtils.getParams(point)),
				AspectUtils.getDeclaringClassAnnotation(point, RocketMessage.class),
				AspectUtils.getAnnotation(point, OrderMessage.class),
				point.proceed(),
				consumerContainer,
				threadPoolExecutor,
				applicationContext);
	}

	@Around("transactionMessagePointcut()")
	public Object transactionMessageSend(ProceedingJoinPoint point) throws Throwable {
		return InterceptRocket.intercept(
				StartDeliverTimeFactory.getStartDeliverTime(point.getArgs(),AspectUtils.getParams(point)),
				AspectUtils.getDeclaringClassAnnotation(point, RocketMessage.class),
				AspectUtils.getAnnotation(point, TransactionMessage.class),
				point.proceed(),
				consumerContainer,
				threadPoolExecutor,
				applicationContext);
	}

	@Override
	public void setApplicationContext(@Nonnull ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
}
