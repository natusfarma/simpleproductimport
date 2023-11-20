import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DadosCabecalhoXml } from 'src/app/modals/dadosCabecalhoXml';
import { ItemNotaXml } from 'src/app/modals/ItemNotaXml';
import { ModeloPadrao } from 'src/app/modals/modeloPadrao';
import { ConsultaCamposService } from 'src/app/services/consulta-campos.service';
import { ProdutosService } from 'src/app/services/produtos.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  arquivoXML!: File;
  objetosXml!: DadosCabecalhoXml;
  codigo: string = '';
  usuario!: ModeloPadrao;
  mensagem: string = '';
  mostrar:boolean = false;
  fornecedor!:ModeloPadrao;
  constructor(private service: ProdutosService, private consultaService: ConsultaCamposService, private route: Router) {

  }
  ngOnInit(): void {
    this.objetosXml = this.service.objetoSelecionado;
    this.usuario = this.service.usuario;
  }
  selecionarArquivo(event: any) {
    this.arquivoXML = event.target.files[0];
    this.objetosXml = new DadosCabecalhoXml;
  }

  enviarArquivo() {
    this.service.lerXml(this.arquivoXML).subscribe((res) => {
      this.objetosXml = res
      if(this.objetosXml.fornecedores.length == 0){
        this.mensagem = "Não foi encontrado o fornecedor com o cpnj do xml"
      }else if(this.objetosXml.fornecedores.length == 1){
        this.fornecedor = this.objetosXml.fornecedores[0]
      }else if(this.objetosXml.fornecedores.length > 1){
        this.mostrar = true;
      }
    })
  }

  
  cadastrar(){
    if (this.usuario?.nome) {
      this.service.cadastroComXml = false;
      this.service.usuario = this.usuario;
      this.route.navigate(['registrar'])
    }else{
      
      this.mensagem = "insira o código do usuario";
    }
  }

  carregarFormulario(index: number) {
    if (this.usuario?.nome) {
      this.service.objetoSelecionado = this.objetosXml
      this.service.notaIndex = index
      this.service.usuario = this.usuario;
      this.service.fornecedor = this.fornecedor
      this.service.cadastroComXml = true;
      this.route.navigate(['registrar'])
    } else {
      this.mensagem = "insira o código do usuario";
    }
  }

  buscarUsuario() {
    this.mensagem = '';
    this.consultaService.consultaCampo('usuario', 'codigo', this.codigo)
      .subscribe({
        next: res => {
          if(res.length == 0){
            this.mensagem = "Usuário não encontrado"
          }else{
            this.usuario = res[0]
          }
        },
        error: err => {
          this.mensagem = "Erro ao obter usuario"
          
        }
      })
  }

  atualizarFornecedor(index:number){
    this.fornecedor = this.objetosXml.fornecedores[index]
    this.service.atualizarStatus(this.objetosXml,index).subscribe((res) =>{
      this.objetosXml.itensNotaXml = res
      this.mostrar = false
    })
  }

}
