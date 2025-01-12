package com.pulse.persist.Controller;


import com.pulse.persist.DTO.UserDTO;
import com.pulse.persist.DTO.UserLoginDTO;
import com.pulse.persist.Model.User;
import com.pulse.persist.Service.JWTService;
import com.pulse.persist.Service.UserService;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")

public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public UserController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser (@RequestBody UserDTO userDTO)  {
        User createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDTO userLoginDTO){
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword()));
        if (auth.isAuthenticated()){
            return ResponseEntity.ok().body(userService.signIn(userLoginDTO.getEmail(),userLoginDTO.getPassword()));
        }
        return ResponseEntity.badRequest().body("Login Failed");
    }

    @GetMapping("hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello World");
    }
}
