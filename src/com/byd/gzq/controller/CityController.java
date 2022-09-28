package com.byd.gzq.controller;

import com.alibaba.dubbo.common.json.JSON;
import com.byd.gzq.bean.*;
import com.byd.gzq.dao.PersonMapper;
import com.byd.gzq.utils.Exception.ServiceException;
import com.rabbitmq.client.Channel;
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
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
    private Channel channel;

    @Autowired
    private PersonMapper mapper;

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

    @GetMapping(value = "getPerson")
    @ResponseBody
    public String getPerson(@RequestParam("id") int id,HttpServletRequest req){
        Person person = mapper.selectPersonById(id);
        try {
            channel.basicPublish("","ssm",null,person.getName().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return person.getName()+"---"+ person.getText();
    }

    @GetMapping(value = "date")
    @ResponseBody
    public String getDate(){
        return "日期"+withDate.toString();
    }

    @PostMapping("newDate")
    @ResponseBody
    public String showInputDate(@RequestParam("date")Date d){
        WithDate a = new WithDate();
        a.setDate(d);
        return a.toString();
    }

    @GetMapping("china")
    @ResponseBody
    public String testChinese(@RequestParam("name") String name) throws ServiceException {
        throw new ServiceException(name,500);
    }
    @GetMapping("consume")
    @ResponseBody
    public String testDel() throws IOException {
        String ssm = channel.basicConsume("ssm", true, (a, b) -> {
            System.out.println(new String(b.getBody(), StandardCharsets.UTF_8));
        }, a -> {
        });
        return ssm;

    }
    // solve the property resolving problems via request params
    @InitBinder
    public void initBinder(WebDataBinder binder,HttpServletRequest req){
        log.warn(req.getRemoteAddr());
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
