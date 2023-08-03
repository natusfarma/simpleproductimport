package br.com.natusfarma.spi.Simple.Product.Importer.services;

import br.com.natusfarma.spi.Simple.Product.Importer.models.ModeloPadrao;
import br.com.natusfarma.spi.Simple.Product.Importer.repositorio.RepositorioFabricante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FabricanteService {
    @Autowired
    private RepositorioFabricante repositorioFabricante;
    public List<ModeloPadrao> consultaNome(String nome){
        return repositorioFabricante.consultaNome(nome);
    }
    public List<ModeloPadrao> consultaCodigo(String codigo){
        return repositorioFabricante.consultaCodigo(codigo);
    }
}
