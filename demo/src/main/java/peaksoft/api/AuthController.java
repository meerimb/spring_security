package peaksoft.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.config.JwtTokenUtil;
import peaksoft.dto.auth.AuthMapper;
import peaksoft.dto.auth.AuthRequest;
import peaksoft.dto.auth.AuthResponse;
import peaksoft.dto.user.UserRequest;
import peaksoft.dto.user.UserResponse;
import peaksoft.entity.User;
import peaksoft.exception.ExceptionType;
import peaksoft.repository.UserRepository;
import peaksoft.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jwt/")
@Tag(name = "Authentication",description = "User with role ADMIN and EDITOR can authenticate")
public class AuthController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository repository;
    private final AuthMapper authMapper;

    @PostMapping("login")
    @Operation(summary = "All users can authenticate",description = "login all users")
    public ResponseEntity<AuthResponse>login(@RequestBody AuthRequest request){
        try {
            UsernamePasswordAuthenticationToken authenticationToken=
                    new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword());
            User user=repository.findByEmail(authenticationToken.getName()).get();
            return ResponseEntity.ok()
                    .body(authMapper.view(jwtTokenUtil.generateToken(user), ExceptionType.SUCCESSFULLY,user));
        }catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authMapper.view("", ExceptionType.LOGIN_FAILED,null));

        }
    }
    @PostMapping("registration")
    @Operation(summary = "All users can registration", description = "user can registration")
    public UserResponse create(@RequestBody UserRequest userRequest){
        return userService.create(userRequest);
    }


}
