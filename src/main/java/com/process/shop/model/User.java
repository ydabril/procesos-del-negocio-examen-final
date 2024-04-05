package com.process.shop.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String document;
    private String documentType;
    private LocalDate birthDay;
    private String phoneNumber;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Address> address;
}
