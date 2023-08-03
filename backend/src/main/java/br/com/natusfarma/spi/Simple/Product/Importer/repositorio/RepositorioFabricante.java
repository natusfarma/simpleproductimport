package br.com.natusfarma.spi.Simple.Product.Importer.repositorio;

import br.com.natusfarma.spi.Simple.Product.Importer.models.ModeloPadrao;
import br.com.natusfarma.spi.Simple.Product.Importer.uteis.Consulta;
import br.com.natusfarma.spi.Simple.Product.Importer.uteis.FileStringUtil;
import br.com.natusfarma.spi.Simple.Product.Importer.uteis.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class RepositorioFabricante {
    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
    private RowMapper rowMapper;
    @Autowired
    private Consulta consulta;
    private File file = new File("arquivosSql/fabricante.sql");
    private String stringQuery = FileStringUtil.FileToString(file);
    public List<ModeloPadrao> consultaNome(String nome){
        String query = stringQuery +
                String.format(" NM_FABRIC like '%s'", "%" + nome + "%");
        return consulta.consulta(query,jdbc,(rs,row)-> rowMapper.modeloPadrao(rs));
    }
    public List<ModeloPadrao> consultaCodigo(String codigo){
        String query = stringQuery +
                String.format(" CD_FABRIC = %s",codigo);
        return consulta.consulta(query,jdbc,(rs,row)-> rowMapper.modeloPadrao(rs));
    }
}
