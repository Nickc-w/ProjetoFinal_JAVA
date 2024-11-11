package com.ProjetoJava.MeuProjeto.models;

import jakarta.persistence.*;

@Entity  
public class Lembrete {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    private String horario;
    private String titulo; 

    @Column(name = "dataL") // porque no banco de dados coloquei como dataL
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

    public String getTitulo() { 
        return titulo;
    }

    public void setTitulo(String titulo) { 
        this.titulo = titulo;
    }
}

