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
@RequestMapping("/lembretes") // para acessar o site
public class ControllerLemb {

    @Autowired
    private LembreteRepository lembreteRepository;
    
    // retornar a pagina "Bem-vindo"
    @GetMapping
    public String pagP(Model model) {
        return "view-inicio"; 
    }

    // listar lembretes
    @GetMapping("/entrada")
    public String entrada(Model model) {
        model.addAttribute("lembretes", lembreteRepository.findAll()); // para o html receber
        return "View-lembretes";
    }

    // formulario para Adicionar lembretes
    @GetMapping("/novo")
    public String Formulario(Model model) { // Spring injeta o model automaticamente
        model.addAttribute("lembrete", new Lembrete());
        return "view-form";
    }

    // salvar lembretes
    @PostMapping("/salvar")
    public String Adicionar(@ModelAttribute("lembrete") Lembrete lembrete){ 
        lembreteRepository.save(lembrete);
        return "redirect:/lembretes/entrada";
    }

    // editar lembretes
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model){
        Lembrete lembrete = lembreteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Lembrete nao encontrado: " + id));
        
        model.addAttribute("lembrete",lembrete);
        return "view-form";
    }

    // deletar lembrete
    @GetMapping("/deletar/{id}") 
    public String deletar(@PathVariable Long id) {
        lembreteRepository.deleteById(id);
        return "redirect:/lembretes/entrada";
    }

    // Buscar lembrete pela palavra-chave
    @GetMapping("/busca")
    public String searchLembretes(@RequestParam(required = false) String keyword, Model model) {
        List<Lembrete> lembretes = new ArrayList<>(); 

        if (keyword != null && !keyword.isEmpty()) {
            lembretes = lembreteRepository.findByTituloContaining(keyword); // Realiza a busca no banco
        }

        model.addAttribute("lembretesEncontrados", lembretes);
        model.addAttribute("keyword", keyword);

        return "busca"; 
    }

}
