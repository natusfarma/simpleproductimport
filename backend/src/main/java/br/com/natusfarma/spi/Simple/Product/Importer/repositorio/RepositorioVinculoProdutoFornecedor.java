package br.com.natusfarma.spi.Simple.Product.Importer.repositorio;

import br.com.natusfarma.spi.Simple.Product.Importer.models.ModeloPadrao;
import br.com.natusfarma.spi.Simple.Product.Importer.uteis.Consulta;
import br.com.natusfarma.spi.Simple.Product.Importer.uteis.FileStringUtil;
import br.com.natusfarma.spi.Simple.Product.Importer.uteis.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;

@Repository
public class RepositorioVinculoProdutoFornecedor {
    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
    private RowMapper rowMapper;
    @Autowired
    private Consulta consulta;
    private File file = new File("arquivosSql/vinculoProdutoComFornecedor.sql");
    private String stringQuery = FileStringUtil.FileToString(file);
    public List<String> consultaCodigo(Long codigoProduto,int codigoFornecedor){
        String query = stringQuery
                .replace("codigoProduto",String.valueOf(codigoProduto))
                .replace("codigoFornecedor",String.valueOf(codigoFornecedor));
        return consulta.consulta(query,jdbc,(rs,row)-> rowMapper.modeloString(rs));
    }
}
