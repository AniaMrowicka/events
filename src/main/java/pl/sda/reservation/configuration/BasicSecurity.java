package pl.sda.reservation.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.sda.reservation.service.AppUserDetailsService;

@Configuration
public class BasicSecurity extends WebSecurityConfigurerAdapter {


    @Autowired
    private AppUserDetailsService appUserDetailsService;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() // po autorizeRequest mozemy dodac wiele reguł.
                .antMatchers("/",
                        "/about",
                        "/register",
                        "/webjars/**",
                        "/css/**").permitAll()
//wsyztskie adresy  beda mialy pozwolenie bez koniecznosci loogwania//wszytsko co jest od tymi adresami jest dostepne
                .anyRequest()
                        .authenticated()
//wszytsko pozostale musi byz zalogowane
                .and().formLogin().
                        loginPage("/login")
                .defaultSuccessUrl("/reservation/list")
                        .permitAll()
//                kazdy powinien miec dostep
                .and().logout().clearAuthentication(true)
                        .logoutUrl("/logout")
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true);
//        wskazanie adresow stron pod ktorymi dosteone beda formularze.
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider= new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(appUserDetailsService);

        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
}
