package com.nazjara.controller;

import com.nazjara.dto.VendorDTO;
import com.nazjara.dto.VendorListDTO;
import com.nazjara.service.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static com.nazjara.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {VendorController.class})
public class VendorControllerTest {

    @MockBean //provided by Spring Context
            VendorService vendorService;

    @Autowired
    MockMvc mockMvc; //provided by Spring Context

    VendorDTO vendorDTO_1;
    VendorDTO vendorDTO_2;

    @Before
    public void setUp() {
        vendorDTO_1 = new VendorDTO();
        vendorDTO_1.setName("Vendor1");

        vendorDTO_2 = new VendorDTO();
        vendorDTO_2.setName("Vendor2");
    }

    @Test
    public void getVendorList() throws Exception {
        VendorListDTO vendorListDTO = new VendorListDTO();
        vendorListDTO.setVendors(Arrays.asList(vendorDTO_1, vendorDTO_2));

        given(vendorService.getVendors()).willReturn(vendorListDTO);

        mockMvc.perform(get("/api/v1/vendors")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    public void getVendorById() throws Exception {
        given(vendorService.getVendorByName(anyString())).willReturn(vendorDTO_1);

        mockMvc.perform(get("/api/v1/vendors/Vendor1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())));
    }

    @Test
    public void createNewVendor() throws Exception {
        given(vendorService.createVendor(vendorDTO_1)).willReturn(vendorDTO_1);

        mockMvc.perform(post("/api/v1/vendors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO_1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())));
    }

    @Test
    public void updateVendor() throws Exception {
        given(vendorService.replaceVendor(anyLong(), any(VendorDTO.class))).willReturn(vendorDTO_1);

        mockMvc.perform(put("/api/v1/vendors/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO_1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())));
    }

    @Test
    public void patchVendor() throws Exception {
        given(vendorService.updateVendor(anyLong(), any(VendorDTO.class))).willReturn(vendorDTO_1);

        mockMvc.perform(patch("/api/v1/vendors/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO_1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())));
    }

    @Test
    public void deleteVendor() throws Exception {
        mockMvc.perform(delete("/api/v1/vendors/1"))
                .andExpect(status().isOk());

        then(vendorService).should().deleteVendor(anyLong());
    }
}