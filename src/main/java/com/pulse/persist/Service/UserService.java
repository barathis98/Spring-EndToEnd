package com.pulse.persist.Service;


import com.pulse.persist.DTO.UserDTO;
import com.pulse.persist.Exception.DuplicateEmailException;
import com.pulse.persist.Model.User;
import com.pulse.persist.Repository.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JWTService jwtService;


    public UserService(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder, JWTService jwtService) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtService = jwtService;
    }

    public User createUser(UserDTO userDTO) throws DuplicateEmailException {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

        if (userRepo.existsByEmail(userDTO.getEmail())){
            throw new DuplicateEmailException("Email already exists "+ userDTO.getEmail());
        }

        return userRepo.save(user);

    }

    public String signIn(String email, String password) {
        return jwtService.generateJWTToken(userRepo.findByEmail(email));

    }
}
