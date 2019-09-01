package com.nazjara.controller;

import com.nazjara.dto.VendorDTO;
import com.nazjara.dto.VendorListDTO;
import com.nazjara.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vendors")
public class VendorController {

    private final VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    public ResponseEntity<VendorListDTO> list() {
        return new ResponseEntity<>(vendorService.getVendors(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<VendorDTO> getByName(@PathVariable String name) {
        return new ResponseEntity<>(vendorService.getVendorByName(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VendorDTO> create(@RequestBody VendorDTO vendorDTO) {
        return new ResponseEntity<>(vendorService.createVendor(vendorDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendorDTO> replace(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return new ResponseEntity<>(vendorService.replaceVendor(id, vendorDTO), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VendorDTO> update(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return new ResponseEntity<>(vendorService.updateVendor(id, vendorDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        vendorService.deleteVendor(id);

        return ResponseEntity.ok().build();
    }
}