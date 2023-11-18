package by.pvt.shaurma.core.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
//@EnableMethodSecurity(securedEnabled = true, jsr250Enabled =true)
public class SecurityConfig  {


    @Bean
    public PasswordEncoder passwordEncoderByCrypt() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authenticationProvider);
    }


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).httpBasic(withDefaults()).formLogin(withDefaults()).authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.DELETE, "/orders/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/orders/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .requestMatchers(HttpMethod.PUT, "/orders/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/orders/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/shawarmas/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/shawarmas/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/shawarmas/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/burgers/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/burgers/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/burgers/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/clients/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/clients/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/clients/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/clients/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/admins/**").authenticated());
        return http.build();
    }
}
