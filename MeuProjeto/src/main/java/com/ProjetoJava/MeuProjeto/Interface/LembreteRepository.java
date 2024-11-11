package com.ProjetoJava.MeuProjeto.Interface;



import org.springframework.data.repository.CrudRepository;
import com.ProjetoJava.MeuProjeto.models.Lembrete;

import java.util.List;

public interface LembreteRepository extends CrudRepository<Lembrete, Long> {
    List<Lembrete> findByTituloContaining(String titulo);
    // porque nao tem um metodo especifico de busca pelo TITULO!! Entao usa esse que o Spring Data JPA oferece
}
