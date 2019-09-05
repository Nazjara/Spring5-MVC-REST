package com.nazjara.mapper;

import com.nazjara.dto.VendorDTO;
import com.nazjara.model.Vendor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VendorMapperTest {

    public static final String NAME = "someName";

    VendorMapper vendorMapper = VendorMapper.MAPPER;

    @Test
    public void vendorToVendorDTO() {
        Vendor vendor = new Vendor();
        vendor.setName(NAME);

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        assertEquals(vendor.getName(), vendorDTO.getName());
    }

    @Test
    public void vendorDTOtoVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        assertEquals(vendorDTO.getName(), vendor.getName());
    }
}