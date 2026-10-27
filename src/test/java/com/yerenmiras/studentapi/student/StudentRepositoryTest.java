package com.yerenmiras.studentapi.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class StudentRepositoryTest {

    @Autowired
    private StudentRepository repository;

    @Test
    void savesAndFindsStudentBySurname() {
        repository.save(new Student("Miras", "Yeren", "IT2-2308", "miras@example.com"));

        List<Student> found = repository.findByLastNameIgnoreCase("yeren");

        assertThat(found).hasSize(1);
        assertThat(found.get(0).getFirstName()).isEqualTo("Miras");
        assertThat(found.get(0).getId()).isNotNull();
    }
}
