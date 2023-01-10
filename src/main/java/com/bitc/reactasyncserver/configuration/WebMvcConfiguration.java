package com.bitc.reactasyncserver.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        //addMapping() : 지정한 패턴에 맞는 페이지에 대한 접근 권한 확인하기
        //allowedOrigins(url) : 접근 허용할 외부 url
//        registry.addMapping("/**")
//                최상위/모든 주소허용하겠음.
//                .allowedOrigins("http://localhost:3000","http://localhost:4000" );
//        모든 페이지 적용 -> 상속 시켜서 적용시킴.
        registry.addMapping("/login/**")
                .allowedOrigins("http://localhost:3000");
        //-> http://localhost:3000/login/** (login 이후의 것들은 전부 해당 주소로 접근가능))
    }
}
