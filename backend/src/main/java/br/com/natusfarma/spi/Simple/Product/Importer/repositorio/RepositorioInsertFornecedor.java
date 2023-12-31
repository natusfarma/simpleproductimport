package br.com.natusfarma.spi.Simple.Product.Importer.repositorio;

import br.com.natusfarma.spi.Simple.Product.Importer.models.DadosProduto;
import br.com.natusfarma.spi.Simple.Product.Importer.uteis.Consulta;
import br.com.natusfarma.spi.Simple.Product.Importer.uteis.FileStringUtil;
import br.com.natusfarma.spi.Simple.Product.Importer.uteis.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public class RepositorioInsertFornecedor {
    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
    private RowMapper rowMapper;
    @Autowired
    private Consulta consulta;
    private File file = new File("arquivosSql/insert-codFornecedor.sql");
    private String stringQuery = FileStringUtil.FileToString(file);

    public String inserirCodFornecedorNaFilial(DadosProduto dadosProduto){
        String query = stringQuery
                .replace("codigoProduto",String.valueOf(dadosProduto.getCodigoProduto()))
                .replace("codigoProdFornecedor",String.valueOf(dadosProduto.getCd_produtoFornecedor()))
                .replace("codigoUsuario",String.valueOf(dadosProduto.getCd_USU()))
                .replace("codigoFornecedor",String.valueOf(dadosProduto.getCd_fornecedor()));

        return query;
    }
}
