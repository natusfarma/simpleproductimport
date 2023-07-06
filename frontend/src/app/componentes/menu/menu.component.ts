import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DadosCabecalhoXml} from 'src/app/modals/dadosCabecalhoXml';
import { ProdutosService } from 'src/app/services/produtos.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  arquivoXML!: File;
  objetosXml!: DadosCabecalhoXml;
  constructor(private service: ProdutosService, private router: Router) {

  }
  ngOnInit(): void {
   
      this.objetosXml = this.service.objetoSelecionado;
    
   /* this.service.getXml().subscribe((res) => (
      //console.log(res),
        this.objetosXml = res))*/
  }


  selecionarArquivo(event: any) {
    this.arquivoXML = event.target.files[0];

  }

  enviarArquivo() {
    this.service.lerXml(this.arquivoXML).subscribe((res) => this.objetosXml = res)
  }
  carregarFormulario(index: number) {
    this.service.objetoSelecionado = this.objetosXml
    this.service.notaIndex = index
  }
}
