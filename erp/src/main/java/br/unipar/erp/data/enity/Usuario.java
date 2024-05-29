package br.unipar.erp.data.enity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@Table(name="usuarios")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    private Integer codigo;

    @Column(name="username")
    private String usuario;

    @Column(name = "password")
    private String senha;

    private String nome;

    @Column(name = "nascimento")
    private LocalDate dataNascimento;
}
