package com.example.empresa.controller;

import com.example.empresa.model.Produto;
import com.example.empresa.repository.ProdutoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoRepository repo;
    public ProdutoController(ProdutoRepository repo){ this.repo = repo; }

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("produto", new Produto());
        model.addAttribute("acao", "Criar");
        return "produto/form";
    }

    @PostMapping("/create")
    public String createSubmit(@ModelAttribute Produto produto){
        repo.save(produto);
        return "redirect:/consultar-dados";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model){
        Produto p = repo.findById(id).orElseThrow();
        model.addAttribute("produto", p);
        model.addAttribute("acao", "Editar");
        return "produto/form";
    }

    @PostMapping("/edit")
    public String editSubmit(@ModelAttribute Produto produto){
        repo.save(produto);
        return "redirect:/consultar-dados";
    }
}
