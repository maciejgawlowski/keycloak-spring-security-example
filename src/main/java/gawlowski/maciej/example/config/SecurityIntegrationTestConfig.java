package gawlowski.maciej.example.config;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;

import static gawlowski.maciej.example.config.ServiceProfile.*;

@Profile(INTEGRATION_TEST)
@EnableWebSecurity
public class SecurityIntegrationTestConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("password").roles("service_admin");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        BaseSecurityConfig.configure(http);
    }
}
