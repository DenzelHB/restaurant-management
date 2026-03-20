package com.denzel.base;

import com.denzel.exception.handler.CustomHttpRequestResponse;
import com.denzel.utils.CustomHttpStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * @creation 27/02/2026 22:48
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.base
 **/
@RequiredArgsConstructor
public abstract class BaseController <ENTITY extends BaseEntity, ID extends Serializable> {

    private final BaseService<ENTITY, ID> baseService;


    @GetMapping("/find-all")
    public CustomHttpRequestResponse<?> findAll() {
        return new CustomHttpRequestResponse<>(CustomHttpStatus.SUCCESS, baseService.findAll());
    }

    @PostMapping("/save")
    public CustomHttpRequestResponse<?> save(@RequestBody ENTITY entity, HttpServletRequest request) {
        return new CustomHttpRequestResponse<>(CustomHttpStatus.SUCCESS, baseService.save(entity));
    }

    @PostMapping("/save-all")
    public CustomHttpRequestResponse<?> saveAll(@RequestBody List<ENTITY> entities, HttpServletRequest request) {
        return new CustomHttpRequestResponse<>(CustomHttpStatus.SUCCESS, baseService.saveAll(entities));
    }

    @PutMapping("/update")
    public CustomHttpRequestResponse<?> update(@RequestBody ENTITY entity, HttpServletRequest request) {
        return new CustomHttpRequestResponse<>(CustomHttpStatus.SUCCESS, baseService.save(entity));
    }

    @GetMapping("/{id}")
    public CustomHttpRequestResponse<?> findById(@PathVariable ID id) {
        return new CustomHttpRequestResponse<>(CustomHttpStatus.SUCCESS, baseService.findById(id));
    }

    @DeleteMapping("/{id}")
    public CustomHttpRequestResponse<?> deleteById(@PathVariable ID id){
        baseService.deleteById(id);
        return new CustomHttpRequestResponse<>(CustomHttpStatus.SUCCESS, CustomHttpStatus.SUCCESS.getMessage());
    }


    public abstract CustomHttpRequestResponse<?> findAll(HttpServletRequest request, HttpServletResponse response);
}
