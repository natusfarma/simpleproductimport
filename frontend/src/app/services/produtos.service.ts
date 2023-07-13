import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DadosCabecalhoXml } from '../modals/dadosCabecalhoXml';

@Injectable({
  providedIn: 'root'
})
export class ProdutosService {

  objetoSelecionado!:DadosCabecalhoXml;
  notaIndex:number = 0;

  constructor(private http:HttpClient) { }

  lerXml(arquivo:File):Observable<DadosCabecalhoXml>{
    const formData = new FormData();
    formData.append('file',arquivo);
    return this.http.post<DadosCabecalhoXml>('/upload-xml', formData);
  }

  gerarExcel(conteudo:FormData,nome:string){
    this.http.post(`/upload-excel/?nome=${nome}`,conteudo).subscribe(res => console.log(res)
    )
  }
  buscarOpcoes(valor:string,campoBusca:string){
    return this.http.get(`/${campoBusca}/${valor}`);
  }

  getAtributos(){
    return  [
    {nome:"1-Fornecedor",tipo:"sub-titulo"},  
    {nome:'Fornecedor',xml:'xNome',campoBusca:'fornecedores'},
    {nome:'Descrição',xml:'xProd'},
    {nome:'Principio ativo'},
    {nome:'Forma farmacêutica'},
    {nome:'Codigo produto no fornecedor',xml:'cProd'},
    {nome:'Linha fornecedor',campoBusca:'linhas'},
    {nome:'Tarja'},
    {nome:'Código de barras(EAN)',xml:'cEAN'},
    {nome:'Nº Registro MS',xml:'cProdANVISA'},
    {nome:'Referência comercial'},
    {nome:'Classificação terapeutica'},
    {nome:'Psicotrópico port 344/98'},
    {nome:'Classificação tipo receita'},
    {nome:'Unidade farmacotécnica'},
    {nome:'Código DCB'},
    {nome:'Fabricante',campoBusca:'fabricantes'},
    {nome:'CNPJ Fabricante'},
    {nome:'Procedência/Origem'},
    {nome:'NCM',xml:'ncm'},
    {nome:'Classificação fiscal',xml:'ncm'},
    {nome:'Cest',xml:'cest'},
    {nome:'Lista PIS/Cofins'},
    {nome:'Regime'},
    {nome:'Produto com IPI'},
    {nome:'Refrigeração'},
    {nome:'Embalagem de faturamento fornecedor',xml:'uCom'},
    {nome:'Preço custo',xml:'vUnCom'},
    {nome:'Preço fábrica'},
    {nome:'PMC'},
    {nome:'EAN da caixa fechada'},
    {nome:'Quantidade fardo'},
    {nome:'Caixa de embarque'},
    {nome:'Altura'},
    {nome:'Largura'},
    {nome:'Comprimento'},
    {nome:'Peso'},

    {nome:"2-Compras",tipo:"sub-titulo"},
    {nome:'Comprador'},
    {nome:'Suprimento ou Ressuprimento'},
    {nome:'Linha comprador'},
    {nome:'Código do produto/fornecedor',xml:'cProd'},

    {nome:"3-Gestão Categorias",tipo:"sub-titulo"},
    {nome:'Descrição definida'},
    {nome:'Departamente'},
    {nome:'Categoria'},
    {nome:'SubCategoria'},
    {nome:'Segmento'},
    {nome:'Equivalência'},
    {nome:'Classificação',tipo:"classificacao"},

    {nome:"4-Contabilidade",tipo:"sub-titulo"},
    {nome:'CST(ICMS)'},
    {nome:'CST(PIS)'},
    {nome:'CST(COFINS)'},
    {nome:'ALIQUOTA(ICMS)'},
    {nome:'ALIQUOTA(PIS)'},
    {nome:'ALIQUOTA(COFINS)'},
    {nome:'Código interno' },

    {nome:"5-Precificação",tipo:"sub-titulo"},
    {nome:'Papel'},
    {nome:'Familia'},
    {nome:'Marca'},
    {nome:'Preço venda'},
    {nome:'Margem' },

    {nome:"6-Gestão Comercial",tipo:"sub-titulo"},
    {nome:'Média P'}

  ]
  }



}
