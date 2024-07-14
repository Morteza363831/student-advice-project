package com.example.studentadviceproject;

import com.example.studentadviceproject.entity.Student;
import com.example.studentadviceproject.repository.StudentRepository;
import io.micrometer.observation.Observation;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.example")
public class StudentAdviceProjectApplication implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentAdviceProjectApplication(StudentRepository studentRepository,
                                           PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
    public static void main(String[] args) {
        SpringApplication.run(StudentAdviceProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Smith");
        student.setKodeMelli("6800537799");
        String encodedPassword = passwordEncoder.encode("123456");
        student.setPassword(encodedPassword);
        studentRepository.save(student);

    }
}
