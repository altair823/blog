package org.altair823.blog;

import org.altair823.blog.web.IndexController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = IndexController.class)
class IndexControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void hello() throws Exception {
        String expected = "hello, world!";
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }
}