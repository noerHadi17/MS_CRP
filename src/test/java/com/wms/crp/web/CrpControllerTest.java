package com.wms.crp.web;

import com.wms.crp.dto.CrpDtos.QuestionListResponse;
import com.wms.crp.service.AnswerService;
import com.wms.crp.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CrpController.class)
class CrpControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private QuestionService questionService;
    @MockBean private AnswerService answerService;
    @MockBean private MessageSource messageSource;

    @Test
    void questionsReturnsWrapper() throws Exception {
        Mockito.when(questionService.getActive()).thenReturn(QuestionListResponse.builder().items(java.util.List.of()).build());
        Mockito.when(messageSource.getMessage(Mockito.anyString(), Mockito.isNull(), Mockito.any())).thenReturn("ok");
        mockMvc.perform(get("/v1/crp/questions").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.items").isArray());
    }
}

