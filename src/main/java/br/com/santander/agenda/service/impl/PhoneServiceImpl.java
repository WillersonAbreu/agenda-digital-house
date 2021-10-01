package br.com.santander.agenda.service.impl;

import br.com.santander.agenda.model.Phone;
import br.com.santander.agenda.repository.PhoneRepository;
import br.com.santander.agenda.service.PhoneService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PhoneServiceImpl implements PhoneService {
  private PhoneRepository phoneRepository;

  public PhoneServiceImpl(PhoneRepository phoneRepository) {
    this.phoneRepository = phoneRepository;
  }

  @Override
  public List<Phone> getAllPhones() {
    return this.phoneRepository.findAll();
  }
}
