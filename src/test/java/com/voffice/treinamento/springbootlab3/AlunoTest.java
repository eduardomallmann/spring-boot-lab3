package com.voffice.treinamento.springbootlab3;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AlunoController.class)
public class AlunoTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper jsonParser;

    @Test
    public void test_listAlunos() throws Exception {
        mockMvc.perform(get("/aluno"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.[0].id", equalTo(1)))
                .andExpect(jsonPath("$.[0].nome", equalTo("João Silva")))
                .andExpect(jsonPath("$.[1].id", equalTo(2)))
                .andExpect(jsonPath("$.[1].nome", equalTo("Maria Silva")));
    }

    @Test
    public void test_createAluno() throws Exception {
        Aluno aluno = new Aluno(6l, "Eduardo Mallmann", 66666, "mallmann.edu@gmail.com");
        mockMvc.perform(post("/aluno").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonParser.writeValueAsString(aluno)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.[3].id", equalTo(6)))
                .andExpect(jsonPath("$.[3].nome", equalTo("Eduardo Mallmann")));
    }

}
