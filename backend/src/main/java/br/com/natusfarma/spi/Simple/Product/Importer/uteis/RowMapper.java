package br.com.natusfarma.spi.Simple.Product.Importer.uteis;

import br.com.natusfarma.spi.Simple.Product.Importer.models.ModeloPadrao;
import br.com.natusfarma.spi.Simple.Product.Importer.models.SubCategoria;
import br.com.natusfarma.spi.Simple.Product.Importer.repositorio.RepositorioSubCategoria;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RowMapper {

    public ModeloPadrao modeloPadrao(ResultSet rs) {
        ModeloPadrao modeloPadrao = new ModeloPadrao();
        try {

            modeloPadrao.setCodigo(rs.getInt("codigo"));
            modeloPadrao.setNome(rs.getString("nome"));
        } catch (SQLException e) {
            e.getMessage();
        }
        return modeloPadrao;
    }

    public SubCategoria subCategoria(ResultSet rs){
        SubCategoria subCategoria = new SubCategoria();
        try{
            subCategoria.setCategoria(rs.getString("CATEGORIA"));
            subCategoria.setSubCategoria(rs.getString("SUBCATEGORIA"));
            subCategoria.setDepartamento(rs.getString("DEPARTAMENTO"));
            subCategoria.setCodigo(rs.getInt("CODIGO"));
            subCategoria.setCodCategoria(rs.getInt("COD_CATEGORIA"));
            subCategoria.setCodDepartamento(rs.getInt("COD_DEPARTAMENTO"));
        }catch (SQLException e){
            e.getMessage();
        }
        return subCategoria;
    }

    public String modeloString(ResultSet rs){
        try{
            return rs.getString("CD_PROD");
        }catch(SQLException e){
            e.getMessage();
        }
        return "";
    }

    public int modeloInt(ResultSet rs){
        try{
            return rs.getInt(1);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

}