package com.byd.gzq.controller;

import com.alibaba.dubbo.common.json.JSON;
import com.byd.gzq.bean.City;
import com.byd.gzq.bean.DatePropertyEditor;
import com.byd.gzq.bean.Person;
import com.byd.gzq.bean.WithDate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @author 4466184
 * @date 2022/8/18 14:56
 */

@Controller
@RequestMapping("/city")
public class CityController {

    private final Logger log = LogManager.getLogger(CityController.class);

    @Autowired
    private Person person;
    @Autowired
    @Qualifier(value = "erhousheng")
    private WithDate withDate;


    public CityController() {
        // debug info warn error fatal
        log.info("CityController has been loaded into IoC container....");
    }

    @GetMapping("/hello")
    public String getPerson(Model model){
        model.addAttribute("person",person);
        return "success1";
    }


    @GetMapping("date")
    @ResponseBody
    public String getDate(){
        return withDate.toString();
    }

    @PostMapping("newDate")
    @ResponseBody
    public String showInputDate(@RequestParam("date")Date d){
        WithDate a = new WithDate();
        a.setDate(d);
        return a.toString();
    }

    // solve the property resolving problems via request params
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(java.util.Date.class,new DatePropertyEditor());
    }
}
