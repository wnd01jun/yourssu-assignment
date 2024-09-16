package yourssu.assignment.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yourssu.assignment.domain.comment.service.CommentService;
import yourssu.assignment.domain.user.dto.UserRequestDTO;
import yourssu.assignment.domain.user.dto.UserResponseDTO;
import yourssu.assignment.domain.user.entity.User;
import yourssu.assignment.domain.user.repository.UserRepository;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final CommentService commentService;
    private final PasswordEncoder encoder;

    @Override
    public UserResponseDTO.UserRegisterResponse registerUser(UserRequestDTO.UserRegisterRequest dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .build();

        userRepository.save(user);

        return UserResponseDTO.UserRegisterResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    @Override
    public void withdrawUser(UserRequestDTO.UserWithdrawRequest dto) {
        User user = login(dto.getEmail(), dto.getPassword());
        commentService.withdrawAllByComment(user);
        userRepository.delete(user);

    }

    @Override
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException());
        if (encoder.matches(password, user.getPassword())) {
            return user;
        }
        return null; // 혹은 예외 던지기
    }
}
