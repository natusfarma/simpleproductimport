import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DadosCabecalhoXml } from '../modals/dadosCabecalhoXml';
import { DadosProduto } from '../modals/dadosProduto';
import { ItemNotaXml } from '../modals/ItemNotaXml';
import { ModeloPadrao } from '../modals/modeloPadrao';

@Injectable({
  providedIn: 'root'
})
export class ProdutosService {

  cadastroComXml: boolean = true;
  objetoSelecionado!: DadosCabecalhoXml;
  notaIndex: number = 0;
  usuario!: ModeloPadrao;
  fornecedor!: ModeloPadrao;

  constructor(private http: HttpClient) { }

  lerXml(arquivo: File): Observable<DadosCabecalhoXml> {
    const formData = new FormData();
    formData.append('file', arquivo);
    return this.http.post<DadosCabecalhoXml>('/api/upload-xml', formData);
  }

  gerarExcel(conteudo: FormData, nome: string) {
    this.http.post(`/api/upload-excel/?nome=${nome}`, conteudo).subscribe(res => console.log(res)
    )
  }
  buscarOpcoes(valor: string, campoBusca: string) {
    return this.http.get(`/api/${campoBusca}/${valor}`);
  }

  registrarProduto(payload: DadosProduto) {
    return this.http.post("/api/registrar/produto", payload);
  }
  atualizarStatus(dadosCabecalhoXml: DadosCabecalhoXml, index: number) {
    return this.http.post<ItemNotaXml[]>(`api/status/${index}`, dadosCabecalhoXml)
  }

  atualizarCodigo(codigo: number, barras: string) {
    const item = this.objetoSelecionado.itensNotaXml.find(item => item.cEAN == barras);
    if (item) {
      item.codigoProduto = codigo
      item.status = "ok"
    }
  }

  verificarBarras(barras: string, codFornecedor: number, codProduto: string) {
    return this.http.get<String>("/api/registrar/verificar", {
      params: {
        barras: barras,
        codProduto: codProduto,
        codFornecedor: codFornecedor
      },
      responseType: 'text' as 'json'
    })
  }

  getAtributos() {
    return [
      { nome: "1-Fornecedor", tipo: "sub-titulo" },
      { nome: 'Fornecedor', xml: 'xNome', campoBusca: 'fornecedores', nomeCadastro: "cd_fornecedor" },
      { nome: 'Descrição', xml: 'xProd' },
      { nome: 'Principio ativo' },
      { nome: 'Forma farmacêutica' },
      { nome: 'Codigo produto no fornecedor', xml: 'cProd' },
      { nome: 'Linha fornecedor' },
      { nome: 'Tarja' },
      { nome: 'Código de barras(EAN)', xml: 'cEAN', nomeCadastro: 'barras' },
      { nome: 'Nº Registro MS', xml: 'cProdANVISA' },
      { nome: 'Referência comercial' },
      { nome: 'Classificação terapeutica' },
      { nome: 'Psicotrópico port 344/98' },
      { nome: 'Classificação tipo receita' },
      { nome: 'Unidade farmacotécnica' },
      { nome: 'Código DCB' },
      { nome: 'Fabricante', campoBusca: 'fabricantes', nomeCadastro: 'cd_FABRIC' },
      { nome: 'CNPJ Fabricante' },
      { nome: 'Procedência/Origem' },
      { nome: 'NCM', xml: 'ncm', nomeCadastro: 'nr_NCM' },
      { nome: 'Classificação fiscal', xml: 'ncm' },
      { nome: 'Cest', xml: 'cest', nomeCadastro: 'nr_CEST' },
      { nome: 'Lista PIS/Cofins' },
      { nome: 'Regime' },
      { nome: 'Produto com IPI', xml: "vIPI" },
      { nome: 'Refrigeração' },
      { nome: 'Embalagem de faturamento fornecedor', xml: 'uCom' },
      { nome: 'Preço custo', xml: 'precoCusto' },
      { nome: 'Preço fábrica' },
      { nome: 'PMC' },
      { nome: 'EAN da caixa fechada' },
      { nome: 'Quantidade fardo' },
      { nome: 'Caixa de embarque' },
      { nome: 'Altura' },
      { nome: 'Largura' },
      { nome: 'Comprimento' },
      { nome: 'Peso' },

      { nome: "2-Compras", tipo: "sub-titulo" },
      { nome: 'Comprador', campoBusca: 'comprador', nomeCadastro: "cd_comprador" },
      { nome: 'Suprimento ou Ressuprimento', tipo: 'radio', nomeCadastro: "suprimento", opcoes: ['Ressuprimento', ' Suprimento', 'Suprimento/Ressuprimento'] },
      { nome: 'Linha comprador', campoBusca: 'linhas', nomeCadastro: "cd_linha" },
      { nome: 'Código do produto/fornecedor', xml: 'cProd', nomeCadastro: "cd_produtoFornecedor" },

      { nome: "3-Gestão Categorias", tipo: "sub-titulo" },
      { nome: 'Descrição usual', xml: 'xProd', nomeCadastro: 'ds_PROD' },
      { nome: 'Descrição reduzida', nomeCadastro: 'ds_USUAL', maxlength: 48 },
      { nome: 'SubCategoria', campoBusca: "codigo", nomeCadastro: "cd_subCategoria" },
      { nome: 'Categoria', nomeCadastro: "cd_categoria" },
      { nome: 'Departamento' },
      { nome: 'Segmento' },
      { nome: 'Equivalência' },
      { nome: 'Classificação', tipo: "radio", opcoes: ['Genérico', 'Similar', 'RX(Ético)'] },

      { nome: "4-Contabilidade", tipo: "sub-titulo" },
      { nome: 'CST(ICMS)' },
      { nome: 'CST(PIS)' },
      { nome: 'CST(COFINS)' },
      { nome: 'ALIQUOTA(ICMS)' },
      { nome: 'ALIQUOTA(PIS)' },
      { nome: 'ALIQUOTA(COFINS)' },
      { nome: 'Código interno' },

      { nome: "5-Precificação", tipo: "sub-titulo" },
      { nome: 'Papel' },
      { nome: 'Familia' },
      { nome: 'Marca' },
      { nome: 'Preço venda' },
      { nome: 'Margem' },

      { nome: "6-Gestão Comercial", tipo: "sub-titulo" },
      { nome: 'Média P' }

    ]
  }



}
