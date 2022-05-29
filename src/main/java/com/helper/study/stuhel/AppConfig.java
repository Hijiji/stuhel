package com.helper.study.stuhel;

import com.helper.study.stuhel.controller.MemberController;
import com.helper.study.stuhel.repository.JpaMemberRepository;
import com.helper.study.stuhel.service.MemberService;
import com.helper.study.stuhel.service.MemberServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@MapperScan(value="com.helper.study.stuhel.mapper.*", sqlSessionFactoryRef="dbMybatisSqlSessionFactory")
@Configuration
@ComponentScan//@Component 어노테이션이 붙은 애들을 찾아서 자동으로  bean등록을 해준다.  @Bean안해줘도된다.
public class AppConfig {

}
