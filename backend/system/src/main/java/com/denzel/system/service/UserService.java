package com.denzel.system.service;

import com.denzel.base.BaseService;
import com.denzel.system.dto.PasswordUpdateRequest;
import com.denzel.system.dto.RegisterParam;
import com.denzel.system.dto.RenitialiazePasswordRequest;
import com.denzel.system.dto.UpdatePhoneRequest;
import com.denzel.system.entity.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @creation 28/02/2026 09:38
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.service.impl
 **/
public interface UserService extends BaseService<User, Long> {

    void createUser(RegisterParam param) throws RuntimeException;

    void UpdateUser(User user);

    Optional<User>  findUserByEmail(String email) throws RuntimeException;

    void updatePassword(PasswordUpdateRequest request, String username);

    void updateUsername(String username, String email);

    void updatePhone(String username, UpdatePhoneRequest request);

    void renitialiazePassword(RenitialiazePasswordRequest request);

    void sendCredentialToUser(String email, String password) throws IOException;

    //void sendAcountActivationLink(String email) throws RuntimeException;
    //void sendCredentialToUser(String email, String password) throws RuntimeException;
    //void activateAccount(String token) throws BadRequestException;

}
