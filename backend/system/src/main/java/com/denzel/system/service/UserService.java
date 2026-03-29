package com.denzel.system.service;

import com.denzel.base.BaseService;
import com.denzel.exception.BadRequestException;
import com.denzel.system.dto.PasswordUpdateDTO;
import com.denzel.system.dto.RegisterDTO;
import com.denzel.system.dto.RenitialiazePasswordDTO;
import com.denzel.system.dto.UpdatePhoneDTO;
import com.denzel.system.entity.User;

import java.io.IOException;
import java.util.Optional;

/**
 * @creation 28/02/2026 09:38
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.service.impl
 **/
public interface UserService extends BaseService<User, Long> {

    void createUser(RegisterDTO param) throws BadRequestException;

    void UpdateUser(User user);

   Optional<User>  findUserByEmail(String email) throws RuntimeException;

    void updatePassword(PasswordUpdateDTO request, String username);

    void updateUsername(String username, String email);

    void updatePhone(String username, UpdatePhoneDTO request);

    void renitialiazePassword(RenitialiazePasswordDTO request);

    void sendCredentialToUser(String email, String password) throws IOException;

    //void sendAcountActivationLink(String email) throws RuntimeException;
    //void sendCredentialToUser(String email, String password) throws RuntimeException;
    //void activateAccount(String token) throws BadRequestException;

}
