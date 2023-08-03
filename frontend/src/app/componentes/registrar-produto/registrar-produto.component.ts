import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl, MaxLengthValidator } from "@angular/forms"
import { ActivatedRoute } from '@angular/router';
import { DadosCabecalhoXml } from 'src/app/modals/dadosCabecalhoXml';
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
  camposInvalidos:String[] = []
  constructor(private fb: FormBuilder, private service: ProdutosService) {

  }

  ngOnInit(): void {
    this.criarFormulario()
    this.usuario = this.service.usuario;
    this.dadosXml = this.service.objetoSelecionado;

    if (this.dadosXml) {
      this.notaXml = this.dadosXml.itensNotaXml[this.service.notaIndex]
      this.preencherCampos(this.dadosXml)
      this.preencherCampos(this.notaXml)
      if (this.dadosXml.fornecedores.length > 1) {
        this.buscarOpcoes(this.atributos[1])
      } else {
        let fornecedor = this.dadosXml.fornecedores[0];
        this.atributos[1].codigo = fornecedor.codigo
        this.form.get(this.atributos[1].nome)?.setValue(fornecedor.nome)
      }
    }

  }

  criarFormulario() {
    this.form = this.fb.group({})
    this.atributos = this.service.getAtributos()
    this.atributos.forEach(atr => {
      if (atr.tipo != "sub-titulo") {
        if (atr.nomeCadastro) {
          this.form.addControl(atr.nome, this.fb.control([], Validators.required))
        } else {
          this.form.addControl(atr.nome, this.fb.control(''))
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

  gerarExcel() {
    let conteudo = this.obterDados();
    const fileName = "nota_" + this.dadosXml.nNF + "_" + this.notaXml.cProd + '.csv';
    const blob = new Blob([conteudo], { type: 'text/csv;charset=utf-8;' });
    const link = document.createElement('a');
    const formData = new FormData();
    formData.append('file', blob, fileName);
    this.service.gerarExcel(formData, fileName);
    link.href = URL.createObjectURL(blob);
    link.download = fileName;
    link.click();
  }

  obterDados(): string {
    let result = '\uFEFF';
    this.atributos.forEach(atr => {
      result += atr.nome;
      if (atr.tipo != "sub-titulo")
        if (atr.opcoes) {
          result += ';' + atr.opcoes[this.form.get(atr.nome)?.value];
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
      let cadastroForm = this.fb.group({});
      for (let i = 0; i < this.atributos.length; i++) {
        const atr = this.atributos[i];
        if (atr.nomeCadastro) {
          cadastroForm.addControl(atr.nomeCadastro, this.fb.control(atr.codigo || this.form.get(atr.nome)?.value))
        }
      }
      cadastroForm.setControl('cd_USU', this.fb.control(this.usuario.codigo));
      this.service.registrarProduto(cadastroForm).subscribe({
        next(res: any) {
          alert(res.message);
        },
        error(error: HttpErrorResponse) {
          if (error.status === 409) {
            alert(error.error);
          } else {
            alert(error.error);
          }
        }
      })
  }

  buscarOpcoes(atributo: ProdutoXml) {
    atributo.mostrarOpcoes = true;
    this.atributo = atributo;
  }

  editarCodigo(codigo:any){
    console.log(codigo);

    for (let i = 0; i < this.atributos.length; i++) {
      const atr = this.atributos[i];
      if(atr.nome == codigo.nome){
        atr.codigo = codigo.codigo
      }
    }
  }

}
