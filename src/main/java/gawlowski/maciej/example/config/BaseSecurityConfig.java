package gawlowski.maciej.example.config;

import org.springframework.security.config.annotation.web.builders.*;

/**
 * Base security configuration for keeping same config for prod and for integration tests
 */
class BaseSecurityConfig {

    static void configure(HttpSecurity http) throws Exception {
        http
                .csrf().ignoringAntMatchers(csrfIgnoredAntMatchers())
                .and()
                .authorizeRequests()
                .antMatchers("/secured/**").hasRole("service_admin")
                .anyRequest().permitAll();
    }

    /**
     * Paths excluded from CSRF protection.
     * Those paths do not need to be protected, because are not used in any html/js, so they are not affected by the CSRF attack.
     */
    private static String[] csrfIgnoredAntMatchers() {
        return new String[]{};
        //        return new String[]{"", ""};
    }
}
