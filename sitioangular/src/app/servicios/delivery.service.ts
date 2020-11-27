import { Injectable } from '@angular/core';
import { NgbPaginationNumber } from '@ng-bootstrap/ng-bootstrap';
//import * as data from 'src/assets/datos/platos.json';
import {HttpClient, HttpParams } from "@angular/common/http";
import { Plato } from '../entidades/Plato';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class DeliveryService {

  //platosFile:any = (data as any).default;
  public platosData:Plato[]=[];
  public platoEncontrado!:Plato;

  constructor(public http: HttpClient) {
    console.log("Servicio Cargado!!!");
  }

  public getPlatos():any[]{
    return this.platosData;
    console.log(this.platosData);
  }

  public getPlatoXId(idx:string):any{
    for(let plato of this.platosData){
      if(plato.id == idx){
        return plato;
      }
    }
  }

  //lee todos los platos
  getPlatosFromDataBase(){
    return this.http.get("http://localhost:9000/api/v1/platos/all").pipe(
      map( platosData => platosData));
  }

  //busca un plato por el id
  getPlatoEnBaseDatosXId(idx:string){
    return this.http.get("http://localhost:9000/api/v1/platos/" + idx).pipe(
      map( platoEncontrado => platoEncontrado));
  }

  //busca los platos por un terminode busqueda
  getPlatosBusquedaFromDataBase(termino:string){
    return this.http.get("http://localhost:9000/api/v1/platos/search?filtro=" + termino).pipe(
      map( platosSearch => platosSearch));
  }

  platoAdminUrl:string = "http://localhost:9000/api/v1/platos/";
  newPlato( platoNuevo: Plato) {
    return this.http.post<Plato>(this.platoAdminUrl,platoNuevo);
    /* return this.http.post<Plato>( this.platoAdminUrl, null, {params: new HttpParams().set("action", "insertar").set("id", "0")
    .set("nombre", platoNuevo.nombre).set("imagenPath", platoNuevo.imagenPath).set("precio", platoNuevo.precio).set("rubro", platoNuevo.rubro)
    }).pipe(map( nuevoPlato => {
            console.log(nuevoPlato.nombre);
            return nuevoPlato;
          })); */
  }



   updatePlato( platoUpdate: Plato, idPlato:string) {
    return this.http.put(this.platoAdminUrl+idPlato,platoUpdate);
      /* return this.http.put<Plato>( this.platoAdminUrl, platoUpdate, {params: new HttpParams().set("id", platoUpdate.id)
      .set("nombre", platoUpdate.nombre).set("imagenPath", platoUpdate.imagenPath).set("precio", platoUpdate.precio).set("rubro", platoUpdate.rubro)
      }).pipe(map( res => {
              console.log(res.nombre);
              return res;
            })); */
    }

    deletePlato(idPlato: string){
      //return this.http.post(this.platoAdminUrl+idPlato,null);
      return this.http.delete(this.platoAdminUrl+idPlato);
      /* return this.http.post( this.platoAdminUrl, null, {params: new HttpParams().set("delete", idPlato)})
            .pipe(
            map( res => {
              console.log(res);
              return res;
            })); */
    }
}
