package br.com.natusfarma.spi.Simple.Product.Importer.controller;

import br.com.natusfarma.spi.Simple.Product.Importer.models.ModeloPadrao;
import br.com.natusfarma.spi.Simple.Product.Importer.repositorio.RepositorioFornecedor;
import br.com.natusfarma.spi.Simple.Product.Importer.services.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("fornecedores/")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;
    @GetMapping("cnpj")
    public List<ModeloPadrao> cpnj(@RequestParam("cnpj") String cnpj){
        return this.fornecedorService.consultaCnpj(cnpj);
    }

    @GetMapping("codigo")
    public List<ModeloPadrao> codigo(@RequestParam("codigo") String codigo) {
        return this.fornecedorService.consultaCodigo(codigo);
    }
    @GetMapping("nome")
    public List<ModeloPadrao> nome(@RequestParam("nome") String nome) {
        return this.fornecedorService.consultaNome(nome);
    }
}