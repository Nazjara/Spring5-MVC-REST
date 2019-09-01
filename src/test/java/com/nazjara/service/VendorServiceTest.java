package com.nazjara.service;

import com.nazjara.dto.VendorDTO;
import com.nazjara.dto.VendorListDTO;
import com.nazjara.exception.ResourceNotFoundException;
import com.nazjara.mapper.VendorMapper;
import com.nazjara.model.Vendor;
import com.nazjara.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class VendorServiceTest {
    public static final String NAME_1 = "Vendor1";
    public static final long ID_1 = 1L;
    public static final String NAME_2 = "Vendor2";
    public static final long ID_2 = 1L;

    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    @Before
    public void setUp() {
        vendorService = new VendorServiceImpl(VendorMapper.MAPPER, vendorRepository);
    }

    @Test
    public void getVendorById() {
        //given
        Vendor vendor = getVendor1();

        //mockito BDD syntax
        given(vendorRepository.findByName("Vendor1")).willReturn(Optional.of(vendor));

        //when
        VendorDTO vendorDTO = vendorService.getVendorByName("Vendor1");

        //then
        then(vendorRepository).should(times(1)).findByName("Vendor1");

        //JUnit Assert that with matchers
        assertThat(vendorDTO.getName(), is(equalTo(NAME_1)));
    }



    @Test(expected = ResourceNotFoundException.class)
    public void getVendorByIdNotFound() {
        //given
        //mockito BBD syntax since mockito 1.10.0
        given(vendorRepository.findByName("Vendor1")).willReturn(Optional.empty());

        //when
        vendorService.getVendorByName("Vendor1");

        //then
        then(vendorRepository).should(times(1)).findByName("Vendor1");

    }

    @Test
    public void getAllVendors() {
        //given
        List<Vendor> vendors = Arrays.asList(getVendor1(), getVendor2());
        given(vendorRepository.findAll()).willReturn(vendors);

        //when
        VendorListDTO vendorListDTO = vendorService.getVendors();

        //then
        then(vendorRepository).should(times(1)).findAll();
        assertThat(vendorListDTO.getVendors().size(), is(equalTo(2)));
    }

    @Test
    public void createNewVendor() {
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(ID_1);
        vendorDTO.setName(NAME_1);

        Vendor vendor = getVendor1();

        given(vendorRepository.save(vendor)).willReturn(vendor);

        //when
        vendorService.createVendor(vendorDTO);

        //then
        // 'should' defaults to times = 1
        then(vendorRepository).should().save(vendor);
    }

    @Test
    public void saveVendorByDTO() {
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(ID_1);
        vendorDTO.setName(NAME_1);

        Vendor vendor = getVendor1();

        given(vendorRepository.save(vendor)).willReturn(vendor);

        //when
        vendorService.replaceVendor(ID_1, vendorDTO);

        //then
        // 'should' defaults to times = 1
        then(vendorRepository).should().save(vendor);
    }

    @Test
    public void patchVendor() {
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(ID_1);
        vendorDTO.setName(NAME_1);

        Vendor vendor = getVendor1();

        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));
        given(vendorRepository.save(vendor)).willReturn(vendor);

        //when
        vendorService.updateVendor(ID_1, vendorDTO);

        //then
        // 'should' defaults to times = 1
        then(vendorRepository).should().save(vendor);
        then(vendorRepository).should(times(1)).findById(anyLong());
    }

    @Test
    public void deleteVendorById() {
        //when
        vendorService.deleteVendor(1L);

        //then
        then(vendorRepository).should().deleteById(anyLong());
    }

    private Vendor getVendor1() {
        Vendor vendor = new Vendor();
        vendor.setName(NAME_1);
        vendor.setId(ID_1);
        return vendor;
    }

    private Vendor getVendor2() {
        Vendor vendor = new Vendor();
        vendor.setName(NAME_2);
        vendor.setId(ID_2);
        return vendor;
    }
}