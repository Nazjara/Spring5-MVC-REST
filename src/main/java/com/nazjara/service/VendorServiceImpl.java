package com.nazjara.service;

import com.nazjara.dto.VendorDTO;
import com.nazjara.dto.VendorListDTO;
import com.nazjara.exception.ResourceNotFoundException;
import com.nazjara.mapper.VendorMapper;
import com.nazjara.model.Vendor;
import com.nazjara.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private VendorMapper vendorMapper;
    private VendorRepository vendorRepository;

    @Autowired
    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public VendorListDTO getVendors() {
        VendorListDTO vendors = new VendorListDTO();
        vendors.setVendors(vendorRepository.findAll().stream()
                .map(vendor -> vendorMapper.MAPPER.vendorToVendorDTO(vendor)).collect(Collectors.toList()));

        return vendors;
    }

    @Override
    public VendorDTO getVendorByName(String name) {
        return vendorMapper.MAPPER.vendorToVendorDTO(vendorRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Vendor with name = %s not found", name))));
    }

    @Override
    public VendorDTO createVendor(VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
        return vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
    }

    @Override
    public VendorDTO replaceVendor(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
        vendor.setId(id);

        return vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
    }

    @Override
    public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id).map(vendor -> {
            if (vendorDTO.getName() != null) {
                vendor.setName(vendorDTO.getName());
            }

            return vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
        }).orElseThrow(() -> new ResourceNotFoundException(String.format("Vendor with id = %d not found", id)));
    }

    @Override
    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }
}