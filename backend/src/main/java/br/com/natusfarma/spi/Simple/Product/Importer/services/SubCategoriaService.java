package br.com.natusfarma.spi.Simple.Product.Importer.services;
import br.com.natusfarma.spi.Simple.Product.Importer.models.SubCategoria;
import br.com.natusfarma.spi.Simple.Product.Importer.repositorio.RepositorioSubCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoriaService {
    @Autowired
    private RepositorioSubCategoria repositorioSubCategoria;
    public List<SubCategoria> consultaNome(String nome){
        return repositorioSubCategoria.consultaNome(nome);
    }
    public List<SubCategoria> consultaCodigo(String codigo){
        return repositorioSubCategoria.consultaCodigo(codigo);
    }
}
