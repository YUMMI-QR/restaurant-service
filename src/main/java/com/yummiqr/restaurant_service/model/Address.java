package com.yummiqr.restaurant_service.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "ADDRESS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "STREET_TYPE", length = 40)
    private String streetType;

    @Column(name = "STREET_NAME", length = 40)
    private String streetName;

    @Column(name = "STREET_NUMBER")
    private Integer streetNumber;

    @Column(name = "ZIP_CODE", length = 6)
    private String zipCode;

    @Column(name = "CITY", length = 40)
    private String city;

    @Column(name = "COUNTRY", length = 40)
    private String country;
}
