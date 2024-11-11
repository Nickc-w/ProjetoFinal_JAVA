package com.ProjetoJava.MeuProjeto.models;

import jakarta.persistence.*;

@Entity  // entidade JPA, sera mapeada para a tabela
public class Lembrete {
    @Id // marca id como chave primaria da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // indica que o valor sera gerado automaticamnete pelo BD
    private Long id;
    private String horario;
    private String titulo; // Mudei "nome" para "titulo"

    @Column(name = "dataL")
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() { // Ajuste no getter
        return titulo;
    }

    public void setTitulo(String titulo) { // Ajuste no setter
        this.titulo = titulo;
    }
}

