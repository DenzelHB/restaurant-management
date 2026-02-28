package com.denzel.base;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

/**
 * @creation 27/02/2026 22:48
 * @Author AMEDE NESTOR HABA (Denzel)
 * @Package com.denzel.base
 **/
@RequiredArgsConstructor
public class BaseServiceImpl <ENTITY extends BaseEntity, ID extends Serializable> implements BaseService<ENTITY, ID> {

    private final BaseRepository<ENTITY, ID> baseRepository;

    @Override
    public ENTITY save(ENTITY entity) {
        return baseRepository.save(entity);
    }

    @Override
    public ENTITY findById(ID id) {
        return baseRepository.findById(id).orElse(null);
    }

    @Override
    public List<ENTITY> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public List<ENTITY> saveAll(List<ENTITY> entities) {
        return baseRepository.saveAll(entities);
    }

    @Override
    public List<ENTITY> findAll(Sort sort) {
        return baseRepository.findAll(sort);
    }

    @Override
    public List<ENTITY> findAllById(Iterable<ID> ids) {
        return baseRepository.findAllById(ids);
    }

    @Override
    public void deleteById(ID id) {
        baseRepository.deleteById(id);
    }
}
