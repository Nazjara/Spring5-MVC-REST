package com.nazjara.service;

import com.nazjara.dto.VendorDTO;
import com.nazjara.dto.VendorListDTO;

public interface VendorService {
    VendorListDTO getVendors();
    VendorDTO getVendorByName(String name);
    VendorDTO createVendor(VendorDTO vendorDTO);
    VendorDTO replaceVendor(Long id, VendorDTO vendorDTO);
    VendorDTO updateVendor(Long id, VendorDTO vendorDTO);
    void deleteVendor(Long id);
}
