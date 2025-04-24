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

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(String codigo, Student studentDetails) {
        Student student = studentRepository.findByCodigo(codigo);
        if (student != null) {
            student.setNombre(studentDetails.getNombre());
            student.setApellido(studentDetails.getApellido());
            student.setProgramaId(studentDetails.getProgramaId());
            student.setFoto(studentDetails.getFoto());
            return studentRepository.save(student);
        }
        return null;
    }

    public boolean deleteStudent(String codigo) {
        Student student = studentRepository.findByCodigo(codigo);
        if (student != null) {
            studentRepository.delete(student);
            return true;
        }
        return false;
    }
} 