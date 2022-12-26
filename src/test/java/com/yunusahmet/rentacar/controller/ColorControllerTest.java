package com.yunusahmet.rentacar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunusahmet.rentacar.business.ColorManager;
import com.yunusahmet.rentacar.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ColorController.class)
@ContextConfiguration(classes = ColorController.class)
public class ColorControllerTest {

    @MockBean
    private ColorManager colorManager;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testCreateColor_whenColorRequestIsExistAndValid_shouldReturnColorDtoAndCreate() throws Exception {
        CreateColorRequest createColorRequest = new CreateColorRequest
                (
                        "pink"
                );

        ColorDto colorDto = new ColorDto
                (
                        1, "pink"
                );


        when(colorManager.createColor(createColorRequest)).thenReturn(colorDto);

        this.mockMvc.perform(post("/color")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(createColorRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.colorId").exists())
                .andExpect(jsonPath("$.colorName").value("pink"));


        Mockito.verify(colorManager).createColor(createColorRequest);
    }

    @Test
    public void testDeleteColor_whenColorIdExist_shouldReturnOK() throws Exception {

        int colorId = 1;


        this.mockMvc.perform(delete("/color/" + colorId))

                .andExpect(status().isOk());


    }

    @Test
    public void testUpdateColor_whenColorIdExist_shouldReturnColorDtoAndCreate() throws Exception {

        UpdateColorRequest updateColorRequest = new UpdateColorRequest(1, "pink");

        ColorDto colorDto = new ColorDto
                (
                        1, "pink"
                );

        when(colorManager.updateColor(updateColorRequest)).thenReturn(colorDto);

        this.mockMvc.perform(put("/color")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(updateColorRequest))
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.colorId").value(1))
                .andExpect(jsonPath("$.colorName").value("pink"));

        verify(colorManager).updateColor(updateColorRequest);
    }

}