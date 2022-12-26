package com.yunusahmet.rentacar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.yunusahmet.rentacar.business.RentalManager;

import com.yunusahmet.rentacar.dto.RentACarRequest;
import com.yunusahmet.rentacar.dto.RentCarDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RentalController.class)
@ContextConfiguration(classes = RentalController.class)
public class RentalControllerTest {

    @MockBean
    private RentalManager rentalManager;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testRentACar_whenRentACarRequestIsExistAndValid_shouldReturnRentCarDtoAndCreate() throws Exception {
        RentACarRequest rentACarRequest = new RentACarRequest
                (
                        LocalDateTime.of(2023, 12, 12, 0,0,0), 1, 1
                );

        RentCarDto rentCarDto = new RentCarDto
                (
                        1, LocalDateTime.now().withNano(0),
                        LocalDateTime.of(2023, 12, 12, 0, 0,0), null, null
                );


        when(rentalManager.rentACar(rentACarRequest)).thenReturn(rentCarDto);

        this.mockMvc.perform(post("/rental")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(rentACarRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.rentalId").exists())
                .andExpect(jsonPath("$.rentDate").exists())
                .andExpect(jsonPath("$.returnDate").exists())
                .andExpect(jsonPath("$.customer").isEmpty())
                .andExpect(jsonPath("$.car").isEmpty());


        verify(rentalManager).rentACar(rentACarRequest);
    }
}
