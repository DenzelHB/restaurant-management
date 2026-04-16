export class ReservationModel {
    id?: number;
    reservationDate: string;
    reservationTime: string;
    numberOfPeople: number;
    status?: ReservationStatus;

    clientFullName: string;
    clientEmail: string;
    clientPhone: string;
    clientComment: string;
}

export  enum ReservationStatus {
    PENDING = 'PENDING',
    CONFIRMED = 'CONFIRMED',
    CANCELLED = 'CANCELLED'
}
