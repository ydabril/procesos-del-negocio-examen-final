package com.process.shop.service;

import com.process.shop.model.Address;
import com.process.shop.model.User;
import com.process.shop.model.enums.DocumentType;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User userUpdated, Long id) {
        return null;
    }

    @Override
    public User getUserById(Long id) {
        return User.builder()
                .fullName("Pepito perez")
                .document("123")
                .documentType(DocumentType.CC)
                .address(List.of(
                        Address.builder()
                        .avenue("1212")
                        .neighborhood("12131")
                        .build(),
                        Address.builder()
                                .avenue("43434343")
                                .neighborhood("DSDDSDSDSD")
                                .build()
                        )
                )
                .build();
    }

    @Override
    public List<User> findAllUsers() {
        return null;
    }
}
