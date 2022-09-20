package com.byd.gzq.controller;

import com.alibaba.dubbo.common.json.JSON;
import com.byd.gzq.bean.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author 4466184
 * @date 2022/8/18 14:56
 */

@Controller
@RequestMapping("/city")
public class CityController {

    private final Logger log = LogManager.getLogger(CityController.class);


    private Person person;
    @Autowired
    @Qualifier(value = "erhousheng")
    private WithDate withDate;


    public CityController() {
        // debug info warn error fatal
        log.info("CityController has been loaded into IoC container....");
    }

    // 加了Lookup注解的方法,其方法体并没有被执行,因为已经被CGLib重写了
    // 所以前面的作用域限定符必须满足被重写的条件<public|protected>
//    @Lookup
//    public Person getPerson2(){
////        System.out.println(111);
//        // 直接return null也是可以的
////        return new Person();
//        return null;
//    }

//    @GetMapping("/hello")
//    public String getPerson(Model model, HttpServletRequest request){
//        System.out.println(request.getRemoteAddr());
//        model.addAttribute("person",getPerson2());
//        return "success1";
//    }


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

    @DeleteMapping("delete")
    @ResponseBody
    public String testDel(Person p){
        log.warn(p);
        return p.getName();
    }
    // solve the property resolving problems via request params
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(java.util.Date.class,new DatePropertyEditor());
    }

//    public void springMvc加载过程(){
//        HttpServletBean.init(){
//            FrameworkServlet.initServletBean(){
//                initWebApplicationContext()//SpringMVC基于spring,先在initWebApplicationContext中加载父容器
//                    ->
//                DispatcherServlet.onRefresh();
//                    ->
//                initStrategies(); initHandlerMappings(); initHandlerAdapters(); initViewResolvers();...
//                    ->
//            };
//        };
//
//        http 80
//        tcp 21
//        ssh scp 22

//    }
}
