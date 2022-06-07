package mk.com.timas.service.impl;

import mk.com.timas.model.Manufacturer;
import mk.com.timas.model.exceptions.ManufacturerNotFoundException;
import mk.com.timas.repository.ManufacturerRepository;
import mk.com.timas.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    public final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return this.manufacturerRepository.findById(id);
    }

    @Override
    public List<Manufacturer> findAll() {
        return this.manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> save(String name, String address) {
        return Optional.of(this.manufacturerRepository.save(new Manufacturer(name, address)));
    }

    @Override
    public Optional<Manufacturer> edit(Long id, String name, String address) {

        Manufacturer manufacturer = this.manufacturerRepository.findById(id)
                .orElseThrow(() -> new ManufacturerNotFoundException(id));

        manufacturer.setName(name);
        manufacturer.setAddress(address);
        return Optional.of(manufacturerRepository.save(manufacturer));
    }

    @Override
    public void deleteById(Long id) {
        this.manufacturerRepository.deleteById(id);
    }
}
