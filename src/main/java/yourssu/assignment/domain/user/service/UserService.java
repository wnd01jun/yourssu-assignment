package yourssu.assignment.domain.user.service;

import yourssu.assignment.domain.user.dto.UserRequestDTO;
import yourssu.assignment.domain.user.dto.UserResponseDTO;
import yourssu.assignment.domain.user.entity.User;

public interface UserService {

    UserResponseDTO.UserRegisterResponse registerUser(
            UserRequestDTO.UserRegisterRequest dto
            );

    void withdrawUser(UserRequestDTO.UserWithdrawRequest dto);

    User login(String email, String password);
}
