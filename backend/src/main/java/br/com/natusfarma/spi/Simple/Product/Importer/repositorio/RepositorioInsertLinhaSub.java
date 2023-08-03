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
public class RepositorioInsertLinhaSub {
    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
    private RowMapper rowMapper;
    @Autowired
    private Consulta consulta;
    private File file = new File("arquivosSql/insert-linha-subcategoria.sql");
    private String stringQuery = FileStringUtil.FileToString(file);

    public String inserirArvoreMercadologica(DadosProduto dadosProduto) {
        String query = stringQuery
                .replace("codigoProduto",String.valueOf(dadosProduto.getCodigoProduto()))
                .replace("codigoCategoria",String.valueOf(dadosProduto.getCd_categoria()))
                .replace("codigoLinha",String.valueOf(dadosProduto.getCd_linha()))
                .replace("codigoUsuario",String.valueOf(dadosProduto.getCd_USU()))
                .replace("codigoSubCategoria",String.valueOf(dadosProduto.getCd_subCategoria()));
        return query;
    }
}
