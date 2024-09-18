package yourssu.assignment.domain.user.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
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
    @Autowired
    PasswordEncoder passwordEncoder;

    static String LOGIN_ID = "changjun157@naver.com";
    static String LOGIN_PASSWORD = "qwer1234";
    static String USERNAME = "테스트1";

    static Long userID;

    @BeforeEach
    void init() {
        User user = User.builder()
                .username(USERNAME)
                .email(LOGIN_ID)
                .password(passwordEncoder.encode(LOGIN_PASSWORD))
                .build();

        userRepository.save(user);
        userID = user.getId();
    }

    @Test
    void registerUser() {


        UserRequestDTO.UserRegisterRequest dto = UserRequestDTO.UserRegisterRequest.builder()
                .email("test1@naver.com")
                .password("test1")
                .username("tester1")
                .build();
        userService.registerUser(dto);

        User user = userRepository.findByEmail("test1@naver.com").get();

        Assertions.assertThat(user.getPassword()).isNotEqualTo("test1");
        Assertions.assertThat(passwordEncoder.matches("test1", user.getPassword())).isTrue();
        Assertions.assertThat(user.getUsername()).isEqualTo("tester1");


    }

    @Test
    void withdrawUser() {

        UserRequestDTO.UserWithdrawRequest withdrawRequest = UserRequestDTO.UserWithdrawRequest.builder()
                .email(LOGIN_ID)
                .password(LOGIN_PASSWORD)
                .build();

        userService.withdrawUser(withdrawRequest);

        Assertions.assertThat(userRepository.findByEmail(LOGIN_ID).isEmpty()).isTrue();


    }

    @Test
    void login() {

        User user = userService.login(LOGIN_ID, LOGIN_PASSWORD);

        Assertions.assertThat(user.getUsername()).isEqualTo(USERNAME);
        Assertions.assertThat(user.getEmail()).isEqualTo(LOGIN_ID);
        Assertions.assertThat(passwordEncoder.matches(LOGIN_PASSWORD, user.getPassword())).isTrue();


    }
}