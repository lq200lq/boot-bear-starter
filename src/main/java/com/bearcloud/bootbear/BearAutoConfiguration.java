package com.bearcloud.bootbear;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.bearcloud.bootbear.beetl.BeetlExtension;
import com.bearcloud.bootbear.beetl.CustomGroupTemplate;
import com.bearcloud.bootbear.properties.BearStarterProperties;
import com.ibeetl.starter.BeetlTemplateCustomize;
import org.beetl.core.GroupTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = BearStarterProperties.class)
@ConditionalOnClass({BeetlTemplateCustomize.class})
@ConditionalOnProperty(prefix = "bear", value = "enable", matchIfMissing = true)
public class BearAutoConfiguration implements ApplicationContextAware{

    private ApplicationContext applicationContext;

    private Logger logger = LoggerFactory.getLogger(BearAutoConfiguration.class);

    @Autowired
    private BearStarterProperties bearStarterProperties;

    /**
     * beetl配置
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(BeetlTemplateCustomize.class)
    public BeetlTemplateCustomize beetlTemplateCustomize() {
        return groupTemplate -> {

            if(applicationContext.containsBean("customGroupTemplate")){
                CustomGroupTemplate customGroupTemplate = applicationContext.getBean(CustomGroupTemplate.class);
                if(customGroupTemplate != null){
                    BeanUtil.copyProperties(customGroupTemplate,groupTemplate);
                }
            }

            BeetlExtension.anno(groupTemplate,applicationContext);
        };
    }

    /*
     * Mybatis-plus分页插件，自动识别数据库类型
     */
    @Bean
    @ConditionalOnMissingBean(PaginationInterceptor.class)
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
