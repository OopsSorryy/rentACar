package com.yunusahmet.rentacar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunusahmet.rentacar.business.BrandManager;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BrandController.class)
@ContextConfiguration(classes = BrandController.class)
public class BrandControllerTest {

    @MockBean
    private BrandManager brandManager;

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
    public void testCreateBrand_whenCustomerRequestIsExistAndValid_shouldReturnCustomerDto() throws Exception {
        CreateBrandRequest createBrandRequest = new CreateBrandRequest
                (
                        "a8"
                );

        BrandDto brandDto = new BrandDto
                (
                        1, "a8"
                );


        when(brandManager.createBrand(createBrandRequest)).thenReturn(brandDto);

        this.mockMvc.perform(post("/brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(createBrandRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.brandId").exists())
                .andExpect(jsonPath("$.brandName").value("a8"));


        Mockito.verify(brandManager).createBrand(createBrandRequest);
    }

    @Test
    public void testDeleteBrand_whenBrandIdExist_shouldReturnOK() throws Exception {

        int brandId = 1;


        this.mockMvc.perform(delete("/brand/" + brandId))

                .andExpect(status().isOk());


    }

    @Test
    public void testUpdateBrand_whenBrandIdExist_shouldReturnBrandDtoAndCreate() throws Exception {

        UpdateBrandRequest updateBrandRequest = new UpdateBrandRequest(1, "a9");

        BrandDto brandDto = new BrandDto(1, "a9");

        when(brandManager.updateBrand(updateBrandRequest)).thenReturn(brandDto);

        this.mockMvc.perform(put("/brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(updateBrandRequest))
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.brandName").value("a9"));

        verify(brandManager).updateBrand(updateBrandRequest);
    }


}