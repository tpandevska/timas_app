package mk.com.timas.service;

import mk.com.timas.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    Optional<Manufacturer> findById(Long id);
    List<Manufacturer> findAll();
    Optional<Manufacturer> save(String name, String address);
    Optional<Manufacturer> edit(Long id,String name, String address);
    void deleteById(Long id);

}
