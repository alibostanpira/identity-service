package org.abpira.identity.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "addresses", indexes = {@Index(name = "idx_user_id", columnList = "userId")})
public class Address extends BaseEntity {

    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false) // Renamed column for consistency
    private User user;
}
