package by.pvt.shaurma.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoderByCrypt() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//            // The builder will ensure the passwords are encoded before saving in memory
//            UserDetails user = User.builder()
//                    .username("user")
//                    .password("password")
//                    .roles("CLIENT")
//                    .build();
//            UserDetails admin = User.builder()
//                    .username("admin")
//                    .password("password")
//                    .roles("CLIENT", "ADMIN")
//                    .build();
//            return new InMemoryUserDetailsManager(user, admin);
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(
//            UserDetailsService userDetailsService,
//            PasswordEncoder passwordEncoder) {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder);
//
//        return new ProviderManager(authenticationProvider);
//    }

//    @Bean
//    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable).httpBasic(Customizer.withDefaults()).authorizeHttpRequests(authorize -> authorize
//                .requestMatchers(HttpMethod.DELETE, "/orders/**").hasAuthority("ADMIN")
//                .requestMatchers(HttpMethod.POST, "/orders/**").hasAuthority("ADMIN")
//                .requestMatchers(HttpMethod.PUT, "/orders/**").hasAuthority("ADMIN")
//                .requestMatchers(HttpMethod.GET, "/orders/**").authenticated()
//                .requestMatchers(HttpMethod.DELETE, "/shawarmas/**").hasAuthority("ADMIN")
//                .requestMatchers(HttpMethod.POST, "/shawarmas/**").hasAuthority("ADMIN")
//                .requestMatchers(HttpMethod.PUT, "/shawarmas/**").hasAuthority("ADMIN")
//                .requestMatchers(HttpMethod.DELETE, "/burgers/**").hasAuthority("ADMIN")
//                .requestMatchers(HttpMethod.POST, "/burgers/**").hasAuthority("ADMIN")
//                .requestMatchers(HttpMethod.PUT, "/burgers/**").hasAuthority("ADMIN")
////                .requestMatchers("/admins/**").hasAuthority("Admin")
//                .requestMatchers(HttpMethod.DELETE, "/clients/**").hasAuthority("ADMIN")
//                .requestMatchers(HttpMethod.PUT, "/clients/**").hasAuthority("ADMIN")
//                .requestMatchers(HttpMethod.GET, "/clients/**").authenticated()
//                .requestMatchers("/admins/**").authenticated()
//                .requestMatchers("/basket/**").authenticated());
//        return http.build();
//    }
}
