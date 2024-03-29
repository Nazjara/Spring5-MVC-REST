package com.nazjara.controller;

import com.nazjara.model.CustomerDTO;
import com.nazjara.model.CustomerListDTO;
import com.nazjara.exception.ResourceNotFoundException;
import com.nazjara.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static com.nazjara.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void testListCustomers() throws Exception {
        CustomerListDTO customerListDTO = new CustomerListDTO();

        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstName("Michale");
        customer1.setLastName("Weston");

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setFirstName("Sam");
        customer2.setLastName("Axe");

        customerListDTO.getCustomers().addAll(Arrays.asList(customer1, customer2));

        when(customerService.getCustomers()).thenReturn(customerListDTO);

        mockMvc.perform(get("/api/v1/customers/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstName("Michale");
        customer1.setLastName("Weston");

        when(customerService.getCustomerById(anyLong())).thenReturn(customer1);

        mockMvc.perform(get("/api/v1/customers/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Michale")));
    }

    @Test
    public void createNewCustomer() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName("Fred");
        customer.setLastName("Flintstone");

        when(customerService.createCustomer(any())).thenReturn(customer);

        mockMvc.perform(post("/api/v1/customers")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("Fred")));
    }

    @Test
    public void testReplaceCustomer() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName("Fred");
        customer.setLastName("Flintstone");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstName(customer.getFirstName());
        returnDTO.setLastName(customer.getLastName());

        when(customerService.replaceCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(put("/api/v1/customers/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Fred")))
                .andExpect(jsonPath("$.lastName", equalTo("Flintstone")));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName("Fred");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstName(customer.getFirstName());
        returnDTO.setLastName("Flintstone");

        when(customerService.updateCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(patch("/api/v1/customers/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Fred")))
                .andExpect(jsonPath("$.lastName", equalTo("Flintstone")));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomer(anyLong());
    }

    @Test
    public void testNotFoundException() throws Exception {
        when(customerService.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get("/api/v1/customers/1000")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}