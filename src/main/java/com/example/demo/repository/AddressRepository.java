package com.example.demo.repository;

import com.example.demo.entities.Address;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Address Repository */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

  List<Address> findByCity(String keyword);
}
