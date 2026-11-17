package com.example.studentmanagement;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.service.StudentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentManagementApplicationTests {

    @Autowired
    private StudentService studentService;

    @Test
    void contextLoads() {
        assertNotNull(studentService);
    }

    @Test
    @DisplayName("Should successfully perform Student CRUD operations")
    void testStudentCrud() {
        // Create
        Student newStudent = new Student("TestFirst", "TestLast", "test.unique@university.edu", "Computer Science", 3.90, "1234567890");
        Student saved = studentService.saveStudent(newStudent);
        assertNotNull(saved.getId());

        // Read
        Student fetched = studentService.getStudentById(saved.getId());
        assertEquals("TestFirst", fetched.getFirstName());
        assertEquals("TestLast", fetched.getLastName());

        // Search
        List<Student> searchResults = studentService.searchStudents("TestFirst");
        assertFalse(searchResults.isEmpty());
        assertTrue(searchResults.stream().anyMatch(s -> s.getEmail().equals("test.unique@university.edu")));

        // Update
        fetched.setDepartment("Data Science");
        Student updated = studentService.updateStudent(fetched.getId(), fetched);
        assertEquals("Data Science", updated.getDepartment());

        // Delete
        studentService.deleteStudent(saved.getId());
        assertThrows(IllegalArgumentException.class, () -> studentService.getStudentById(saved.getId()));
    }
}
