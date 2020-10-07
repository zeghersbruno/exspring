package be.abis.it.controller;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import be.abis.controller.AbisController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AbisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AbisController abisController;

    @Test
    public void giveMeSomethingFromCourse() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().is2xxSuccessful())
            .andExpect(view().name("course"))
            .andExpect(model().size(1))
            .andExpect(model().attributeExists("course"));
    }

    @Test
    public void showMeTheLoginForm() throws Exception {
        mockMvc.perform(get("/login"))
            .andExpect(status().is2xxSuccessful())
            .andExpect(view().name("login"))
            .andExpect(model().size(1))
            .andExpect(model().attributeExists("person"));
    }
}
