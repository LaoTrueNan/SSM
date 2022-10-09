package com.byd.gzq.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditor;
import java.util.Date;
import java.util.List;

/**
 * @author Leonard
 * @date 2022/10/9 9:02
 */
@Component
public class CustomEditor {

    @Bean
    public BeanFactoryPostProcessor getCustomEditorConfigurer(PropertyEditorRegistrar[] propertyEditorRegistrars){
        return new BeanFactoryPostProcessor() {
            @Override
            public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
                for (PropertyEditorRegistrar registrar : propertyEditorRegistrars) {
                    beanFactory.addPropertyEditorRegistrar(registrar);
                }
            }
        };
    }

    @Bean
    public PropertyEditorRegistrar getPropertyEditorRegistrar(PropertyEditor pe){
        return new PropertyEditorRegistrar() {
            @Override
            public void registerCustomEditors(PropertyEditorRegistry registry) {
                registry.registerCustomEditor(Date.class,pe);
            }
        };
    }
}
