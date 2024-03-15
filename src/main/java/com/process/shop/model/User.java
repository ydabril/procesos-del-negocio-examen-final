package com.process.shop.model;
import com.process.shop.model.enums.DocumentType;
import lombok.*;

import java.util.Date;
import java.util.List;


@Data
@Builder
public class User {
    private String fullName;
    private String document;
    private DocumentType documentType;
    private Date birthDay;
    private String phoneNumber;
    private String email;
    private String password;
    private List<Address> address;
}
