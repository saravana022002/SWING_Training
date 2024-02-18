package net.saravana.controllers;

import lombok.RequiredArgsConstructor;
import net.saravana.entities.Vendor;
import net.saravana.services.VendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v2/")
public class VendorController {

    private final VendorService vendorService;


    // get all vendors
    @GetMapping("/vendors")
    public List<Vendor> getAllVendors(){
        return vendorService.getAllVendors();
    }


    // create vendor rest api
    @PostMapping("/vendors")
    public Vendor createVendor(@RequestBody Vendor vendor) {
        return vendorService.createVendor(vendor);
    }

    // get vendor by id rest api
    @GetMapping("/vendors/{id}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable Long id) {
        Vendor vendor = vendorService.getVendorById(id);
        return ResponseEntity.ok(vendor);
    }

    // update vendor rest api

    @PutMapping("/vendors/{id}")
    public ResponseEntity<Vendor> updateVendor(@PathVariable Long id, @RequestBody Vendor vendorDetails){
        Vendor vendor = vendorService.getVendorById(id);

        vendor.setFirstName(vendorDetails.getFirstName());
        vendor.setLastName(vendorDetails.getLastName());
        vendor.setEmailId(vendorDetails.getEmailId());

        Vendor updatedVendor = vendorService.createVendor(vendor);
        return ResponseEntity.ok(updatedVendor);
    }

    // delete vendor rest api
    @DeleteMapping("/vendors/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteVendor(@PathVariable Long id){
        Vendor vendor = vendorService.getVendorById(id);

        vendorService.deleteVendor(vendor);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
