import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MenuComponent } from './componentes/menu/menu.component';
import { RegistrarProdutoComponent } from './componentes/registrar-produto/registrar-produto.component';

const routes: Routes = [
  {title:'Registrar Produto', path:"registrar",component:RegistrarProdutoComponent},
  {title:'Simple Product Importer',path:'',component:MenuComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
