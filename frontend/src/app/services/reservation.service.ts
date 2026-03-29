import {inject, Injectable} from '@angular/core';
import {ConfigService} from "@services/config.service";
import {HttpClient} from "@angular/common/http";
import {Reservation} from "@models/app.models";
import {Observable} from "rxjs";
import {ReservationModel} from "@models/reservation.model";
import {GlobalService} from "@services/global.service";

@Injectable({
  providedIn: 'root',
})
export class ReservationService {

  private configService = inject(ConfigService);
  private hostUrl = this.configService.getAPIUrl();
  private http = inject(HttpClient)
  private globalService = inject(GlobalService)

  private baseEndPoint = `${this.hostUrl}/restaurant/api/v1/reservations`;


  create(reservation: ReservationModel){
    console.log("tesrt", reservation);

    return this.http.post<Reservation>(`${this.baseEndPoint}/create-reservation`, reservation);
  }

  getAll() : Observable<any>{
    return this.http.get<any>(`${this.baseEndPoint}/getAll-reservation`, this.globalService.httpOptions)
  }

  getById(id: number){
    return this.http.get<any>(`${this.baseEndPoint}/${id}`);
  }

  update(id:number , reservation: Reservation){
    return this.http.put<any>(`${this.baseEndPoint}/${id}`, reservation);
  }

  updateStatus(id:number, status:string){
    return this.http.patch(`${this.baseEndPoint}/${id}/status`, null, {params : {status}});
  }

  getByStatus(status:string){
    return this.http.get<any>(`${this.baseEndPoint}/filter/status`, {params : {status}});
  }

  getByDate(date:string){
    return this.http.get<any>(`${this.baseEndPoint}/filter/date`, {params : {date}});
  }


}
