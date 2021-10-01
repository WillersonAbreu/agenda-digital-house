package br.com.santander.agenda.repository;

import br.com.santander.agenda.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {}
