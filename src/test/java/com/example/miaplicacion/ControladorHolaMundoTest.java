package com.example.miaplicacion;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControladorHolaMundoTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHolaMundo() throws Exception {
        mockMvc.perform(get("/hola"))
                .andExpect(status().isOk())
                .andExpect(content().string("¡Hola, este es mi primer proyecto :)!"));
    }
}