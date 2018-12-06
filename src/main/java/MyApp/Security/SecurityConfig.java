package MyApp.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.net.Authenticator;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{


    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/rest/headers/*").hasRole(USER)
                .antMatchers("/all/roles*").hasRole(USER)
                .antMatchers("/all/roles/admin/**").hasRole(ADMIN)
                .and()
                .formLogin();

    }

    @Autowired
    public void configeGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("wiktor").password("aaa").roles(USER)
                .and()
                .withUser("start").password("sss").roles(USER, ADMIN);

    }

}
