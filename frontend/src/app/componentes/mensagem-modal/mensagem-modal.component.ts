import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-mensagem-modal',
  templateUrl: './mensagem-modal.component.html',
  styleUrls: ['./mensagem-modal.component.css']
})
export class MensagemModalComponent {

  @Input() mensagem:String = ""
  @Output() mensagemChange = new EventEmitter<string>();
  @Input() alerta!:String;


  fechar(){
    this.mensagemChange.emit("")
  }
}
