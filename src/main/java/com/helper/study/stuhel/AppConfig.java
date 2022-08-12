package com.helper.study.stuhel;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

//@MapperScan(value="com.helper.study.stuhel.mapper.*", sqlSessionFactoryRef="dbMybatisSqlSessionFactory")
@Configuration
@ComponentScan//@Component 어노테이션이 붙은 애들을 찾아서 자동으로  bean등록을 해준다.  @Bean안해줘도된다.
@RequiredArgsConstructor//초기화
public class AppConfig implements WebMvcConfigurer {
    private final SessionInterceptor sessionInterceptor;
    public void addInterceptors(InterceptorRegistry registry){
        System.out.println("registry = " + registry);
        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns("/stuhel/**")
                .excludePathPatterns("/stuhel").excludePathPatterns("/member/**").excludePathPatterns("/");
    }
    /*
    * public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.maxAge(60, TimeUnit.MINUTES));
    } //  //  1. / 으로 시작되믄 모든 요청을 다룬다.  2.자료의 위치를 입력해준다.  3.캐시 60분으로 설정됨
    * */
}
