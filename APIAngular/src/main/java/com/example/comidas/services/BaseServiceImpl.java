package com.example.comidas.services;

import com.example.comidas.entities.Base;
import com.example.comidas.entities.Plato;
import com.example.comidas.repositories.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class BaseServiceImpl<E extends Base, ID extends Serializable> implements  BaseService<E, ID> {
    protected BaseRepository<E, ID> baseRepository;

    public BaseServiceImpl(BaseRepository<E,ID> baseRepository){
        this.baseRepository = baseRepository;
    }
    @Override
    @Transactional
    public List<E> findAll() throws Exception{
        try{
            List<E> entities = baseRepository.findAll();
            return  entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @Override
    @Transactional
    public Map<String, Object> findAll(int page, int size, String sortBy, String direction) throws Exception{
        try{
            Pageable pageable;
            if (direction.equals("desc")){
                pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sortBy));
            } else {
                pageable = PageRequest.of(page,size,Sort.by(Sort.Direction.ASC, sortBy));
            }
            Page<E> entityPage = baseRepository.findAll(pageable);
            List<E> entities = entityPage.getContent();

            Map<String, Object> respose = new HashMap<>();
            respose.put("payload",entities);
            respose.put("length", entityPage.getTotalElements());
            return respose;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E findById (ID id) throws Exception {
        try{
            Optional<E> entityOptional = baseRepository.findById(id);
            return entityOptional.get();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E save(E entity) throws Exception{
        try {
            entity = baseRepository.save(entity);
            return entity;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E update( ID id, E entity) throws Exception {
        try{
            Optional<E> entityOptional = baseRepository.findById(id);
            E entityUpdate = entityOptional.get();
            entityUpdate = baseRepository.save(entity);
            return  entityUpdate;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete (ID id) throws Exception {
        try{
            if (baseRepository.existsById(id)){
                baseRepository.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}