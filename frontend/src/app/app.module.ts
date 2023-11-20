
import { NgModule } from '@angular/core';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistrarProdutoComponent } from './componentes/registrar-produto/registrar-produto.component';
import { MenuComponent } from './componentes/menu/menu.component';
import { NavbarComponent } from './componentes/navbar/navbar.component';
import { OpcoesComponent } from './componentes/opcoes/opcoes.component';
import { FilterPipe } from './pipes/filter.pipe';
import { UppercaseDirective } from './diretivas/uppercase-input.directive';
import { MensagemModalComponent } from './componentes/mensagem-modal/mensagem-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    RegistrarProdutoComponent,
    MenuComponent,
    NavbarComponent,
    OpcoesComponent,
    FilterPipe,
    UppercaseDirective,
    MensagemModalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
