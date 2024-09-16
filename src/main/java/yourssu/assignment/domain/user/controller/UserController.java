package yourssu.assignment.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yourssu.assignment.domain.user.dto.UserRequestDTO;
import yourssu.assignment.domain.user.dto.UserResponseDTO;
import yourssu.assignment.domain.user.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public UserResponseDTO.UserRegisterResponse registerUser(
           @Valid @RequestBody UserRequestDTO.UserRegisterRequest dto
    ) {
        return userService.registerUser(dto);
    }

    @DeleteMapping("/users")
    public String withdrawUser(
            @Valid @RequestBody UserRequestDTO.UserWithdrawRequest dto
    ) {
        userService.withdrawUser(dto);
        return "ok";
    }
}
