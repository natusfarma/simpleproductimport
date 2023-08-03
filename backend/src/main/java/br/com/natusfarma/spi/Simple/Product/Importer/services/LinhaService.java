package br.com.natusfarma.spi.Simple.Product.Importer.services;

import br.com.natusfarma.spi.Simple.Product.Importer.models.ModeloPadrao;
import br.com.natusfarma.spi.Simple.Product.Importer.repositorio.RepositorioLinha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LinhaService {
    @Autowired
    private RepositorioLinha repositorioLinha;
    public List<ModeloPadrao> consultaNome(String nome){
        return repositorioLinha.consultaNome(nome);
    }
    public List<ModeloPadrao> consultaCodigo(String codigo){
        return repositorioLinha.consultaCodigo(codigo);
    }
}
