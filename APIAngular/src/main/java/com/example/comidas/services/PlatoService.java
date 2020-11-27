package com.example.comidas.services;

import com.example.comidas.entities.Plato;

import java.util.List;
import java.util.Map;

public interface PlatoService extends BaseService<Plato, Long> {
    List<Plato> search(String filtro) throws Exception;
    public Map<String, Object> search(String filter, int page, int size, String sortBy, String direction) throws Exception;
}
