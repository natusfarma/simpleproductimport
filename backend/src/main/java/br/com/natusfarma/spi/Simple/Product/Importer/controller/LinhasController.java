package br.com.natusfarma.spi.Simple.Product.Importer.controller;

import br.com.natusfarma.spi.Simple.Product.Importer.models.ModeloPadrao;
import br.com.natusfarma.spi.Simple.Product.Importer.services.LinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/linhas/")
public class LinhasController {
    @Autowired
    private LinhaService linhaService;

    @GetMapping("codigo")
    public List<ModeloPadrao> codigo(@RequestParam("codigo") String codigo) {
        return this.linhaService.consultaCodigo(codigo);
    }
    @GetMapping("nome")
    public List<ModeloPadrao> nome(@RequestParam("nome") String nome) {
        return this.linhaService.consultaNome(nome);
    }
}
