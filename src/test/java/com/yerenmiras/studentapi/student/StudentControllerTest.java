package com.yerenmiras.studentapi.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class StudentControllerTest {

    private static final String STUDENT_JSON = """
            {"firstName":"Miras","lastName":"Yeren","groupName":"IT2-2308","email":"miras@example.com"}
            """;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void infoEndpointShowsStudentName() throws Exception {
        mockMvc.perform(get("/api/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.student").value("Miras Yeren"))
                .andExpect(jsonPath("$.studentId").value("41517"));
    }

    @Test
    void createsAndReadsStudent() throws Exception {
        mockMvc.perform(post("/api/students").contentType(MediaType.APPLICATION_JSON).content(STUDENT_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.lastName").value("Yeren"))
                .andExpect(jsonPath("$.id").isNumber());

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Miras"));
    }

    @Test
    void updatesAndDeletesStudent() throws Exception {
        String body = mockMvc.perform(post("/api/students").contentType(MediaType.APPLICATION_JSON).content(STUDENT_JSON))
                .andReturn().getResponse().getContentAsString();
        String id = body.replaceAll(".*\"id\":(\\d+).*", "$1");

        mockMvc.perform(put("/api/students/" + id).contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Miras\",\"lastName\":\"Yeren\",\"groupName\":\"IT2-9999\",\"email\":\"miras@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.groupName").value("IT2-9999"));

        mockMvc.perform(delete("/api/students/" + id)).andExpect(status().isNoContent());
        mockMvc.perform(get("/api/students/" + id)).andExpect(status().isNotFound());
    }

    @Test
    void returns404ForNonexistentStudent() throws Exception {
        mockMvc.perform(get("/api/students/999999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }
}
