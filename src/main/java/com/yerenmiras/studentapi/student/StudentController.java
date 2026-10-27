package com.yerenmiras.studentapi.student;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Student> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@Valid @RequestBody Student student) {
        student.setId(null);
        return repository.save(student);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @Valid @RequestBody Student update) {
        Student student = repository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        student.setFirstName(update.getFirstName());
        student.setLastName(update.getLastName());
        student.setGroupName(update.getGroupName());
        student.setEmail(update.getEmail());
        return repository.save(student);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
