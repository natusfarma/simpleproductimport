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
    return this.http.post<DadosCabecalhoXml>('/api/upload-xml', formData);
  }

  getXml(){
    return this.http.get<DadosCabecalhoXml>('/api/getXml');
  }

  getAtributos(){
    return  [
    {nome:"1-Fornecedor",tipo:"sub-titulo"},  
    {nome:'Fornecedor',xml:'xNome'},
    {nome:'Descrição',xml:'xProd'},
    {nome:'Principio ativo',xml:''},
    {nome:'Forma farmacêutica',xml:''},
    {nome:'Codigo produto no fornecedor',xml:'cProd'},
    {nome:'Linha fornecedor',xml:''},
    {nome:'Tarja',xml:''},
    {nome:'Código de barras(EAN)',xml:'cEAN'},
    {nome:'Nº Registro MS',xml:'cProdANVISA'},
    {nome:'Referência comercial',xml:''},
    {nome:'Classificação terapeutica',xml:''},
    {nome:'Psicotrópico port 344/98',xml:''},
    {nome:'Classificação tipo receita',xml:''},
    {nome:'Unidade farmacotécnica',xml:''},
    {nome:'Código DCB',xml:''},
    {nome:'Fabricante',xml:''},
    {nome:'CNPJ Fabricante',xml:''},
    {nome:'Procedência/Origem',xml:''},
    {nome:'NCM',xml:'NCM'},
    {nome:'Classificação fiscal',xml:''},
    {nome:'Cest',xml:'CEST'},
    {nome:'Lista PIS/Cofins',xml:''},
    {nome:'Regime',xml:''},
    {nome:'Produto com IPI',xml:''},
    {nome:'Refrigeração',xml:''},
    {nome:'Embalagem de faturamento fornecedor',xml:'uCom'},
    {nome:'Preço custo',xml:'vUnCom'},
    {nome:'Preço fábrica',xml:''},
    {nome:'PMC',xml:''},
    {nome:'EAN da caixa fechada',xml:''},
    {nome:'Quantidade fardo',xml:''},
    {nome:'Caixa de embarque',xml:''},
    {nome:'Altura',xml:''},
    {nome:'Largura',xml:''},
    {nome:'Comprimento',xml:''},
    {nome:'Peso',xml:''},

    {nome:"2-Compras",tipo:"sub-titulo"},
    {nome:'Comprador',xml:''},
    {nome:'Suprimento ou Ressuprimento',xml:''},
    {nome:'Linha comprador',xml:''},
    {nome:'Código do produto/fornecedor',xml:'cProd'},

    {nome:"3-Gestão Categorias",tipo:"sub-titulo"},
    {nome:'Descrição definida',xml:''},
    {nome:'Departamente',xml:''},
    {nome:'Categoria',xml:''},
    {nome:'SubCategoria',xml:''},
    {nome:'Segmento',xml:''},
    {nome:'Equivalência',xml:''},
    {nome:'Classificação',tipo:"classificacao"},

    {nome:"4-Contabilidade",tipo:"sub-titulo"},
    {nome:'CST(ICMS)',xml:''},
    {nome:'CST(PIS)',xml:''},
    {nome:'CST(COFINS)',xml:''},
    {nome:'ALIQUOTA(ICMS)',xml:''},
    {nome:'ALIQUOTA(PIS)',xml:''},
    {nome:'ALIQUOTA(COFINS)',xml:''},
    {nome:'Código interno',xml:'' },

    {nome:"5-Precificação",tipo:"sub-titulo"},
    {nome:'Papel',xml:''},
    {nome:'Familia',xml:''},
    {nome:'Marca',xml:''},
    {nome:'Preço venda',xml:''},
    {nome:'Margem',xml:'' },

    {nome:"6-Gestão Comercial",tipo:"sub-titulo"},
    {nome:'Média P',xml:''}

  ]
  }



}
