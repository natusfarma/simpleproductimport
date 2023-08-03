package br.com.natusfarma.spi.Simple.Product.Importer.controller;

import br.com.natusfarma.spi.Simple.Product.Importer.models.ModeloPadrao;
import br.com.natusfarma.spi.Simple.Product.Importer.models.SubCategoria;
import br.com.natusfarma.spi.Simple.Product.Importer.services.SubCategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/subCategorias/")
public class SubCategoriaController {

    @Autowired
    private SubCategoriaService subCategoriaService;

    @GetMapping("codigo")
    public List<SubCategoria> codigo(@RequestParam("codigo") String codigo) {
        return this.subCategoriaService.consultaCodigo(codigo);
    }
    @GetMapping("nome")
    public List<SubCategoria> nome(@RequestParam("nome") String nome) {
        return this.subCategoriaService.consultaNome(nome);
    }
}
