package com.yunusAhmet.rentACar.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Customer {

    // docker,aws,integration test,auth2,kafka,rabbit,redis,microservice
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String matchingPassword;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY,mappedBy = "customer")
    private Rental rental;

    public Customer(String firstName, String lastName, String email, String password,String matchingPassword) {
        this.matchingPassword=matchingPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

    }

    public Customer(int customerId, String firstName, String lastName, String password, String matchingPassword) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.matchingPassword = matchingPassword;
    }

    public Customer(int customerId, String firstName, String lastName, String email, String password, String matchingPassword) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.matchingPassword = matchingPassword;
    }

    public Customer(int customerId, String firstName, String lastName, String email, String password, String matchingPassword, Role role) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.role = role;
    }

    public Customer(String firstName, String lastName, String email, String password, String matchingPassword, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.role = role;
    }
}
