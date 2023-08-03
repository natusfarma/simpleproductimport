import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ModeloPadrao } from 'src/app/modals/modeloPadrao';
import { ProdutoXml } from 'src/app/modals/produtoXml';
import { SubCategoria } from 'src/app/modals/subCategoria';
import { ConsultaCamposService } from 'src/app/services/consulta-campos.service';

@Component({
  selector: 'app-opcoes',
  templateUrl: './opcoes.component.html',
  styleUrls: ['./opcoes.component.css']
})
export class OpcoesComponent implements OnInit {

  @Input() form!:FormGroup;
  @Input() atributo!:ProdutoXml;
  @Input() listaFornecedor:ModeloPadrao[] = [];
  @Output() editarCodigo = new EventEmitter<ModeloPadrao>();
  listaPadrao:ModeloPadrao[] = [];
  listaSubCategorias:SubCategoria[] = [];
  tipo:string = 'nome';
  campoBusca:string = '';
  mensagemErro:string = "";
  carregando:boolean = false;
    
  constructor(private service:ConsultaCamposService){}
  ngOnInit(): void {
    if(this.atributo.nome == "Fornecedor"){
      this.listaPadrao = this.listaFornecedor; 
    }
  }

  buscar(){
    if(this.campoBusca != "" && this.atributo.campoBusca){
      this.carregando = true;
      this.mensagemErro = "";
      if(this.atributo.campoBusca != 'subCategoria'){
        this.consultaPadrao(this.atributo.campoBusca);
      }else{
        this.consultaSubCategoria();
      }
    }
  }

  consultaSubCategoria(){
    this.service.consultaSubCategoria(this.tipo,this.campoBusca).subscribe({
      next:(res) => {
        this.carregando = false;
        this.listaSubCategorias = res;
      },
      error:(err) =>{
        console.log(err);
        this.carregando = false;
        this.mensagemErro = "Ocorreu um erro";
        this.listaSubCategorias = [];
      }
      
  })
  }

  consultaPadrao(url:string){
    this.service.consultaCampo(url,this.tipo,this.campoBusca).subscribe({
      next:(res) => {
        this.carregando = false;
        this.listaPadrao = res;
      },
      error:(err) =>{
        console.log(err);
        this.carregando = false;
        this.mensagemErro = "Ocorreu um erro";
        this.listaPadrao = [];
      }
  })
  }
  fechar(){
    this.atributo.mostrarOpcoes = false;
  }
  selecionar(item:ModeloPadrao){
    this.form.get(this.atributo.nome)?.setValue(item.nome);
    this.atributo.codigo = item.codigo;
    this.atributo.mostrarOpcoes = false;
  }

  selecionarSubCategoria(sub:SubCategoria){
    let categoria = new ModeloPadrao;
    let departamento = new ModeloPadrao
    categoria.nome = "Categoria";
    categoria.codigo = sub.codCategoria;
    this.editarCodigo.emit(categoria)
    departamento.nome = "Departamento";
    departamento.codigo = sub.codDepartamento;
    this.editarCodigo.emit(departamento)
    this.form.get("SubCategoria")?.setValue(sub.subCategoria);
    this.form.get("Categoria")?.setValue(sub.categoria);
    this.form.get("Departamento")?.setValue(sub.departamento);
    this.atributo.codigo = sub.codigo;
    this.atributo.mostrarOpcoes = false;
  }
}
