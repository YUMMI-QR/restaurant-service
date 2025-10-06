package com.yummiqr.restaurant_service.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "RESTAURANT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 40)
    private String name;

    @Column(name = "NIF", length = 40)
    private String nif;

    @Column(name = "EMAIL", length = 40, unique = true)
    private String email;

    @Column(name = "TAX_PERCENT", precision = 5, scale = 2)
    private BigDecimal taxPercent;

    @Column(name = "FEE_PERCENT", precision = 5, scale = 2)
    private BigDecimal feePercent;

    @Column(name = "CREATION_DATE", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RestaurantTable> tables;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categories;
}
