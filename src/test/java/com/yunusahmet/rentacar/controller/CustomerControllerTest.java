package com.yunusahmet.rentacar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunusahmet.rentacar.business.CustomerManager;
import com.yunusahmet.rentacar.dto.CreateCustomerRequest;
import com.yunusahmet.rentacar.dto.CustomerDto;
import com.yunusahmet.rentacar.dto.UpdateCustomerRequest;

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

@WebMvcTest(CustomerController.class)
@ContextConfiguration(classes = CustomerController.class)
public class CustomerControllerTest {

    @MockBean
    private CustomerManager customerManager;

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
    public void testCreateCustomer_whenCustomerRequestIsExistAndValid_shouldReturnCustomerDto() throws Exception {
        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest
                (
                        "Ahmet", "Dayi", "ahmetdayı31@gmail.com",
                        "Ahmet.123", "Ahmet.123"
                );

        CustomerDto customerDto = new CustomerDto
                (
                        1, "Ahmet", "Dayi", "ahmetdayı31@gmail.com"
                );


        when(customerManager.createCustomer(createCustomerRequest)).thenReturn(customerDto);

        this.mockMvc.perform(post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(createCustomerRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customerId").exists())
                .andExpect(jsonPath("$.firstName").value("Ahmet"))
                .andExpect(jsonPath("$.lastName").value("Dayi"))
                .andExpect(jsonPath("$.email").value("ahmetdayı31@gmail.com"));

        Mockito.verify(customerManager).createCustomer(createCustomerRequest);
    }

    @Test
    public void testDeleteCustomer_whenCustomerIdExists_shouldDeleteCustomerAndReturnOk() throws Exception {

        int customerId = 1;

        this.mockMvc.perform(delete("/customer/" + customerId))

                .andExpect(status().isOk());


    }

    @Test
    public void testUpdateCustomer_whenCustomerIdExists_shouldReturnCustomerDto() throws Exception {

        UpdateCustomerRequest updateCustomerRequest = new UpdateCustomerRequest
                (
                        1, "Ahmet", "Dayi",
                        "Ahmet.123", "Ahmet.123"
                );

        CustomerDto customerDto = new CustomerDto
                (
                        1, "Ahmet", "Dayi", "ahmetdayı31@gmail.com"
                );
        when(customerManager.updateCustomer(updateCustomerRequest)).thenReturn(customerDto);

        this.mockMvc.perform(put(("/customer"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(updateCustomerRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customerId").value(1))
                .andExpect(jsonPath("$.firstName").value("Ahmet"))
                .andExpect(jsonPath("$.lastName").value("Dayi"))
                .andExpect(jsonPath("$.email").value("ahmetdayı31@gmail.com"));


        verify(customerManager).updateCustomer(updateCustomerRequest);
    }
}