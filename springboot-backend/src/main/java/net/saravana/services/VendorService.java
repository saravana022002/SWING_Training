package net.saravana.services;

import net.saravana.entities.Vendor;
import net.saravana.exceptions.ResourceNotFoundException;
import net.saravana.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {
    @Autowired
    private final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public List<Vendor> getAllVendors(){
        return vendorRepository.findAll();
    }
    public Vendor createVendor(Vendor vendor){
        return  vendorRepository.save(vendor);
    }

    public Vendor getVendorById(Long id){
        return vendorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not exist with id :" + id));
    }
    public void deleteVendor(Vendor vendor){
        vendorRepository.delete(vendor);
    }
}
