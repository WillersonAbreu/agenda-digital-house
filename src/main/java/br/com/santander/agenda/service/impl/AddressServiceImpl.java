package br.com.santander.agenda.service.impl;

import br.com.santander.agenda.model.Address;
import br.com.santander.agenda.repository.AddressRepository;
import br.com.santander.agenda.service.AddressService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
  private AddressRepository addressRepository;

  @Autowired
  public AddressServiceImpl(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }

  @Override
  public List<Address> getAll() {
    return this.addressRepository.findAll();
  }
}
