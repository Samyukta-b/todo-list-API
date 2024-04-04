package com.example.HelloSQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CloudVService {

    @Autowired
    CloudVRepository repository;

    public Iterable<CloudVModel> getAll() {
        return repository.findAll();
    }

    public void addUser(CloudVModel vendor) {
        repository.save(vendor);
    }

    public void updateUser(Long id, CloudVModel vendor) {
        Optional<CloudVModel> query = repository.findById(id);
        if (query.isPresent()) {
            CloudVModel user = query.get();
            user.setVendorName(vendor.getVendorName());
            user.setVendorEmail(vendor.getVendorEmail());
            repository.save(user);
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
