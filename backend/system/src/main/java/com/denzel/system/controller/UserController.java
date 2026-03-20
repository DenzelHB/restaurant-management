package com.denzel.system.controller;

import com.denzel.base.BaseController;
import com.denzel.exception.BadRequestException;
import com.denzel.exception.handler.CustomHttpRequestResponse;
import com.denzel.system.dto.PasswordUpdateRequest;
import com.denzel.system.dto.RegisterParam;
import com.denzel.system.dto.RenitialiazePasswordRequest;
import com.denzel.system.dto.UpdatePhoneRequest;
import com.denzel.system.entity.User;
import com.denzel.system.securtity.constants.RestConstant;
import com.denzel.system.service.UserService;
import com.denzel.utils.CustomHttpStatus;
import com.denzel.utils.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;


/**
 * @creation 20/03/2026 00:12
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.system.controller
 **/
@RestController
@RequestMapping(RestConstant.APPLICATION_NAME + RestConstant.RESOURCE_USER)
public class UserController extends BaseController <User, Long>{

    private final UserService userService;

    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
    }

    @Override
    public CustomHttpRequestResponse<?> findAll(HttpServletRequest request, HttpServletResponse response) {
        return new CustomHttpRequestResponse<>(CustomHttpStatus.SUCCESS, userService.findAll());
    }

    @PostMapping(path = "/create-user")
    public CustomHttpRequestResponse<?> createUser(@RequestBody RegisterParam param) throws RuntimeException {
        userService.createUser(param);
        return new CustomHttpRequestResponse<>(CustomHttpStatus.SUCCESS, null);
    }


    @PostMapping("/update-password")
    public CustomHttpRequestResponse<?> updatePassword(@RequestBody PasswordUpdateRequest passwordUpdateRequest) throws BadRequestException {
        String username = SecurityUtil.getCurrentUsername();
        userService.updatePassword(passwordUpdateRequest, username);
        return new CustomHttpRequestResponse<>(CustomHttpStatus.SUCCESS, null);
    }


    @PostMapping("/update-phone")
    public CustomHttpRequestResponse<?> updatePhone(@RequestBody UpdatePhoneRequest request) throws BadRequestException{
        String username = SecurityUtil.getCurrentUsername();
        userService.updatePhone(username, request);
        return new CustomHttpRequestResponse<>(CustomHttpStatus.SUCCESS, null);
    }

    @PostMapping("/renitialiaze-password")
    public CustomHttpRequestResponse<?> renitialiazePassword(@RequestBody RenitialiazePasswordRequest request) {
        userService.renitialiazePassword(request);
        return new CustomHttpRequestResponse<>(CustomHttpStatus.SUCCESS, null);
    }

    @GetMapping(path = "/get-connected-user")
    public CustomHttpRequestResponse<?> getConnetedUser() throws BadRequestException{
        var user  = userService.findUserByEmail(SecurityUtil.getCurrentUsername()).orElseThrow(()-> new BadRequestException("Utiliseur n'existe pas dans notre systeme!"));
        return new CustomHttpRequestResponse<>(CustomHttpStatus.SUCCESS, user);
    }
}
