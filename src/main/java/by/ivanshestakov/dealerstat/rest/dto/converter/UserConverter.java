package by.ivanshestakov.dealerstat.rest.dto.converter;

import by.ivanshestakov.dealerstat.rest.dto.UserDTO;
import by.ivanshestakov.dealerstat.rest.entity.Role;
import by.ivanshestakov.dealerstat.rest.entity.User;
import by.ivanshestakov.dealerstat.rest.exception.RecordNotFoundException;
import by.ivanshestakov.dealerstat.rest.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    private final RoleRepository roleRepository;

    @Autowired
    public UserConverter(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        Role role = roleRepository.findByName("ROLE_" + userDTO.getRoleName())
                .orElseThrow(() -> {
                    throw new RecordNotFoundException("Role with name=" + userDTO.getRoleName() + " not found");
                });
        user.setRole(role);
        return user;
    }
}
