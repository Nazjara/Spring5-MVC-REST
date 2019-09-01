package com.nazjara.controller;

import com.nazjara.dto.VendorDTO;
import com.nazjara.dto.VendorListDTO;
import com.nazjara.service.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vendors")
@Api(description = "Operations pertaining to vendors")
public class VendorController {

    private final VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    @ApiOperation(value = "View a list of available vendors", response = VendorListDTO.class)
    public ResponseEntity<VendorListDTO> list() {
        return new ResponseEntity<>(vendorService.getVendors(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    @ApiOperation(value = "Get vendor by id", response = VendorDTO.class)
    public ResponseEntity<VendorDTO> getByName(@PathVariable String name) {
        return new ResponseEntity<>(vendorService.getVendorByName(name), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Create new vendor", response = VendorDTO.class)
    public ResponseEntity<VendorDTO> create(@RequestBody VendorDTO vendorDTO) {
        return new ResponseEntity<>(vendorService.createVendor(vendorDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Replace existing vendor", response = VendorDTO.class)
    public ResponseEntity<VendorDTO> replace(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return new ResponseEntity<>(vendorService.replaceVendor(id, vendorDTO), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "Update existing vendor", response = VendorDTO.class)
    public ResponseEntity<VendorDTO> update(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return new ResponseEntity<>(vendorService.updateVendor(id, vendorDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete existing vendor")
    public ResponseEntity delete(@PathVariable Long id) {
        vendorService.deleteVendor(id);

        return ResponseEntity.ok().build();
    }
}