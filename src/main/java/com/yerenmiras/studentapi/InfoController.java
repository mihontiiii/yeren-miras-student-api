package com.yerenmiras.studentapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/** Shows the student's name and individual data (Spring Boot application requirement). */
@RestController
public class InfoController {

    private final String fullName;
    private final String group;
    private final String studentId;
    private final String version;

    public InfoController(@Value("${app.student.full-name}") String fullName,
                          @Value("${app.student.group}") String group,
                          @Value("${app.student.id}") String studentId,
                          @Value("${app.version}") String version) {
        this.fullName = fullName;
        this.group = group;
        this.studentId = studentId;
        this.version = version;
    }

    @GetMapping("/api/info")
    public Map<String, String> info() {
        Map<String, String> info = new LinkedHashMap<>();
        info.put("application", "Student API");
        info.put("student", fullName);
        info.put("group", group);
        info.put("studentId", studentId);
        info.put("version", version);
        return info;
    }
}
