package org.springplayground.error;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ErrorHandlingTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldThrowSystemExceptionWithResulting500Error() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/?exception=SystemException")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.sessionId").doesNotExist())
                .andExpect(jsonPath("$.conversationId").doesNotExist())
                .andExpect(jsonPath("$.traceId").exists())
                .andExpect(jsonPath("$.invocationId").doesNotExist())
                .andExpect(jsonPath("$.errorId").exists())
                .andExpect(jsonPath("$.errorCode").value("sys.1337"))
                .andExpect(jsonPath("$.status").value("500"))
                .andExpect(jsonPath("$.error").value("Internal Server Error"))
                .andExpect(jsonPath("$.message").value("BOOM!"))
                .andExpect(jsonPath("$.path").value("/"));
    }

    @Test
    void shouldThrowApplicationExceptionWithResulting500Error() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/?exception=ApplicationException")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(418))
                .andExpect(jsonPath("$.sessionId").doesNotExist())
                .andExpect(jsonPath("$.conversationId").doesNotExist())
                .andExpect(jsonPath("$.traceId").exists())
                .andExpect(jsonPath("$.invocationId").doesNotExist())
                .andExpect(jsonPath("$.errorId").exists())
                .andExpect(jsonPath("$.errorCode").value("app.0001"))
                .andExpect(jsonPath("$.status").value("418"))
                .andExpect(jsonPath("$.error").value("I'm a teapot"))
                .andExpect(jsonPath("$.message").value("OUCH!"))
                .andExpect(jsonPath("$.path").value("/"));
    }
}
