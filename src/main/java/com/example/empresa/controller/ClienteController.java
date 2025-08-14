package com.example.empresa.controller;

import com.example.empresa.model.Cliente;
import com.example.empresa.repository.ClienteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteRepository repo;
    public ClienteController(ClienteRepository repo){ this.repo = repo; }

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("acao", "Criar");
        return "cliente/form";
    }

    @PostMapping("/create")
    public String createSubmit(@ModelAttribute Cliente cliente){
        repo.save(cliente);
        return "redirect:/consultar-dados";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model){
        Cliente c = repo.findById(id).orElseThrow();
        model.addAttribute("cliente", c);
        model.addAttribute("acao", "Editar");
        return "cliente/form";
    }

    @PostMapping("/edit")
    public String editSubmit(@ModelAttribute Cliente cliente){
        repo.save(cliente);
        return "redirect:/consultar-dados";
    }
}

