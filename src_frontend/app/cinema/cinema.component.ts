// @ts-ignore

import { Component,OnInit } from '@angular/core';
import {HttpClientModule} from "@angular/common/http";

import { HttpClient } from '@angular/common/http';
import {CinemaService} from "../services/cinema.service";


// @ts-ignore
@Component({
  selector: 'app-cinema',
  templateUrl: './cinema.component.html',
  styleUrls: ['./cinema.component.css']
})


export class CinemaComponent implements OnInit {
  // @ts-ignore
  public villes;
  // @ts-ignore
  public cinemas;
  // @ts-ignore
public currentvilles;
  // @ts-ignore
  public currentcinema;
  // @ts-ignore
  public salles;
  public currentprojection: any;
  // @ts-ignore
  public selectedticket;
  constructor(public cinemaService:CinemaService) {
  }

  ngOnInit() {
    this.cinemaService.getvilles()
      .subscribe(data => {
        this.villes = data;
      }, err => {
        console.log(err);
      });
  }
  // @ts-ignore
  getImageName(film){
    let imageName=film.titre.replace(/[\\*-]/g,"");
    return imageName+".jpg";
  }
  // @ts-ignore
  onGetCinemas(v) {
    this.currentvilles=v;
    this.salles=undefined;
    // @ts-ignore
    // @ts-ignore
    this.cinemaService.getCinemas(v).subscribe(
      // @ts-ignore
      data => {
        this.cinemas = data;
      },
      // @ts-ignore
      err => {
        console.log(err);
      }
    );
  } // @ts-ignore
  onGetTicketsPlaces(p){
    this.currentprojection=p;
    this.cinemaService.getplaces(p)
      // @ts-ignore
      .subscribe(data=>{this.currentprojection.tickets=data
          this.selectedticket=[];}

        // @ts-ignore
        ,err=>{console.log(err)})
  }
// @ts-ignore
  onGetSalles(c) {
this.currentcinema=c;
this.cinemaService.getsalles(c)
  .subscribe(
      // @ts-ignore
      data => {

        this.salles= data;
        // @ts-ignore
        this.salles._embedded.salles.forEach(salle =>{

          this.cinemaService.getprojections(salle)
            .subscribe(
              // @ts-ignore
              data => {salle.projection
        },    // @ts-ignore
          err=>{console.log(err);})
      })},
      // @ts-ignore
      err => {
        console.log(err);
      }
    );
  }
  // @ts-ignore
  onSelectTicket(t) {
if(!t.selected){
  t.selected=true;
  this.selectedticket.push(t);
}
else{
  t.selected=false;
  this.selectedticket.splice(this.selectedticket.indexOf(t),1);
}
console.log( this.selectedticket)
  }
  // @ts-ignore
  getticketclass(t) {
    let str ="btn ticket";
    if(t.reserve==true){str+="btn-danger";}
    else if(t.selected){str+="btn-warning"}
    else{
      str+="btn-success"
    }
    return str;

  }

  // @ts-ignore
  onpayetickets(dataform) {
let ticket: any[] =[];
// @ts-ignore
this.selectedticket.forEach(t=>{ticket.push(t.id)});
dataform.tickets=ticket;
this.cinemaService.payertickets(dataform)
  // @ts-ignore
  .subscribe(data=>{
    alert("ticket reserve avec succes")
    this.onGetTicketsPlaces(this.currentprojection);
  },
    // @ts-ignore
    err=>{console.log(err);})
  }
}
