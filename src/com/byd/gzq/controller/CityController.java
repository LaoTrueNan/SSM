package com.byd.gzq.controller;

import com.byd.gzq.bean.City;
import com.byd.gzq.bean.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 4466184
 * @date 2022/8/18 14:56
 */

@Controller
public class CityController {

    private final Logger log = LogManager.getLogger(CityController.class);

    @Autowired
    private Person person;

    public CityController() {
        // debug info warn error fatal
        log.warn("CityController has been loaded into IoC container....");
    }

    @GetMapping("/hello")
    public String getPerson(Model model){
        model.addAttribute("person",person);
        return "success1";
    }
}
