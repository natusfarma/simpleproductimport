package br.com.natusfarma.spi.Simple.Product.Importer.repositorio;

import br.com.natusfarma.spi.Simple.Product.Importer.models.ModeloPadrao;
import br.com.natusfarma.spi.Simple.Product.Importer.uteis.Consulta;
import br.com.natusfarma.spi.Simple.Product.Importer.uteis.FileStringUtil;
import br.com.natusfarma.spi.Simple.Product.Importer.uteis.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;
@Repository
public class RepositorioLinha {
    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
    private RowMapper rowMapper;
    @Autowired
    private Consulta consulta;
    private File file = new File("arquivosSql/linha.sql");
    private String stringQuery = FileStringUtil.FileToString(file);

    public List<ModeloPadrao> consultaNome(String nome){
        String query = stringQuery +
                String.format(" AND DS_ARV_MERC_LINHA LIKE '%s'", "%" + nome + "%");
        return consulta.consulta(query,jdbc,(rs,row)-> rowMapper.modeloPadrao(rs));
    }
    public List<ModeloPadrao> consultaCodigo(String codigo){
        String query = stringQuery +
                String.format(" AND CD_ARV_MERC_LINHA = %s",codigo);
        return consulta.consulta(query,jdbc,(rs,row)-> rowMapper.modeloPadrao(rs));
    }
}
