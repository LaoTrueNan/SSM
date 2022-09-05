package com.byd.gzq.bean;

import com.byd.gzq.Customer;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

import java.beans.PropertyEditor;
import java.util.Date;

/**
 * @author 4466184
 * @date 2022/8/29 13:24
 */

public class DatePropertyEditorRegistry implements PropertyEditorRegistrar {

    private PropertyEditor pe;

    public DatePropertyEditorRegistry() {
        System.out.println("com.byd.gzq.bean.DatePropertyEditorRegistry loaded...");
    }

    @Override
    public void registerCustomEditors(PropertyEditorRegistry propertyEditorRegistry) {
        propertyEditorRegistry.registerCustomEditor(java.util.Date.class,getPe());
    }


    public PropertyEditor getPe() {
        return pe;
    }

    public void setPe(PropertyEditor pe) {
        this.pe = pe;
    }
}
