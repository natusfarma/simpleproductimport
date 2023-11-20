package br.com.natusfarma.spi.Simple.Product.Importer.controller;

import br.com.natusfarma.spi.Simple.Product.Importer.models.DadosCabecalhoXml;
import br.com.natusfarma.spi.Simple.Product.Importer.models.ItemNotaXml;
import br.com.natusfarma.spi.Simple.Product.Importer.models.ModeloPadrao;
import br.com.natusfarma.spi.Simple.Product.Importer.services.ConsultaXmlService;
import br.com.natusfarma.spi.Simple.Product.Importer.uteis.LeitorXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("api/")
public class XMLController {

    @Autowired
    private ConsultaXmlService consultaXmlService;
    private LeitorXml leitorXml = new LeitorXml();

    @PostMapping("/upload-xml")
    public DadosCabecalhoXml uploadXml(@RequestParam("file") MultipartFile file) throws IOException, ParserConfigurationException, SAXException {
        return this.consultaXmlService.lerXml(file.getInputStream());
    }

    @PostMapping("/status/{index}")
    public List<ItemNotaXml> novosStatus(@PathVariable int index,@RequestBody DadosCabecalhoXml dados){
        return this.consultaXmlService.obterLista(dados,index);
    }

    @PostMapping("/upload-excel")
    public String uploadExcel(@RequestBody MultipartFile file,@RequestParam("nome") String nome){
        try {
            String filePath = "logs/" + nome;
            Path path = Paths.get(filePath);
            path = retornaNovoPath(path);
            Files.write(path, file.getBytes());
            System.out.println("Arquivo salvo com sucesso em: " + path);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
        return "";
    }

    private Path verificarSeExiste(Path path,int num){

            String caminhoArquivo = path.toString();
            int index = caminhoArquivo.indexOf(".");
            int indexUnderline = caminhoArquivo.indexOf("-");
            String extensao = caminhoArquivo.substring(index);
            String caminho;
            if (indexUnderline == -1){
                caminho = caminhoArquivo.substring(0,index);
            }else{
                caminho = caminhoArquivo.substring(0,indexUnderline);
            }
            String caminhoFinal = caminho + "-"+ num + extensao;
            Path novoPath = Paths.get(caminhoFinal);
            return novoPath;



    }
    private Path retornaNovoPath(Path path){
        int i = 1;
        while(path.toFile().exists()){
            path = verificarSeExiste(path,i);
            i++;
        }

        return path;
    }


}
