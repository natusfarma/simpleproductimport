import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from "@angular/forms"
import { ActivatedRoute} from '@angular/router';
import { DadosCabecalhoXml } from 'src/app/modals/dadosCabecalhoXml';
import { ItemNotaXml } from "src/app/modals/ItemNotaXml"
import { ProdutoXml } from 'src/app/modals/produto';
import { ProdutosService } from 'src/app/services/produtos.service';
@Component({
  selector: 'app-registrar-produto',
  templateUrl: './registrar-produto.component.html',
  styleUrls: ['./registrar-produto.component.css']
})
export class RegistrarProdutoComponent implements OnInit {

  form!: FormGroup;

  atributos: Array<ProdutoXml> = []
  atributosXml!:DadosCabecalhoXml;
  atributosNotasXml!:ItemNotaXml;
  atributo!:ProdutoXml;
  constructor(private fb: FormBuilder, private service: ProdutosService) {

  }

  ngOnInit(): void {
   
    this.form = this.fb.group({})
    
    this.criarFormulario()
    
    this.atributosXml = this.service.objetoSelecionado;
    if(this.atributosXml){
      this.atributosNotasXml = this.atributosXml.itensNotaXml[this.service.notaIndex]
      this.preencherCampos(this.atributosXml)
      this.preencherCampos(this.atributosNotasXml)
    }
      if(this.atributosXml.fornecedores.length > 1){
        this.buscarOpcoes(this.atributos[1])
      }else{
        let fornecedor = this.atributosXml.fornecedores[0];
        this.form.get(this.atributos[1].nome)?.setValue(fornecedor.codigo + " - " +fornecedor.nome )
      }
  }

  criarFormulario() {
    this.atributos = this.service.getAtributos()
    this.atributos.forEach(atr =>{
      if(atr.tipo != "sub-titulo"){
        this.form.addControl(atr.nome,this.fb.control(''))
      }
    })
  }

  preencherCampos(obj:any){
    for (const key in obj) {
      for (let i = 0; i < this.atributos.length; i++) {
        const e = this.atributos[i];
        if(key == e.xml){
          this.form.get(e.nome)?.setValue(obj[key])
        }
      }
    }
  }



  gerarExcel() {
    
    let conteudo = this.obterDados();
    const fileName = "nota_" + this.atributosXml.nNF + "_" + this.atributosNotasXml.cProd + '.csv';
    const blob = new Blob([conteudo], { type: 'text/csv;charset=utf-8;' });
    const link = document.createElement('a');
    const formData = new FormData();
    formData.append('file', blob, fileName);
    this.service.gerarExcel(formData,fileName);
   
    link.href = URL.createObjectURL(blob);
    link.download = fileName;
    link.click();
  }

  obterDados(): string {
    let result = '\uFEFF';
    this.atributos.forEach(a => {
      if(a.tipo === 'sub-titulo'){
        result += a.nome +"\n"
      }else{
        result += a.nome + ";";
        if(a?.codigo){
          result += a.codigo + " - ";
        }
        result += this.form.get(a.nome)?.value +"\n";
      }
      
    });
    return result
  }


  buscarOpcoes(atributo:ProdutoXml){
    atributo.mostrarOpcoes = true;
    this.atributo = atributo;
    //let valor = this.form.get(atributo.nome)?.value;
    //this.service.buscarOpcoes(valor,campoBusca).subscribe(res => {
    //atributo.listaOpcoes = res;
    //});
  }

}
