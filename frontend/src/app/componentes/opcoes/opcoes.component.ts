import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ModeloPadrao } from 'src/app/modals/modeloPadrao';
import { ProdutoXml } from 'src/app/modals/produto';
import { ConsultaCamposService } from 'src/app/services/consulta-campos.service';

@Component({
  selector: 'app-opcoes',
  templateUrl: './opcoes.component.html',
  styleUrls: ['./opcoes.component.css']
})
export class OpcoesComponent implements OnInit {

  @Input() form!:FormGroup;
  @Input() atributo!:ProdutoXml;
  @Input() list:ModeloPadrao[] = [];
  tipo:string = 'nome';
  campoBusca:string = '';
  mensagemErro:string = "";
  carregando:boolean = false;
    
  constructor(private service:ConsultaCamposService){}
  ngOnInit(): void {
    if(this.atributo.nome != "Fornecedor"){
      this.list = []
    }
  }

  buscar(){
    if(this.campoBusca != "" && this.atributo.campoBusca){
      this.carregando = true;
      this.mensagemErro = "";
      this.service.consultaCampo(this.atributo.campoBusca,this.tipo,this.campoBusca).subscribe({
        next:(res) => {
          this.carregando = false;
          this.list = res;
        },
        error:(err) =>{
          console.log(err);
          this.carregando = false;
          this.mensagemErro = "Ocorreu um erro";
          this.list = [];
        }
        
    })
    }
  }

  fechar(){
    this.atributo.mostrarOpcoes = false;
  }
  selecionar(item:ModeloPadrao){
    this.form.get(this.atributo.nome)?.setValue(item.nome);
    this.atributo.codigo = item.codigo;
    this.atributo.mostrarOpcoes = false;
  }
}
