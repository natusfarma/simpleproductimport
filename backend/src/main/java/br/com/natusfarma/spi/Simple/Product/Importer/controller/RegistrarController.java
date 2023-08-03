package br.com.natusfarma.spi.Simple.Product.Importer.controller;

import br.com.natusfarma.spi.Simple.Product.Importer.models.DadosProduto;
import br.com.natusfarma.spi.Simple.Product.Importer.services.ConsultaXmlService;
import br.com.natusfarma.spi.Simple.Product.Importer.services.RegistrarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            String existe = consultaXmlService.verificarBarras(dadosProduto.getBarras());
            if (existe != "") {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Barras já registrado no banco de dados.");
            }
            return ResponseEntity.ok().body("{\"message\": \""+
                    registrarService.registrarProduto(dadosProduto)
                    +"\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a requisição.");
        }


    }
}
