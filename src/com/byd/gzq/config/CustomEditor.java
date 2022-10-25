package com.byd.gzq.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.beans.PropertyEditor;
import java.util.Date;

/**
 * @author Leonard
 * @date 2022/10/9 9:02
 */
@Component
public class CustomEditor {

    @Bean
    public BeanFactoryPostProcessor getCustomEditorConfigurer(PropertyEditorRegistrar[] propertyEditorRegistrars){
        return beanFactory -> {
            for (PropertyEditorRegistrar registrar : propertyEditorRegistrars) {
                beanFactory.addPropertyEditorRegistrar(registrar);
            }
        };
    }

    @Bean
    public PropertyEditorRegistrar getPropertyEditorRegistrar(PropertyEditor pe){
        return registry -> registry.registerCustomEditor(Date.class,pe);
    }

    @Bean
    public ServerEndpointExporter getServerEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
