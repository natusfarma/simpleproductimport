import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filter'
})
export class FilterPipe implements PipeTransform {

  transform(lista: any[], filtro:string):any[] {
    if (!lista || !filtro) {
      return lista;
    }
    filtro = filtro.toLowerCase();
    return lista.filter(item =>{
      if (item.toLowerCase().includes(filtro)) {
        return true;
      }
      return false;
    })
    
  }

}
