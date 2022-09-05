package com.byd.gzq.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 4466184
 * @date 2022/8/29 12:52
 */
@Component(value = "erhousheng")
public class WithDate {

    @Value("2099--09--21")
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "WithDate{" +
                "date=" + date.toString() +
                '}';
    }
}
