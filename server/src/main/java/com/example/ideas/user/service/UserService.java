package com.example.ideas.user.service;

import com.example.ideas.exception.DataAlreadyExistsException;
import com.example.ideas.exception.EntityNotFoundException;
import com.example.ideas.exception.NoAuthorizationException;
import com.example.ideas.security.auth.AuthenticationService;
import com.example.ideas.security.config.JwtService;
import com.example.ideas.user.controller.UserResponseDTO;
import com.example.ideas.user.controller.UserUpdateRequest;
import com.example.ideas.user.model.User;
import com.example.ideas.user.repository.UserRepository;
import com.example.ideas.util_Entities.department.model.Department;
import com.example.ideas.util_Entities.department.repository.DepartmentRepository;
import com.example.ideas.util_Entities.role.model.Role;
import com.example.ideas.util_Entities.role.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.ideas.helpers.ObjectProvider.getObjectFromDB;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final AuthenticationService authenticationService;
    private final UserDTOMapper userDTOMapper;
    private final JwtService jwtService;


    public List<UserResponseDTO> getUsers() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "email")).stream()
                .map(userDTOMapper)
                .collect(toList());
    }

    public UserResponseDTO getUserById(Long userId) throws EntityNotFoundException {
        User user = getObjectFromDB(userId, userRepository);
        return userDTOMapper.apply(user);
    }

    public UserResponseDTO getUserByEmail(String email) throws EntityNotFoundException {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new EntityNotFoundException("user not found in DB"));
        return userDTOMapper.apply(user);
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("student with id: " + userId + " does not exists");
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public UserResponseDTO updateUserById(Long userId, UserUpdateRequest updatedUser, String token)
            throws EntityNotFoundException, DataAlreadyExistsException, IllegalAccessException, NoAuthorizationException {

        User user = getObjectFromDB(userId, userRepository);
        String userRoleName = authenticationService.getUserRoleName(token);
        String userName = getUserName(token);

        if (!(user.getEmail().equals(userName) || userRoleName.equals("Admin"))) {
            throw new NoAuthorizationException("user details can only be modified by the data owner or Admin");
        }

        String name = updatedUser.getName();
        if (name != null && !name.isEmpty()) {
            user.setName(name);
        }

        String email = updatedUser.getEmail();
        if (email != null) {
            Optional<User> optionalUser = userRepository.findUserByEmail(email);
            if (optionalUser.isPresent()) {
                throw new DataAlreadyExistsException("email already exists in DB");
            }
            user.setEmail(email);
        }

        Long roleId = updatedUser.getRoleId();
        if (roleId != null && Objects.equals(userRoleName, "Admin")) {
            Role role = getObjectFromDB(roleId, roleRepository);
            user.setRole(role);
        } else if (roleId != null) {
            throw new IllegalAccessException("This user has no authority to modify role");
        }

        Long departmentId = updatedUser.getDepartmentId();
        if (departmentId != null) {
            Department department = getObjectFromDB(departmentId, departmentRepository);
            user.setDepartment(department);
        }

        return userDTOMapper.apply(user);
    }

    private String getUserName(String token) {
        String userJWT = jwtService.getJWT(token);
        return jwtService.extractUsername(userJWT);
    }

//    @Transactional
//    public ResponseEntity<String> updateUserByEmail(String email, User updatedUser) {
//        User user = userRepository.findUserByEmail(email).orElse(null);
//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id: " + email + " not found");
//        }
//        return validation(updatedUser.getEmail(), updatedUser.getName(), updatedUser.getPassword(), updatedUser.getRole().getRoleId(), updatedUser.getDepartment().getDepartmentId(), user);
//    }

//    private ResponseEntity<String> validation(String email, String name, String password, Long roleId, Long departmentId, User user) {
//        if (email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)) { // user.getEmail().equals(email)
//            Optional<User> userOptional = userRepository.findUserByEmail(email);
//            if (userOptional.isPresent()) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already taken");
//            }
//            user.setEmail(email);
//        }
//        if (name != null && name.length() > 0 && !Objects.equals(user.getName(), name)) {
//            user.setName(name);
//        }
//        if (password != null && password.length() > 0 && !Objects.equals(user.getPassword(), password)) {
//            user.setPassword(password);
//        }
//        if (roleId != null && roleId > 0 && !Objects.equals(user.getRole().getRoleId(), roleId)) {
//            Optional<Role> roleOptional = roleRepository.findById(roleId);
//            if (roleOptional.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role with id: " + roleId + " doesn't exist");
//            }
//            user.setRole(roleOptional.get());
//        }
//        if (departmentId != null && departmentId > 0 && !Objects.equals(user.getDepartment().getDepartmentId(), departmentId)) {
//            Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
//            if (departmentOptional.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department with id: " + departmentId + " doesn't exist");
//            }
//            user.setDepartment(departmentOptional.get());
//        }
//        return ResponseEntity.ok("Validation successful");
//    }

//    private boolean isEmailValid(String email) {
//        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
//        Pattern pattern = Pattern.compile(emailRegex);
//        Matcher matcher = pattern.matcher(email);
//        return matcher.matches();
//    }

}