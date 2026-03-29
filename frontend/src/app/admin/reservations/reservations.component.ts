import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { AppService } from '@services/app.service';
import { DetailsDialogComponent } from './details-dialog/details-dialog.component';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import {ReservationModel, ReservationStatus} from "@models/reservation.model";
import {Reservation} from "@models/app.models";
import {ReservationService} from "@services/reservation.service";
import {DefaultFlexDirective, DefaultLayoutDirective} from "@ngbracket/ngx-layout";

@Component({
    selector: 'app-reservations',
  imports: [
    MatCardModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatIconModule,
    MatButtonModule,
    MatInputModule,
    MatSelectModule,
    DefaultFlexDirective,
    DefaultLayoutDirective,
  ],
    templateUrl: './reservations.component.html'
})
export class ReservationsComponent implements OnInit {
  displayedColumns: string[] = [
    'id', 'clientFullName', 'clientPhone', 'reservationDate', 'reservationTime', 'numberOfPeople', 'status', 'action', 'view'
  ];
  dataSource!: MatTableDataSource<Reservation>;
  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort!: MatSort;

  statuses = Object.values(ReservationStatus);


  constructor(public appService: AppService,  public reservationService: ReservationService, public snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.loadReservation();
  }

  loadReservation(){
    this.reservationService.getAll().subscribe({
      next:(data) => {
        this.initDataSource(data.content);
      },
      error:()=>  this.snackBar.open('Erreur survenue', '×', { panelClass: 'success', verticalPosition: 'top', duration: 3000 })
    });

  }


  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  filterByStatus(status: string): void {
    if (!status) {
      this.loadReservation();
      return;
    }
    this.reservationService.getByStatus(status).subscribe({
      next: (data) => this.initDataSource(data?.content)
    });
  }

  filterByDate(date: string): void {
    if (!date) {
      this.loadReservation();
      return;
    }
    this.reservationService.getByDate(date).subscribe({
      next: (data) => this.initDataSource(data.content)
    });
  }

  public initDataSource(data: any) {
    this.dataSource = new MatTableDataSource(data);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    (this.dataSource.sortingDataAccessor as any) = (data: any, sortHeaderId: string) => {
      return this.getPropertyByPath(data, sortHeaderId);
    };
  }

  getPropertyByPath(obj: Object, pathString: string) {
    return pathString.split('.').reduce((o: any, i: any) => o[i], obj);
  }

  public onStatusSelectionChange(event: any, reservation: ReservationModel) {
    if (event.value) {

      this.reservationService.updateStatus(reservation?.id, event.value).subscribe(({
        next:()=>{
          this.snackBar.open('Le status de la reservation a bien été mis à jour', '×', { panelClass: 'success', verticalPosition: 'top', duration: 3000 });
          this.loadReservation();
        },
        error:()=>{
          this.snackBar.open('Erreur lors de la mise à jours', '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });

        }
      }))

      // const index: number = this.dataSource.data.indexOf(reservation);
      // if (index !== -1) {
      //   this.dataSource.data.find(item => item.id == reservation.id)!.status = event.value;
      //   this.initDataSource(this.dataSource.data);
      //   this.snackBar.open('Reservation status updated successfully!', '×', { panelClass: 'success', verticalPosition: 'top', duration: 3000 });
      // }
    }
  }

  public onTableSelectionChange(event: any, reservation: Reservation) {
    if (event.value) {
      const index: number = this.dataSource.data.indexOf(reservation);
      if (index !== -1) {
        this.dataSource.data.find(item => item.id == reservation.id)!.tableNumber = event.value;
        this.initDataSource(this.dataSource.data);
        this.snackBar.open('Table assigned successfully!', '×', { panelClass: 'success', verticalPosition: 'top', duration: 3000 });
      }
    }
  }


  public view(reservation: Reservation) {
    const dialogRef = this.appService.openDialog(DetailsDialogComponent, reservation, 'theme-dialog');
    dialogRef.afterClosed().subscribe(data => {
      if (data) {

      }
    });
  }

}

