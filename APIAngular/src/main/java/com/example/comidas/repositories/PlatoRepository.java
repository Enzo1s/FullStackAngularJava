package com.example.comidas.repositories;

import com.example.comidas.entities.Plato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatoRepository extends  BaseRepository<Plato, Long>{

    List<Plato> findByNombreContaining(String nombre);

    Page<Plato> findByNombreContaining(String nombre, Pageable pageable);

    @Query(value = "SELECT p FROM Plato p WHERE p.nombre LIKE %:filtro%")
    List<Plato> search(@Param("filtro") String filtro);

    @Query(value = "SELECT p FROM Plato p WHERE p.nombre LIKE %:filtro%")
    Page<Plato> search(@Param("filtro") String filtro, Pageable pageable);

    @Query(value = "SELECT * FROM plato WHERE plato.nombre LIKE %:filtro%", nativeQuery = true)
    List<Plato> searchNativo(@Param("filtro") String filtro);

    @Query(
            value = "SELECT * FROM plato WHERE plato.nombre LIKE %:filtro%",
            countQuery = "SELECT count(*) FROM plato",
            nativeQuery = true
    )
    Page<Plato> searchNativo(@Param("filtro") String filtro, Pageable pageable);
}
