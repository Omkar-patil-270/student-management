package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/students";
    }

    @GetMapping("/students")
    public String listStudents(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Student> students;
        if (keyword != null && !keyword.trim().isEmpty()) {
            students = studentService.searchStudents(keyword);
            model.addAttribute("keyword", keyword.trim());
        } else {
            students = studentService.getAllStudents();
        }

        double avgGpa = students.isEmpty() ? 0.0 :
                students.stream().mapToDouble(Student::getGpa).average().orElse(0.0);

        model.addAttribute("students", students);
        model.addAttribute("totalStudents", students.size());
        model.addAttribute("avgGpa", String.format("%.2f", avgGpa));
        return "students";
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model) {
        Student student = new Student();
        student.setGpa(3.5); // Default suggested GPA
        model.addAttribute("student", student);
        model.addAttribute("pageTitle", "Add New Student");
        return "create-student";
    }

    @PostMapping("/students")
    public String saveStudent(@Valid @ModelAttribute("student") Student student,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              Model model) {

        if (studentService.isEmailTaken(student.getEmail(), null)) {
            bindingResult.rejectValue("email", "error.student", "This email address is already in use.");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "Add New Student");
            return "create-student";
        }

        try {
            studentService.saveStudent(student);
            redirectAttributes.addFlashAttribute("successMessage", "Student '" + student.getFullName() + "' added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error saving student: " + e.getMessage());
        }
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Student student = studentService.getStudentById(id);
            model.addAttribute("student", student);
            model.addAttribute("pageTitle", "Edit Student");
            return "edit-student";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/students";
        }
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id,
                                @Valid @ModelAttribute("student") Student student,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                Model model) {

        if (studentService.isEmailTaken(student.getEmail(), id)) {
            bindingResult.rejectValue("email", "error.student", "This email address is already used by another student.");
        }

        if (bindingResult.hasErrors()) {
            student.setId(id);
            model.addAttribute("pageTitle", "Edit Student");
            return "edit-student";
        }

        try {
            studentService.updateStudent(id, student);
            redirectAttributes.addFlashAttribute("successMessage", "Student '" + student.getFullName() + "' updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating student: " + e.getMessage());
        }
        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            Student student = studentService.getStudentById(id);
            studentService.deleteStudent(id);
            redirectAttributes.addFlashAttribute("successMessage", "Student '" + student.getFullName() + "' was deleted successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete student: " + e.getMessage());
        }
        return "redirect:/students";
    }
}
