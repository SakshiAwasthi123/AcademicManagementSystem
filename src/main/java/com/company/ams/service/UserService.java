package com.company.ams.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.ams.dto.request.LoginRequest;
import com.company.ams.entity.UserEntity;
import com.company.ams.exceptions.AMSException;
import com.company.ams.repository.UserDetailsRepository;
import com.company.ams.utils.JwtUtil;
import com.company.ams.validator.UserValidator;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.naming.AuthenticationException;
import java.util.List;

import static com.company.ams.exceptions.ErrorCode.EMAIL_NOT_FOUND;
import static com.company.ams.exceptions.ErrorCode.UNAUTHORIZED_USER;

@Service
public class UserService {
    @Autowired
    private UserDetailsRepository userRepository;

    @Autowired
    UserValidator userValidator;
    @Autowired
    JwtUtil jwtUtil;

    public UserEntity registerUser(UserEntity request) throws BadRequestException {
        userValidator.validateRegisterRequest(request);
        String password= request.getPassword();
        String encryptedPassword= encryptPassword(password);
        request.setPassword(encryptedPassword);
        return userRepository.save(request);
    }

    public List<UserEntity> fetchAllUsers(){
        return userRepository.findAll();
    }

    public static String encryptPassword(String rawPassword) {
        return BCrypt.withDefaults().hashToString(12, rawPassword.toCharArray());
    }

    public static boolean verifyPassword(String rawPassword, String hashedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), hashedPassword);
        return result.verified;
    }

    public String userLogin(LoginRequest loginRequest) {
        UserEntity user = userRepository.findByEmail(loginRequest.getEmail());
        if(user==null){
            throw new AMSException(EMAIL_NOT_FOUND, "Email not registered. Please register!!");
        }else{
            if(verifyPassword(loginRequest.getPassword(), user.getPassword())){
//                return"Logged In Successfully!!";
                return jwtUtil.generateToken(user);

            }
        }
        throw new AMSException(UNAUTHORIZED_USER, "Incorrect Password");
    }
}
