package com.example.HelloSQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cloudvendor")
public class CloudVController {

    @Autowired
    public CloudVRepository vendorRepository;


    @GetMapping("/{vendorId}")
    public Optional<CloudVModel> getDetails(@PathVariable Long vendorId) {
        return vendorRepository.findById(vendorId); // Retrieve the vendor by ID using the repository
    }

    @PostMapping("/addVendor")
    public String createDetails(@RequestBody CloudVModel vendor) {
        vendorRepository.save(vendor); // Save the vendor object using the repository
        return "Cloud Vendor Created Successfully";
    }


    @PutMapping("/{vendorId}")
    public String updateDetails(@PathVariable Long vendorId, @RequestBody CloudVModel vendor) {
        vendor.setVendorId(vendorId); // Ensure the vendorId is set for the update
        vendorRepository.save(vendor); // Save the updated vendor object using the repository
        return "Cloud Vendor Updated Successfully";
    }

    @DeleteMapping("/{vendorId}")
    public String deleteDetails(@PathVariable Long vendorId) {
        vendorRepository.deleteById(vendorId); // Delete the vendor object by ID using the repository
        return "Cloud Vendor Deleted Successfully";
    }
}
