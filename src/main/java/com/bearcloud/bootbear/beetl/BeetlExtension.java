package com.bearcloud.bootbear.beetl;

import com.bearcloud.bootbear.beetl.anno.FunctionName;
import com.bearcloud.bootbear.beetl.anno.TagName;
import org.beetl.core.Function;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Tag;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * beetl配置扩展
 */
public class BeetlExtension {

    /**
     * 扩展注解
     * @param groupTemplate
     */
    public static void anno(GroupTemplate groupTemplate, ApplicationContext applicationContext){
        //放置自定义标签
        Map<String, Tag> tags = applicationContext.getBeansOfType(Tag.class);
        for (String beanName : tags.keySet()) { // 读取自定义标签名
            TagName anno = tags.get(beanName).getClass().getAnnotation(TagName.class);
            String name = anno != null ? anno.value() : beanName;
            groupTemplate.registerTag(name,tags.get(beanName).getClass());
        }

        //放置自定义方法
        Map<String, Function> functions = applicationContext.getBeansOfType(Function.class);
        for (String beanName : functions.keySet()) { // 读取自定义标签名
            FunctionName anno = functions.get(beanName).getClass().getAnnotation(FunctionName.class);
            String name = anno != null ? anno.value() : beanName;
            groupTemplate.registerFunction(name,functions.get(beanName));
        }
    }

}
