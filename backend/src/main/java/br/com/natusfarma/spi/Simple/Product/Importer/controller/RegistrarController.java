package br.com.natusfarma.spi.Simple.Product.Importer.controller;

import br.com.natusfarma.spi.Simple.Product.Importer.models.DadosProduto;
import br.com.natusfarma.spi.Simple.Product.Importer.services.ConsultaXmlService;
import br.com.natusfarma.spi.Simple.Product.Importer.services.RegistrarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/registrar/")
public class RegistrarController {

    @Autowired
    private RegistrarService registrarService;
    @Autowired
    private ConsultaXmlService consultaXmlService;
    @PostMapping("produto")
    public ResponseEntity<String> produto(@RequestBody DadosProduto dadosProduto){
        try {
            String codigoProduto = consultaXmlService.verificarBarras(dadosProduto.getBarras());
            if (codigoProduto != "") {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Barras já registrado.Codigo do produto: " + codigoProduto);
            }
            return ResponseEntity.ok().body("{\"codigo\": \""+
                    registrarService.registrarProduto(dadosProduto)
                    +"\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a requisição.");
        }
    }

    @GetMapping("verificar")
    public ResponseEntity<String> barras (@RequestParam String barras,@RequestParam String codProduto,@RequestParam int codFornecedor){
        String codigoProduto = consultaXmlService.verificarBarras(barras);
        if(codigoProduto != ""){
            return ResponseEntity.ok("Barras já registrado.Codigo do produto: " + codigoProduto);
        }
        codigoProduto = consultaXmlService.verificarVinculo(codProduto,codFornecedor);
        if (codigoProduto != ""){
            return ResponseEntity.ok("Produto :"+codProduto+" encontrado com o fornecedor: " + codFornecedor );
        }
        return ResponseEntity.ok("Barras não registrado");
    }

}
