package com.denzel.system.service.impl;

import com.denzel.base.BaseRepository;
import com.denzel.base.BaseServiceImpl;
import com.denzel.exception.BadRequestException;
import com.denzel.system.dto.PasswordUpdateDTO;
import com.denzel.system.dto.RegisterDTO;
import com.denzel.system.dto.RenitialiazePasswordDTO;
import com.denzel.system.dto.UpdatePhoneDTO;
import com.denzel.system.entity.User;
import com.denzel.system.repository.RoleRepository;
import com.denzel.system.repository.UserRepository;
import com.denzel.system.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

/**
 * @creation 28/02/2026 09:41
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.service.impl
 **/
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(BaseRepository<User, Long> baseRepository, UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(baseRepository);
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void createUser(RegisterDTO param) throws BadRequestException {

            if (userRepository.existsByEmail(param.getEmail())) {
                throw new BadRequestException("Cet Email est déjà utilisé");
            }
            if(userRepository.existsByPhone(param.getPhone())){
                throw new BadRequestException("Ce numéro de téléphone est déja utilisé");
            }

            User newUser = new User();
            newUser.setFirstName(param.getFirstName());
            newUser.setLastName(param.getLastName());
            newUser.setEmail(param.getEmail());
            newUser.setPhone(param.getPhone());
            newUser.setPassword(bCryptPasswordEncoder.encode(param.getPassword()));
            newUser.setRoles(Set.of(roleRepository.findByName("DEFAULT").orElseThrow(()->new RuntimeException("Default role not found"))));
            userRepository.save(newUser);
    }

    @Override
    public void UpdateUser(User user) {

    }

//    @Override
//    public Optional<User> findUserByEmail(String email) throws RuntimeException {
//        return userRepository.findByEmail(email);
//    }
    @Override
    public  Optional<User> findUserByEmail(String email) throws RuntimeException {
        return userRepository.findByEmail(email);
    }
    @Override
    public void updatePassword(PasswordUpdateDTO request, String username) {

    }

    @Override
    public void updateUsername(String username, String email) {

    }

    @Override
    public void updatePhone(String username, UpdatePhoneDTO request) {

    }

    @Override
    public void renitialiazePassword(RenitialiazePasswordDTO request) {

    }

    @Override
    public void sendCredentialToUser(String email, String password) throws IOException {

    }

}
