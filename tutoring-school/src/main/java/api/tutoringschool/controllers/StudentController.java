package api.tutoringschool.controllers;

import java.util.List;
import java.util.UUID;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.tutoringschool.dtos.student.StudentDTO;
import api.tutoringschool.model.Student;
import api.tutoringschool.services.StudentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService service;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody @Valid StudentDTO studentDTO) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createStudent(studentDTO));
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllSchools() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudent(@PathVariable(value = "id") UUID id) {
        return service.getStudent(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@PathVariable(value = "id") UUID id,
            @RequestBody @Valid StudentDTO studentData) {
        return service.updateStudent(id, studentData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable(value = "id") UUID id) {
        return service.deleteStudent(id);
    }
}