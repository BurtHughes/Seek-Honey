package com.penguin.find.seekhoney.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

/**
 * 获取Spring Bean的工具类<br/>
 * 用以访问Spring容器管理的Bean
 */
@Component
public final class BeanUtil extends ApplicationObjectSupport {

    /**
     * 应用程序上下文
     */
    private static ApplicationContext applicationContext = null;

    /**
     * 初始化应用程序上下文
     * @param context
     * @throws BeansException
     */
    @Override
    protected void initApplicationContext(ApplicationContext context) throws BeansException {
        super.initApplicationContext(context);
        if(null == BeanUtil.applicationContext) {
            BeanUtil.applicationContext = context;
        }
    }

    /**
     * 获取Spring Bean<br/>
     * 根据提供的Bean名称(id)获取对应的Bean
     * @param beanName Bean的id
     * @return 获取到的Bean
     */
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }
}
