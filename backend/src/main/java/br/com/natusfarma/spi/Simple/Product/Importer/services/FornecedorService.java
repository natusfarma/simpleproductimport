package br.com.natusfarma.spi.Simple.Product.Importer.services;

import br.com.natusfarma.spi.Simple.Product.Importer.models.ModeloPadrao;
import br.com.natusfarma.spi.Simple.Product.Importer.repositorio.RepositorioFornecedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    @Autowired
    private RepositorioFornecedor repositorioFornecedor;
    public List<ModeloPadrao> consultaCnpj(String cnpj){
        return repositorioFornecedor.consultaCnpj(cnpj);
    }
    public List<ModeloPadrao> consultaNome(String nome){
        return repositorioFornecedor.consultaNome(nome);
    }
    public List<ModeloPadrao> consultaCodigo(String codigo){
        return repositorioFornecedor.consultaCodigo(codigo);
    }
}
