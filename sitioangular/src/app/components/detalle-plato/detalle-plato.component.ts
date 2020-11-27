import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Plato } from 'src/app/entidades/Plato';
import { DeliveryService } from 'src/app/servicios/delivery.service';

@Component({
  selector: 'app-detalle-plato',
  templateUrl: './detalle-plato.component.html',
  styleUrls: ['./detalle-plato.component.css']
})
export class DetallePlatoComponent implements OnInit {

plato!:Plato;

  constructor(private ActivatedRoute:ActivatedRoute, private servicioDelibery:DeliveryService) {

    this.ActivatedRoute.params.subscribe(params =>{
      this.servicioDelibery.getPlatoEnBaseDatosXId(params['id']).subscribe(platoEncontrado => {
        this.plato = platoEncontrado as Plato;
      });
    })
   }

  ngOnInit(): void {
  }

}
