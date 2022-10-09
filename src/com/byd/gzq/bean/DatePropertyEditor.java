package com.byd.gzq.bean;

import com.byd.gzq.Customer;
import com.byd.gzq.utils.GZQ;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.util.Date;

/**
 * @author 4466184
 * @date 2022/8/29 13:00
 */
@Component
public class DatePropertyEditor extends PropertyEditorSupport{

    public DatePropertyEditor() {
        super();
    }

    @Override
    @Customer
    public void setAsText(String text) throws IllegalArgumentException {
        String s = text.replaceAll("-+", "/");
        System.out.println(s);
        Date date = new Date(s);
        setValue(date);
    }

}
