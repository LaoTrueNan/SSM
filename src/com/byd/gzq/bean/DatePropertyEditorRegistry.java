package com.byd.gzq.bean;

import com.byd.gzq.Customer;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditor;
import java.util.Date;

/**
 * @author 4466184
 * @date 2022/8/29 13:24
 */

public class DatePropertyEditorRegistry implements PropertyEditorRegistrar {

//    private PropertyEditor pe;
//
    @Override
    public void registerCustomEditors(PropertyEditorRegistry propertyEditorRegistry) {
//        propertyEditorRegistry.registerCustomEditor(java.util.Date.class,pe);
    }

}
