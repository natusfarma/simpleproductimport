import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ModeloPadrao } from '../modals/modeloPadrao';

@Injectable({
  providedIn: 'root'
})
export class ConsultaCamposService {

  constructor(private http:HttpClient) { }

  getFornecedores(tipo:string,filtro:string){
    return this.http.get(`/fornecedor/${tipo}/${filtro}`);
  }

  consultaCampo(url:string,tipo:string,filtro:string){
    return this.http.get<ModeloPadrao[]>(`/${url}/${tipo}?${tipo}=${filtro}`)
  }
  
}
