package MyApp.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;
import java.net.Authenticator;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    //https://www.youtube.com/watch?v=i5IcfjS4x8I
    //https://www.youtube.com/watch?v=V3rvY9j1ro0&t=2421s

    @Autowired
    DataSource dataSource;

    private final String USERS_QUERY = "Select login, password, active from users where login=?";
    private final String ROLES_QUERY = "SELECT u.login, r.roles FROM USERS U INNER JOIN user_roles UR ON (U.ID=UR.USER_ID) INNER JOIN roles R ON (UR.ROLE_ID=R.ID) where u.login=?";

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(USERS_QUERY)
                .authoritiesByUsernameQuery(ROLES_QUERY);

    }

    protected void configure(HttpSecurity http) throws Exception{

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/loginek").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/all/**").hasAnyAuthority("ADMIN","USER").anyRequest()
                .authenticated().and().csrf().disable()
                .formLogin().loginPage("/loginek")
                .failureUrl("/loginek?error-true")
                .defaultSuccessUrl("/all/items")
                .usernameParameter("login")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/loginek")
                .and().rememberMe()
                .and().exceptionHandling().accessDeniedPage("/access_denied");
    }

}
