package com.example.demo.Repository;

import com.example.demo.Entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Address Repository
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
