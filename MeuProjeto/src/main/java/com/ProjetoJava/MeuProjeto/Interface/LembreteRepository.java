package com.ProjetoJava.MeuProjeto.Interface;



import org.springframework.data.repository.CrudRepository;
import com.ProjetoJava.MeuProjeto.models.Lembrete;

import java.util.List;

// <Entidade: Define qual tipo de objeto (classe) o repositório vai manipular, tipo do id ( pq mts metodos usam o id)
public interface LembreteRepository extends CrudRepository<Lembrete, Long> {
    //LembreteRepository (fala "repositorio) pq..., estamos dizendo que
    // ele vai "armazenar" os métodos que lidam com a manipulação de dados da
    // entidade Lembrete no banco de dados.

    List<Lembrete> findByTituloContaining(String titulo);
    // porque nao tem um metodo especifico de busca pelo TITULO!! Entao usa esse que o O Spring Data JPA oferece

}
