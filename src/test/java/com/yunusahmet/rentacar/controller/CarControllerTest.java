package com.yunusahmet.rentacar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.yunusahmet.rentacar.business.CarManager;
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

import java.util.List;



import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
@ContextConfiguration(classes = CarController.class)
public class CarControllerTest {

    @MockBean
    private CarManager carManager;

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
    public void testCreateCar_whenCarRequestIsExistAndValid_shouldReturnCarDtoAndCreate() throws Exception {
        CreateCarRequest createCarRequest = new CreateCarRequest
                (
                        "Togg", 50000L, "2023", 1, List.of(1, 2)
                );

        CarDto carDto = new CarDto
                (
                        1, "Togg", 50000L, "2023", null, null, null
                );


        when(carManager.createCar(createCarRequest)).thenReturn(carDto);

        this.mockMvc.perform(post("/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(createCarRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.carId").exists())
                .andExpect(jsonPath("$.carName").value("Togg"))
                .andExpect(jsonPath("$.dailyPrice").value(50000L))
                .andExpect(jsonPath("$.productYear").value("2023"))
                .andExpect(jsonPath("$.brand").isEmpty())
                .andExpect(jsonPath("$.image").isEmpty())
                .andExpect(jsonPath("$.carColors").isEmpty());


        Mockito.verify(carManager).createCar(createCarRequest);
    }

    @Test
    public void testDeleteCar_whenCarIdExist_shouldReturnOK() throws Exception {

        int carId = 1;


        this.mockMvc.perform(delete("/car/" + carId))

                .andExpect(status().isOk());


    }

    @Test
    public void testUpdateCar_whenCarIdExist_shouldReturnCarDtoAndCreate() throws Exception {

        UpdateCarRequest updateCarRequest = new UpdateCarRequest
                (
                        1, "Togg", 50000L, "2023", 1, List.of(1, 2)
                );

        CarDto carDto = new CarDto
                (
                        1, "Togg", 50000L, "2023", null, null, null
                );

        when(carManager.update(updateCarRequest)).thenReturn(carDto);

        this.mockMvc.perform(put("/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(updateCarRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.carId").exists())
                .andExpect(jsonPath("$.carName").value("Togg"))
                .andExpect(jsonPath("$.dailyPrice").value(50000L))
                .andExpect(jsonPath("$.productYear").value("2023"))
                .andExpect(jsonPath("$.brand").isEmpty())
                .andExpect(jsonPath("$.image").isEmpty())
                .andExpect(jsonPath("$.carColors").isEmpty());


        Mockito.verify(carManager).update(updateCarRequest);
    }

    @Test
    public void testGetAllCarByBrandId_whenBrandExist_shouldReturnListOfCarsAndOK() throws Exception {

        int brandId = 1;

        List<BrandCarDto> cars = List.of(new BrandCarDto
                (
                        1, "Togg", 50000L, "2023", null
                ));

        when(carManager.getCarsByBrandId(brandId)).thenReturn(cars);

        this.mockMvc.perform(get("/car/" + brandId))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].carId").value(1))
                .andExpect(jsonPath("$[0].carName").value("Togg"))
                .andExpect(jsonPath("$[0].dailyPrice").value(50000L))
                .andExpect(jsonPath("$[0].productYear").value("2023"))
                .andExpect(jsonPath("$[0].carColors").isEmpty());

        verify(carManager).getCarsByBrandId(brandId);
    }

    @Test
    public void testGetAllCar_shouldReturnListOfCarsAndOK() throws Exception {

        List<CarDto> cars = List.of
                (
                        new CarDto(1, "Togg", 50000L, "2023", null, null, null)

                        );

        when(carManager.getAllCar()).thenReturn(cars);

        this.mockMvc.perform(get("/car"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].carId").value(1))
                .andExpect(jsonPath("$[0].carName").value("Togg"))
                .andExpect(jsonPath("$[0].dailyPrice").value(50000L))
                .andExpect(jsonPath("$[0].productYear").value("2023"))
                .andExpect(jsonPath("$[0].brand").isEmpty())
                .andExpect(jsonPath("$[0].image").isEmpty())
                .andExpect(jsonPath("$[0].carColors").isEmpty());


        verify(carManager).getAllCar();
    }


}