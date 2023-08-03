package br.com.natusfarma.spi.Simple.Product.Importer.services;

import br.com.natusfarma.spi.Simple.Product.Importer.models.DadosProduto;
import br.com.natusfarma.spi.Simple.Product.Importer.repositorio.*;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RegistrarService {
    @Autowired
    private RepositorioCodigo repositorioCodigo;
    @Autowired
    private RepositorioUsuario repositorioUsuario;
    @Autowired
    private RepositorioBarras insertBarras;
    @Autowired
    private RepositorioProduto insertProduto;
    @Autowired
    private RepositorioInsertFornecedor insertFornecedor;
    @Autowired
    private RepositorioInsertComprador insertComprador;
    @Autowired
    private RepositorioInsertLinhaSub insertLinhaSub;
    @Autowired
    private RepositorioInsertFilial insertFilial;

    public String registrarProduto(DadosProduto dadosProduto) {
        List<Integer> resultado = repositorioCodigo.getUltimoCodigo();
        dadosProduto.setCodigoProduto(resultado.get(0));
        System.out.println(insertProduto.insertProduto(dadosProduto));
        System.out.println(insertBarras.insertBarras(dadosProduto));
        System.out.println(insertFornecedor.inserirCodFornecedorNaFilial(dadosProduto));
        System.out.println(insertComprador.inserirComprador(dadosProduto));
        System.out.println(insertLinhaSub.inserirArvoreMercadologica(dadosProduto));
        System.out.println(insertFilial.inserirNaFilial(dadosProduto,1));


        return "Produto Inserido com sucesso.";
    }


}
