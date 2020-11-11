package com.stealth.yash.FaceRecognition.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static com.stealth.yash.FaceRecognition.enums.Roles.ADMIN;
import static com.stealth.yash.FaceRecognition.enums.Roles.STUDENT;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final LoggingAccessDeniedHandler accessDeniedHandler;


    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler customeSuccessHandler(){
        return new CustomeSuccessHandler();
    }

    public SecurityConfig(LoggingAccessDeniedHandler accessDeniedHandler, UserDetailsServiceImpl userDetailsService) {
        this.accessDeniedHandler = accessDeniedHandler;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers( "/","/index", "/about","/confirm-account", "/contact","/accountVerified").permitAll()
                .antMatchers("/dashboard", "/students/**", "/comingsoon/**", "/usermanagement/**","/courses/**", "/departments/**", "/fobmanager/**","/institutes/**", "/professors/**", "/programs/**")
                .hasRole(ADMIN.name())
                .antMatchers("/student").hasRole(STUDENT.name())
                .anyRequest().authenticated()
                .and()
                .formLogin() .loginPage("/login")
                .loginProcessingUrl("/login").successHandler(customeSuccessHandler()).permitAll()
                .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout").permitAll()
        .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**", "/assets/**", "/img/**", "/scss/**", "/js/**", "/vendor/**","/templates/**");
    }

}
