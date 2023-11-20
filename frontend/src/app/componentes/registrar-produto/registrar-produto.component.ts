import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from "@angular/forms"
import { DadosCabecalhoXml } from 'src/app/modals/dadosCabecalhoXml';
import { DadosProduto } from 'src/app/modals/dadosProduto';
import { ItemNotaXml } from "src/app/modals/ItemNotaXml"
import { ModeloPadrao } from 'src/app/modals/modeloPadrao';
import { ProdutoXml } from 'src/app/modals/produtoXml';
import { ProdutosService } from 'src/app/services/produtos.service';
@Component({
  selector: 'app-registrar-produto',
  templateUrl: './registrar-produto.component.html',
  styleUrls: ['./registrar-produto.component.css']
})
export class RegistrarProdutoComponent implements OnInit {

  form!: FormGroup;

  atributos: Array<ProdutoXml> = []
  dadosXml!: DadosCabecalhoXml;
  notaXml!: ItemNotaXml;
  atributo!: ProdutoXml;
  categoria!: ProdutoXml;
  subCategoria!: ProdutoXml;
  usuario!: ModeloPadrao;
  camposInvalidos: String[] = []
  desabilitado: boolean = false
  mensagem: String = "";
  corAlerta: String = "";
  carregando: boolean = false;
  constructor(private fb: FormBuilder, private service: ProdutosService) {

  }

  ngOnInit(): void {
    this.criarFormulario()
    this.usuario = this.service.usuario;
    this.dadosXml = this.service.objetoSelecionado;
    if (this.dadosXml && this.service.cadastroComXml) {
      this.notaXml = this.dadosXml.itensNotaXml[this.service.notaIndex]
      this.preencherCampos(this.dadosXml)
      this.preencherCampos(this.notaXml)
      let fornecedor = this.service.fornecedor;
      this.atributos[1].codigo = fornecedor.codigo
      this.form.get(this.atributos[1].nome)?.setValue(fornecedor.nome)
    }
  }

  criarFormulario() {
    this.form = this.fb.group({})
    this.atributos = this.service.getAtributos()
    this.atributos.forEach(atr => {
      if (atr.tipo != "sub-titulo") {
        this.form.addControl(atr.nome, this.fb.control(''))
        if (atr.nomeCadastro) {
          this.form.get(atr.nome)?.setValidators(Validators.required)
        }
        if (atr.campoBusca || atr.nome == "Categoria" || atr.nome == "Departamento" || atr.nome == "Código interno") {
          this.form.get(atr.nome)?.disable()
        }
      }
    })
  }

  preencherCampos(obj: any) {
    for (const key in obj) {
      for (let i = 0; i < this.atributos.length; i++) {
        const e = this.atributos[i];
        if (key == e.xml) {
          this.form.get(e.nome)?.setValue(obj[key])
        }
      }
    }
  }

  gerarExcel(conteudo: string) {
    const fileName = "nota_" + (this.dadosXml?.nNF ?? 0) + "_" + (this.notaXml?.cProd ?? 0) + '.csv';
    const blob = new Blob([conteudo], { type: 'text/csv;charset=utf-8;' });
    const link = document.createElement('a');
    const formData = new FormData();
    formData.append('file', blob, fileName);
    //this.service.gerarExcel(formData, fileName);
    link.href = URL.createObjectURL(blob);
    link.download = fileName;
    link.click();
  }
  excelLinhas(): string {
    let result = '\uFEFF' + this.atributos.map(atr => atr.nome).join('; ') + "\n";
    this.atributos.forEach(atr => {
      if (atr.tipo != "sub-titulo") {
        if (atr.opcoes) {
          result += atr.opcoes[this.form.get(atr.nome)?.value] ?? "";
        } else {
          result += this.form.get(atr.nome)?.value;
          if (atr.codigo) {
            result += " - " + atr.codigo;
          }
        }
      }
      result += ";";
    })
    return result;
  }

  excelColunas(): string {    
    let result = '\uFEFF';
    this.atributos.forEach(atr => {
      result += atr.nome;
      if (atr.tipo != "sub-titulo")
        if (atr.opcoes) {
          result += ';' + atr.opcoes[this.form.get(atr.nome)?.value] ?? "";
        } else {
          result += ";" + this.form.get(atr.nome)?.value;
          if (atr?.codigo) {
            result += " - " + atr.codigo;
          }
        }
      result += "\n";
    });
    return result.toUpperCase();
  }


  exportar() {
    this.carregando = true;
    let payload = this.montarPayload();
    this.service.registrarProduto(payload).subscribe({
      next: (res: any) => {
        this.carregando = false
        this.corAlerta = "success"
        this.mensagem = "Produto inserido com Código interno:" + res.codigo
        this.desabilitado = true;
        //this.form.get("Código interno")?.setValue(res.codigo);
        this.service.atualizarCodigo(res.codigo, payload.barras)
      },
      error: (e: HttpErrorResponse) => {
        this.carregando = false
        this.corAlerta = "danger"
        if (e.status === 409) {
          this.desabilitado = true
          this.mensagem = e.error
        } else {
          this.mensagem = "Ocorreu algum erro"
        }
      }
    })
  }
  montarPayload(): DadosProduto {
    const dados: any = new DadosProduto();
    for (let i = 0; i < this.atributos.length; i++) {
      const atr = this.atributos[i];
      if (atr.nomeCadastro) {
        if (atr.codigo) {
          dados[atr.nomeCadastro] = atr.codigo;
        } else {
          let a = this.form.get(atr.nome)?.value;
          if (a == "") {
            dados[atr.nomeCadastro] = null
          } else {
            dados[atr.nomeCadastro] = this.form.get(atr.nome)?.value
          }
        }
      }
    }
    dados.cd_USU = this.usuario.codigo;
    return dados;
  }


  buscarOpcoes(atributo: ProdutoXml) {
    atributo.mostrarOpcoes = true;
    this.atributo = atributo;
  }

  editarCodigo(codigo: any) {
    for (let i = 0; i < this.atributos.length; i++) {
      const atr = this.atributos[i];
      if (atr.nome == codigo.nome) {
        atr.codigo = codigo.codigo
      }
    }
  }

  verificarBarras() {
    const barras = this.form.get("Código de barras(EAN)")?.value
    const codFornecedor = this.atributos[1].codigo
    const codProduto = this.form.get("Código do produto/fornecedor")?.value
    if (!barras || !codFornecedor || !codProduto) {
      this.mensagem = "Preencha os campos:\n-Fornecedor\n-Código de barras(EAN)\n-Código do produto/fornecedor"
      this.corAlerta = "danger"
    } else {
      this.service.verificarBarras(barras, codFornecedor, codProduto)
        .subscribe({
          next: (res: String) => {
            this.mensagem = res
            this.corAlerta = "info"
          },
          error: (e) => {
            this.corAlerta = "danger"
            this.mensagem = "Ocorreu algum erro ao verificar"
          },
        })
    }
  }

  resetarForm(){
    this.form.reset()
    this.atributos.forEach(atr =>{
      atr.codigo = undefined;
    })
  }
}


