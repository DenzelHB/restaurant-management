package com.denzel.base;

import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @creation 27/02/2026 22:47
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.base
 **/
public interface BaseService <ENTITY extends BaseEntity, ID> {

    ENTITY save(ENTITY entity);


    ENTITY findById(ID id);

    List<ENTITY> findAll();

    List<ENTITY> saveAll(List<ENTITY> entities);

    List<ENTITY> findAll(Sort sort);

    List<ENTITY> findAllById(Iterable<ID> ids);

    void deleteById(ID id);
}
