//package gr.hua.ds.postponement.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import javax.sql.DataSource;
//
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
//                .usersByUsernameQuery("select username,password, enabled from user where username=?")
//                .authoritiesByUsernameQuery("select username, authority from authorities where username=?");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.httpBasic()
//
//                .and().authorizeRequests()
//                //.antMatchers("/**").hasRole("ADMIN")
//                //.antMatchers("/politis/**").hasRole("POLITIS")
//                .antMatchers("/api/**").hasRole("ADMIN")
//                .antMatchers("/politis/**").hasAnyRole("ADMIN","POLITIS")
//
//                .and().csrf().disable().headers().frameOptions().disable()
//
//                .and().formLogin().permitAll()
//
//                .and().logout().permitAll()
//
//                .and().exceptionHandling().accessDeniedPage("/403");
//    }
//
//    //    Paths που θα είναι προσβάσιμα χωρίς authorization
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/resources/");
//        web.ignoring().antMatchers("/");
//        web.ignoring().antMatchers("/api");
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        return encoder;
//    }
//
//
//
//}