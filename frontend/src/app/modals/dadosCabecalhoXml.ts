import { ItemNotaXml } from "./ItemNotaXml";
import { ModeloPadrao } from "./modeloPadrao";

export class DadosCabecalhoXml{
    nNF!:number;
    serie!:number;
    CNPJ!:string;
    xNome!:string;
    itensNotaXml!:ItemNotaXml[];
    fornecedores!:ModeloPadrao[];
}

