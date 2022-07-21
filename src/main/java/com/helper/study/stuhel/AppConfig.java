package com.helper.study.stuhel;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@MapperScan(value="com.helper.study.stuhel.mapper.*", sqlSessionFactoryRef="dbMybatisSqlSessionFactory")
@Configuration
@ComponentScan//@Component 어노테이션이 붙은 애들을 찾아서 자동으로  bean등록을 해준다.  @Bean안해줘도된다.
@RequiredArgsConstructor//초기화
public class AppConfig implements WebMvcConfigurer {
    private final SessionInterceptor sessionInterceptor;
    public void addInterceptors(InterceptorRegistry registry){
        System.out.println("registry = " + registry);
        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/");
    }
}
