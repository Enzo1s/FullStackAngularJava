package com.example.comidas.services;

import com.example.comidas.entities.Plato;
import com.example.comidas.repositories.BaseRepository;
import com.example.comidas.repositories.PlatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlatoServiceImpl extends BaseServiceImpl<Plato, Long> implements PlatoService {

    @Autowired
    private PlatoRepository platoRepository;

    public PlatoServiceImpl(BaseRepository<Plato, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    public List<Plato> search(String filtro) throws Exception {
        try {
            List<Plato> platos = platoRepository.searchNativo(filtro);
            return  platos;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Map<String, Object> search(String filter, int page, int size, String sortBy, String direction) throws Exception {
        try {
            Pageable pageable;
            if (direction.equals("desc")){
                pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sortBy));
            } else {
                pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortBy));
            }
            Page<Plato> entityPage = platoRepository.search(filter, pageable);
            List<Plato> entities = entityPage.getContent();
            Map<String, Object> respose = new HashMap<>();
            respose.put("payload", entities);
            respose.put("length", entityPage.getTotalElements());
            return respose;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
