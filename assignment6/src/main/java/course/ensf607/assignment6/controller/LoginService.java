package course.ensf607.assignment6.controller;

import course.ensf607.assignment6.entity.User;
import course.ensf607.assignment6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Optional<User> authenticate(String username, String password){
        Optional<User> foundUser = this.userRepository.findByUserName(username);
        if(foundUser.get().getPassword().equals(password)){
            return foundUser;
        }
        else{
            return Optional.empty();
//            throw new InputMismatchException("Username or password is wrong.");
//
        }
    }


}
