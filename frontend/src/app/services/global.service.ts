import {inject, Injectable} from '@angular/core';
import {AuthService} from "@services/auth.service";
import {HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root',
})
export class GlobalService {

  private authService = inject(AuthService);

  get httpOptions() {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.authService.getToken()}`
      })
    };
  }
  
}
