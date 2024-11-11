package com.ProjetoJava.MeuProjeto.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.ProjetoJava.MeuProjeto.Interface.LembreteRepository;
import com.ProjetoJava.MeuProjeto.models.Lembrete;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/lembretes")
public class ControllerLemb {

    @Autowired
    private LembreteRepository lembreteRepository;
    //  O Spring cria e entrega os objetos automaticamente para você
    // Spring cuide da criação e injeção de dependências,
    // como repositórios e serviços, sem que você precise se preocupar em criá-los manualmente (usando new..)



    @GetMapping
    public String pagP(Model model) {
        return "view-inicio"; // ou o nome do seu arquivo HTML sem a extensão
    }

    // listar lembretes
    @GetMapping("/entrada")
    public String entrada(Model model) {
        model.addAttribute("lembretes", lembreteRepository.findAll()); // para o html receber
        return "View-lembretes";
        // Isso retorna uma lista de todos os lembretes armazenados no banco de dados.
    }

    // formulario para Adicionar lembretes
    @GetMapping("/novo")
    public String Formulario(Model model) { // Spring injeta o model automaticamente
        model.addAttribute("lembrete", new Lembrete());
        return "view-form";
    }

    @PostMapping("/salvar")
    public String Adicionar(@ModelAttribute("lembrete") Lembrete lembrete){ // a partir dos dados enviados pelo formulário na página HTML.
        lembreteRepository.save(lembrete);
        return "redirect:/lembretes/entrada";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model){
        Lembrete lembrete = lembreteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Lembrete nao encontrado: " + id));
        // orElseTheow(() -> new IllegalArgumentException(...)) = estrutura padrao
        // -> indica o que será executado.
        model.addAttribute("lembrete",lembrete);
        return "view-form";
    }

    @GetMapping("/deletar/{id}") // nao precisa dos dados, nao precisa do model
    // tem que ter o mesmo nome no html!!!!!
    public String deletar(@PathVariable Long id) {
        lembreteRepository.deleteById(id);
        return "redirect:/lembretes/entrada";
    }


    @GetMapping("/busca")
    public String searchLembretes(@RequestParam(required = false) String keyword, Model model) {
        List<Lembrete> lembretes = new ArrayList<>(); // Ja que nao tem else para garantir que ela sempre sera inicializada

        if (keyword != null && !keyword.isEmpty()) {
            lembretes = lembreteRepository.findByTituloContaining(keyword); // Realiza a busca no banco
        }

        model.addAttribute("lembretesEncontrados", lembretes);
        model.addAttribute("keyword", keyword);

        return "busca"; // Retorna a view de busca
    }









}