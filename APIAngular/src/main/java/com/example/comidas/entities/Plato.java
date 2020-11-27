package com.example.comidas.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "plato")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class Plato extends Base {

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "precio")
    private String precio;

    @Column(name = "rubro")
    private String rubro;

    @Column(name = "imagenPath")
    private String imagenPath;

    //@Column(name = "Ingredientes")
   // private String[] ingredientes [];
}
