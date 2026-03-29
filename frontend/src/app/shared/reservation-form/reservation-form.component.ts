import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ReactiveFormsModule, UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { emailValidator } from '../../theme/utils/app-validators';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatDatepickerModule } from '@angular/material/datepicker'; 
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { FlexLayoutModule } from '@ngbracket/ngx-layout';

@Component({
    selector: 'app-reservation-form',
    imports: [
        ReactiveFormsModule,
        MatInputModule,
        MatIconModule,
        MatDatepickerModule,
        MatSelectModule,
        MatButtonModule,
        FlexLayoutModule
    ],
    templateUrl: './reservation-form.component.html'
})
export class ReservationFormComponent implements OnInit {
  public form!: UntypedFormGroup;
  public hours = [ '12:00', '12:30', '13:00', '13:30', '19:00', '19:30', '20:00', '20:30', '21:00'];
  public today = new Date();
  @Output() onFormSubmit: EventEmitter<any> = new EventEmitter();
  constructor(public formBuilder: UntypedFormBuilder) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      clientFullName: [null, Validators.required],
      clientPhone: [null, Validators.required],
      clientEmail: [null, Validators.compose([Validators.required, emailValidator])],
      numberOfPeople: [null, Validators.required],
      reservationDate: [null, Validators.required],
      reservationTime: [null, Validators.required],
      clientComment: null
    });
  }

  public submit() {
    if (this.form.valid) {
      this.onFormSubmit.emit(this.form);
    }
  }

}
