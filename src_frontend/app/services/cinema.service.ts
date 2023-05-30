import { Injectable } from '@angular/core';
import {HttpClientModule} from "@angular/common/http";
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class CinemaService {
public host:String="http://localhost:8080"
  constructor(private http:HttpClient) { }
  public getvilles(){
  return this.http.get(this.host+"/villes");
  }

  // @ts-ignore
  getCinemas = (v) => {
  return this.http.get(v._links.cinema.href);

  }
  // @ts-ignore
  getsalles(c) {
    return this.http.get(c._links.salles.href);
  }

  // @ts-ignore
  getprojections(salle) {
  let url=salle._links.projection.href.replace("{?projection}","")
    return this.http.get(url+"?projection=p1");
  }

  // @ts-ignore
  getplaces(p) {
    let url=p._links.projection.href.replace("{?projection}","")
    return this.http.get(url+"?projection=ticketproj");
  }
  // @ts-ignore
  payertickets(dataform) {
    return this.http.post(this.host+"/payerticket",dataform);

  }
}
