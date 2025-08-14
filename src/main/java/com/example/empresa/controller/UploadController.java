package com.example.empresa.controller;

import com.example.empresa.model.DadosEmpresa;
import com.example.empresa.repository.ClienteRepository;
import com.example.empresa.repository.ProdutoRepository;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Controller
public class UploadController {

    private final ProdutoRepository produtoRepo;
    private final ClienteRepository clienteRepo;

    public UploadController(ProdutoRepository produtoRepo, ClienteRepository clienteRepo) {
        this.produtoRepo = produtoRepo;
        this.clienteRepo = clienteRepo;
    }

    @GetMapping("/upload")
    public String formUpload() {
        return "upload";
    }

    @PostMapping("/upload")
    public String handleUpload(@RequestParam("file") MultipartFile file, Model model) {
        // validação básica
        if (file == null || file.isEmpty()) {
            model.addAttribute("msgErro", "Envie um arquivo XML.");
            return "upload";
        }
        String nome = StringUtils.cleanPath(file.getOriginalFilename() != null ? file.getOriginalFilename() : "");
        if (!nome.toLowerCase().endsWith(".xml")) {
            model.addAttribute("msgErro", "Arquivo inválido. Envie um .xml");
            return "upload";
        }

        try (InputStream is = file.getInputStream()) {
            JAXBContext ctx = JAXBContext.newInstance(DadosEmpresa.class);
            Unmarshaller unmarshaller = ctx.createUnmarshaller();
            DadosEmpresa dados = (DadosEmpresa) unmarshaller.unmarshal(is);

            if (dados.getProdutos() != null && !dados.getProdutos().isEmpty()) {
                produtoRepo.saveAll(dados.getProdutos());
            }
            if (dados.getClientes() != null && !dados.getClientes().isEmpty()) {
                clienteRepo.saveAll(dados.getClientes());
            }

            model.addAttribute("msgOk", "Upload e importação realizados com sucesso!");
            model.addAttribute("linkConsulta", "/consultar-dados");
        } catch (Exception e) {
            model.addAttribute("msgErro", "Falha ao processar XML: " + e.getMessage());
        }
        return "upload";
    }
}

