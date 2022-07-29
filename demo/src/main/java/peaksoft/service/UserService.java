package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.dto.user.UserRequest;
import peaksoft.dto.user.UserResponse;
import peaksoft.entity.User;
import peaksoft.repository.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("user with email not found"));
    }

    public UserResponse create(UserRequest userRequest){
        User user=mapToEntity(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setActive(true);
        userRepository.save(user);
        return mapToResponse(user);
    }

    public User mapToEntity(UserRequest request){
        User user=new User();
        user.setFirstName(request.getFirstName());
        user.setLastname(request.getLastName());
        user.setEmail(request.getEmail());
        user.setCreated(LocalDateTime.now());
        return user;
    }

    public UserResponse mapToResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastname())
                .isActive(user.isActive())
                .created(user.getCreated())
                .build();

    }

}
