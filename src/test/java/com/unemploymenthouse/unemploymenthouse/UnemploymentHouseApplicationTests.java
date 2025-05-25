package com.unemploymenthouse.unemploymenthouse;

import com.unemploymenthouse.unemploymenthouse.web.MainController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = UnemploymentHouseApplicationTests.class)
class UnemploymentHouseApplicationTests {

    @InjectMocks
    private MainController controller;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void defaultMessage() throws Exception {
        this.mockMvc.perform(get("/index")).andDo(print()).andExpect(status().is(401));
    }
}
