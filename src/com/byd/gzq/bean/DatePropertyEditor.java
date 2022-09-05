package com.byd.gzq.bean;

import com.byd.gzq.Customer;
import com.byd.gzq.utils.GZQ;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.util.Date;

/**
 * @author 4466184
 * @date 2022/8/29 13:00
 */
public class DatePropertyEditor extends PropertyEditorSupport{



    public DatePropertyEditor() {
        System.out.println("com.byd.gzq.bean.DatePropertyEditor loaded...");
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
