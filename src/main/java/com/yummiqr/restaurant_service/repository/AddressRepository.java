package com.yummiqr.restaurant_service.repository;

import com.yummiqr.restaurant_service.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
