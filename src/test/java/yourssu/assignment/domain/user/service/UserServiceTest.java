package yourssu.assignment.domain.user.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import yourssu.assignment.domain.user.dto.UserRequestDTO;
import yourssu.assignment.domain.user.dto.UserResponseDTO;
import yourssu.assignment.domain.user.entity.User;
import yourssu.assignment.domain.user.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    void registerUser() {
        UserRequestDTO.UserRegisterRequest dto1 = UserRequestDTO.UserRegisterRequest.builder()
                .username("테스트1")
                .email("changjun157@naver.com")
                .password("qwer1234")
                .build();

        UserResponseDTO.UserRegisterResponse response = userService.registerUser(dto1);

        User user = userRepository.findByEmail("changjun157@naver.com").get();

        Assertions.assertThat(user.getPassword()).isNotEqualTo(dto1.getPassword());
        Assertions.assertThat(user.getUsername()).isEqualTo(dto1.getUsername());
        org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> userService.registerUser(dto1));


    }

    @Test
    void withdrawUser() {
    }

    @Test
    void login() {
    }
}