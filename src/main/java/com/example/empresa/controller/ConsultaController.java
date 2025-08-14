package com.example.empresa.controller;

import com.example.empresa.repository.ClienteRepository;
import com.example.empresa.repository.ProdutoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ConsultaController {

    private final ProdutoRepository produtoRepo;
    private final ClienteRepository clienteRepo;

    public ConsultaController(ProdutoRepository produtoRepo, ClienteRepository clienteRepo) {
        this.produtoRepo = produtoRepo;
        this.clienteRepo = clienteRepo;
    }

    @GetMapping("/consultar-dados")
    public String consultar(Model model) {
        model.addAttribute("produtos", produtoRepo.findAll());
        model.addAttribute("clientes", clienteRepo.findAll());
        return "consulta";
    }

    @GetMapping("/produto/delete/{id}")
    public String deleteProduto(@PathVariable Long id) {
        produtoRepo.deleteById(id);
        return "redirect:/consultar-dados";
    }

    @GetMapping("/cliente/delete/{id}")
    public String deleteCliente(@PathVariable Long id) {
        clienteRepo.deleteById(id);
        return "redirect:/consultar-dados";
    }
}
