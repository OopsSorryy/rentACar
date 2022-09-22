//package com.yunusAhmet.rentACar.Entity;
//
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "rent_a_car_ınfo")
//public class RentACarInfo {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int rentACarInfoId;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "userId")
//    private User user;
//
//    private String rentDate;
//
//    private String returnDate;//araba teslım edılmemısse null olacak
//
//    public void addUserToRentACarInfo(User user){
//        this.user=user;
//    }
//
//
//}
