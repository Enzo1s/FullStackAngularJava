import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Plato } from 'src/app/entidades/Plato';
import { Router } from '@angular/router';

@Component({
  selector: 'app-item-plato',
  templateUrl: './item-plato.component.html',
  styleUrls: ['./item-plato.component.css']
})
export class ItemPlatoComponent implements OnInit {

  @Input() platoAux!:Plato;
  @Input() index!:string;

  //entre <> tiene que asignarse el tipo de dato a emitir
  //puede ser un objeto, string, number, etc.
  @Output() platoSeleccionado: EventEmitter<string>; //number es el index

  constructor(private router:Router) {
    this.platoSeleccionado = new EventEmitter();
   }

  ngOnInit(): void {
  }

  public verPlato(){
    console.log(this.index);
    //this.router.nav.gate(['/detallePlato',this.index]);
    this.platoSeleccionado.emit(this.index);
  }

}
