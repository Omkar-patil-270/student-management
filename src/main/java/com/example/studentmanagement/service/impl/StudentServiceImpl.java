package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.repository.StudentRepository;
import com.example.studentmanagement.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + id));
    }

    @Override
    public Student saveStudent(Student student) {
        if (student.getEmail() != null && isEmailTaken(student.getEmail(), student.getId())) {
            throw new IllegalArgumentException("Email '" + student.getEmail() + "' is already registered.");
        }
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long id, Student studentDetails) {
        Student existingStudent = getStudentById(id);

        if (isEmailTaken(studentDetails.getEmail(), id)) {
            throw new IllegalArgumentException("Email '" + studentDetails.getEmail() + "' is already used by another student.");
        }

        existingStudent.setFirstName(studentDetails.getFirstName());
        existingStudent.setLastName(studentDetails.getLastName());
        existingStudent.setEmail(studentDetails.getEmail());
        existingStudent.setDepartment(studentDetails.getDepartment());
        existingStudent.setGpa(studentDetails.getGpa());
        existingStudent.setPhone(studentDetails.getPhone());

        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> searchStudents(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return studentRepository.findAll();
        }
        return studentRepository.searchStudents(keyword.trim());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isEmailTaken(String email, Long currentStudentId) {
        if (email == null) return false;
        if (currentStudentId == null) {
            return studentRepository.existsByEmail(email.trim());
        }
        return studentRepository.existsByEmailAndIdNot(email.trim(), currentStudentId);
    }

    @Override
    @Transactional(readOnly = true)
    public long countStudents() {
        return studentRepository.count();
    }
}
