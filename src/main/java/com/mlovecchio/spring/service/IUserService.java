package com.mlovecchio.spring.service;


import com.mlovecchio.spring.Payload.TransactionRequest;
import com.mlovecchio.spring.Payload.UserRequest;
import com.mlovecchio.spring.model.User;
import org.springframework.http.HttpStatus;


import java.util.List;

public interface IUserService {

    List<User> findAll();
    User findById(long id);
    User findUserByName(String name);
    void save(User user);
    void deleteById(long id);

    boolean validateUserExists(long id);
    void saveUser (UserRequest userRequest);
    void updateUser (UserRequest userRequest, long id);
    HttpStatus saveTransactionUserGame (TransactionRequest transactionRequest);
    void saveTransactionUserMod (TransactionRequest transactionRequest);
}
