package com.kuzin.service.auth;

import static com.kuzin.entity.enums.ApplicationUserRole.*;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



/** security config class.*/

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    DataSource dataSource;
    Environment environment;
    PasswordEncoder encoder;

    @Autowired
    public SecurityConfig(DataSource dataSource,
                          Environment environment, PasswordEncoder encoder) {
        this.dataSource = dataSource;
        this.environment = environment;
        this.encoder = encoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/").permitAll()
                .antMatchers("/articles/api/report/**").hasRole(WORKER.name())
                .antMatchers("/articles/api/description/**").hasRole(WORKER.name())
                .antMatchers("/articles/api/repair/export/**").hasRole(WORKER.name())
                .antMatchers("/articles/**").hasRole(ADMIN.name())
                .antMatchers("/material").hasRole(SUPP.name())
                .antMatchers("/persons/**").hasRole(ADMIN.name())
                .antMatchers("/supp/**").hasRole(SUPP.name())
                .antMatchers("/user/**").hasRole(WORKER.name())
                .antMatchers("/admin").hasRole(ADMIN.name())
                .antMatchers("/menu").hasRole(ADMIN.name())
                .antMatchers("/repair").hasRole(WORKER.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                    .loginPage("/getLogin")
                    .permitAll()
                    .defaultSuccessUrl("/", true)
                    .passwordParameter("password")
                    .usernameParameter("username")
                .and()
                .rememberMe()
                    .rememberMeParameter("remember-me")
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me")
                    .logoutSuccessUrl("/");
    }

}