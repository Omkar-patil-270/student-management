package com.example.studentmanagement;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class StudentManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(StudentRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.saveAll(List.of(
                        new Student("Alex", "Morgan", "alex.morgan@university.edu", "Computer Science", 3.85, "+1 555-0101"),
                        new Student("Sophia", "Chen", "sophia.chen@university.edu", "Data Science", 3.92, "+1 555-0102"),
                        new Student("Marcus", "Johnson", "marcus.j@university.edu", "Electrical Engineering", 3.65, "+1 555-0103"),
                        new Student("Emma", "Watson", "emma.watson@university.edu", "Business Analytics", 3.78, "+1 555-0104"),
                        new Student("Liam", "Davis", "liam.davis@university.edu", "Software Engineering", 3.40, "+1 555-0105")
                ));
            }
        };
    }
}
