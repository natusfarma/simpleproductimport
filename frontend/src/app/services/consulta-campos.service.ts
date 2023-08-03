import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ModeloPadrao } from '../modals/modeloPadrao';
import { SubCategoria } from '../modals/subCategoria';

@Injectable({
  providedIn: 'root'
})
export class ConsultaCamposService {

  constructor(private http:HttpClient) { }

  consultaCampo(url:string,tipo:string,filtro:string){
    return this.http.get<ModeloPadrao[]>(`/api/${url}/${tipo}?${tipo}=${filtro}`)
  }
  consultaSubCategoria(tipo:string,filtro:string){
    return this.http.get<SubCategoria[]>(`/api/subCategorias/${tipo}?${tipo}=${filtro}`)
  }
}
