package com.yummiqr.restaurant_service.repository;

import com.yummiqr.restaurant_service.model.Address;
import com.yummiqr.restaurant_service.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByStreetTypeAndStreetNameAndStreetNumberAndZipCodeAndCityAndCountry(String streetType,
                                                                                                 String streetName,
                                                                                                 Integer streetNumber,
                                                                                                 String zipCode,
                                                                                                 String city,
                                                                                                 String country);

}
