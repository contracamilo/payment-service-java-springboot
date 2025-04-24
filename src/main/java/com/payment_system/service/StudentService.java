package com.payment_system.service;

import com.payment_system.model.Student;
import com.payment_system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentByCodigo(String codigo) {
        return studentRepository.findByCodigo(codigo);
    }

    public List<Student> getStudentsByPrograma(String programaId) {
        return studentRepository.findByProgramaId(programaId);
    }
} 