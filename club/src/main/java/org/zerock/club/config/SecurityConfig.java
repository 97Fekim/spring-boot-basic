package org.zerock.club.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.zerock.club.security.filter.ApiCheckFilter;
import org.zerock.club.security.handler.ClubLoginSuccessHandler;
import org.zerock.club.security.service.ClubUserDetailsService;

@Configuration
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired    // 주입
    private ClubUserDetailsService userDetailsService;

    // PasswordEncode에 주입될 구현체 : BCryptPasswordEncoder
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{

//        http.authorizeRequests().antMatchers("/sample/all").permitAll()
//                .antMatchers("/sample/member").hasRole("USER");

        http.formLogin();
        http.csrf().disable();  // csrf 토큰 비활성화
        http.logout();
        http.oauth2Login().successHandler(successHandler());
        http.rememberMe().tokenValiditySeconds(60*60*24*7)
                .userDetailsService(userDetailsService);    // 7days

        http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    // SuccessHandler 생성자 주입
    @Bean
    public ClubLoginSuccessHandler successHandler() {
        return new ClubLoginSuccessHandler(passwordEncoder());
    }

    @Bean
    public ApiCheckFilter apiCheckFilter() {
        return new ApiCheckFilter("/notes/**/*");
    }

    // ClubUserDatilsService 가 있으므로, 이 부분은 필요가 없다
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        // inMemoryAuthentication으로 한명의 사용자를 생성
//        // 사용자 계정은 user1
//        auth.inMemoryAuthentication().withUser("user1")
//                // "1111" 패스워드의 인코딩 결과
//                .password("$2a$10$dLW2uZDaAhRcBlXkGgjTqOW0mnmMN6dTLBBr8i6NnWzXkhwDGI9oG")
//                .roles("USER");
//        // id : user1 , pw : 1111 -> "USER"
//    }

}
