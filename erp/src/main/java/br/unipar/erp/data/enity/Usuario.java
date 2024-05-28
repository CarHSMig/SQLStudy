package br.unipar.erp.data.enity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;


@Entity
public class Usuario {

    @Id
    private Integer codigo;
    private String usuario;
    private String senha;
    private LocalDate dataNascimento;
}
