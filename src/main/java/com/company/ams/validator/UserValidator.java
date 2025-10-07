package com.company.ams.validator;

import com.company.ams.entity.UserEntity;
import com.company.ams.repository.UserDetailsRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidator {

    @Autowired
    private UserDetailsRepository userRepository;

    public void validateRegisterRequest(UserEntity request) throws BadRequestException {
        UserEntity users = userRepository.findByUsername(request.getUsername());

        if(users != null){
            throw new BadRequestException("User already exist");
        }
        UserEntity users1 = userRepository.findByEmail(request.getEmail());

        if(users1 != null){
            throw new BadRequestException("Email already exist");
        }
    }

}
