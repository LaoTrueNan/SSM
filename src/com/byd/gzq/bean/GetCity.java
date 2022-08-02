package com.byd.gzq.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GetCity {

    @Bean(name = "city")
    public City getACity(){
        City city = new City();
        city.setId(19);
        city.setName("太原");
        city.setCountryCode("CHN");
        city.setDistrict("Asia");
        city.setPopulation(112233);
        return city;
    }
}
