package com.mlovecchio.spring.service;

import com.mlovecchio.spring.Payload.TransactionRequest;
import com.mlovecchio.spring.Payload.UserRequest;
import com.mlovecchio.spring.dao.GameRepository;
import com.mlovecchio.spring.dao.ModRepository;
import com.mlovecchio.spring.dao.UserRepository;
import com.mlovecchio.spring.exceptions.ResourceException;
import com.mlovecchio.spring.model.Game;
import com.mlovecchio.spring.model.Item;
import com.mlovecchio.spring.model.Mod;
import com.mlovecchio.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    ModRepository modRepository;


    /*public User getUser(long id){
        return userRepository.findById(id).get();
    }*/

    /*public List<User> getAll(){
        return (List<User>) userRepository.findAll();
    }*/


    @Override
    public List<User> findAll() {
        List<User> users = (List<User>) userRepository.findAll();
        if (!users.isEmpty())
        {
            return users;
        }
        else {
            throw new ResourceException(HttpStatus.NOT_FOUND, "List is empty.");
        }

    }

    @Override
    public User findById(long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null)
        {
            return user;
        }
        else {
            throw new ResourceException(HttpStatus.NOT_FOUND, "We were unable to find the user.");
        }
    }

    @Override
    public User findUserByName(String name) {
        User user = userRepository.findByName(name);
        if (user != null)
        {
            return user;
        }
        else {
            throw new ResourceException(HttpStatus.NOT_FOUND, "We were unable to find the user.");
        }
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(long id) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).orElse(null);
            user.setActive(false);
            user.setId(id);
            userRepository.save(user);
            throw new ResourceException(HttpStatus.OK, "User deleted successfuly.");
        }
        else
        {
            throw new ResourceException(HttpStatus.NOT_FOUND, "We were unable to find the user.");
        }

    }



    @Override
    public boolean validateUserExists(long id) {
        return userRepository.existsById(id);
    }

    @Override
    public void saveUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setActive(true);
        userRepository.save(user);
        throw new ResourceException(HttpStatus.OK, "User saved successfuly.");
    }

    @Override
    public void updateUser(UserRequest userRequest, long id) {
        if (userRepository.findById(id).isPresent()) {

            User user = userRepository.findById(id).get();
            user.setName(userRequest.getName());
            user.setActive(true);
            userRepository.save(user);
            throw new ResourceException(HttpStatus.OK, "User modified successfuly.");
        }
        else
        {
            throw new ResourceException(HttpStatus.NOT_FOUND, "We were unable to find the user.");
        }

    }



    @Override
    public HttpStatus saveTransactionUserGame(TransactionRequest transactionRequest) {
        User user = userRepository.findById(transactionRequest.getIdUser()).orElse(null);
        Game game = gameRepository.findById(transactionRequest.getIdGame()).orElse(null);

        if(user != null && game != null && user.isActive() && game.isActive())
        {
            user.getItems().add(game);
            userRepository.save(user);
            throw new ResourceException(HttpStatus.OK, "Transaction successful.");

        }
        else
        {
            throw new ResourceException(HttpStatus.NOT_FOUND, "We were unable to find the user/game.");

        }

    }

    @Override
    public void saveTransactionUserMod(TransactionRequest transactionRequest) {

        User user = userRepository.findById(transactionRequest.getIdUser()).orElse(null);
        Mod mod = modRepository.findById(transactionRequest.getIdMod()).orElse(null);

        if(user != null && mod != null && user.isActive() && mod.isActive())
        {
            user.getItems().add(mod);
            userRepository.save(user);
            throw new ResourceException(HttpStatus.OK, "Transaction successful.");
        }
        else
        {
            throw new ResourceException(HttpStatus.NOT_FOUND, "We were unable to find the user/mod.");
        }

    }


}
