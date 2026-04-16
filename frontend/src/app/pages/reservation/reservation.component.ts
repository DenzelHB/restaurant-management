import {Component, inject} from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { AppService } from '@services/app.service';
import { GetAppComponent } from '@shared/get-app/get-app.component';
import { HeaderImageComponent } from '@shared/header-image/header-image.component';
import { ReservationFormComponent } from '@shared/reservation-form/reservation-form.component';
import {ReservationService} from "@services/reservation.service";
import {ReservationModel, ReservationStatus} from "@models/reservation.model";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
    selector: 'app-reservation',
    imports: [
        HeaderImageComponent,
        MatCardModule,
        ReservationFormComponent,
        GetAppComponent
    ],
    templateUrl: './reservation.component.html'
})
export class ReservationComponent {

    private snackbar = inject(MatSnackBar);
  constructor(public reservationService: ReservationService) { }

  public onMakeReservation(form: any) {

      const value = form.value;

      const reservation : ReservationModel = {
          reservationDate: value.reservationDate,
          reservationTime: value.reservationTime+':00',
          numberOfPeople: value.numberOfPeople,
          clientFullName: value.clientFullName,
          clientEmail: value.clientEmail,
          clientPhone:value.clientPhone ,
          clientComment: value.clientComment ?? ''
      };
      this.reservationService?.create(reservation).subscribe({
          next: ()=>{
              this.snackbar.open("Reservation effectuée avec sucess", '×', { panelClass: 'success', verticalPosition: 'top', duration: 3000 });
              form.reset();
          },error: ()=>{
              this.snackbar.open("Erreur lors de la reservation", '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
          }
      })
  }
    private formatDate(date : Date){
     return date.toDateString().split('T')[0];
    }
}


