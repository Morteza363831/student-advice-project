package com.example.studentadviceproject.config;

import com.example.studentadviceproject.dto.StudentDto;
import com.example.studentadviceproject.entity.Student;
import com.example.studentadviceproject.repository.StudentRepository;
import org.springframework.context.annotation.*;
//import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.annotation.web.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@ComponentScan("com.example")
public class WebSecurityConfig{



//    @Bean
//    public UserDetailsService userDetailsService(StudentRepository studentRepository) {
//
//        return kodeMelli -> {
//            Student student = studentRepository.findStudentsByKodeMelli(kodeMelli);
//            if (student == null) {
//                throw new UsernameNotFoundException("user not found");
//            }
//            return org.springframework.security.core.userdetails.User.builder()
//                    .username("morteza")
//                    .password("123456")
//                    .roles("STUDENT")
//                    .build();
//        };
//    }
    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("morteza")
                .password(passwordEncoder.encode("1234567"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request ->
                        request
                                .requestMatchers("/advices/all","/students/new","students/new","/h2-console/")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .formLogin(request ->
                        request
                                .loginPage("/login")
                                .defaultSuccessUrl("/students/all",true)
                                .permitAll())
                .csrf(csrf ->
                        csrf
                                .ignoringRequestMatchers("/login"));

        return http.build();
    }


//    @Bean
//    public PasswordEncoder passwordEncoder() {
//
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder;
    }

}