package com.example.studentadviceproject.config;

import com.example.studentadviceproject.dto.StudentDto;
import com.example.studentadviceproject.entity.Student;
import com.example.studentadviceproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
//import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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

    private final StudentRepository studentRepository;

    public WebSecurityConfig(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        if (studentRepository == null) {
        }
        else {
        }

        return username -> {
            Student student = studentRepository.findStudentsByKodeMelli(username);
            System.out.println(student.getKodeMelli() + " " + student.getPassword());
            String password = passwordEncoder.encode(student.getPassword());
            System.out.println(password);
            if (student == null) {
                throw new UsernameNotFoundException("user not found");
            }
            return org.springframework.security.core.userdetails.User.builder()
                    .username(student.getKodeMelli())
                    .password(student.getPassword())
                    .roles("STUDENT")
                    .build();
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request ->
                        request
                                .requestMatchers("/students/new**")
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
                                .ignoringRequestMatchers("/h2-console/**"));

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/h2-console/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

}