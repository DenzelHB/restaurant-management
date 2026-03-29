import {inject, Injectable, signal} from '@angular/core';
import {ConfigService} from "@services/config.service";
import {HttpClient} from "@angular/common/http";
import {AuthResponse, LoginRequest, UserInfo} from "@models/auth.model";
import {tap} from "rxjs";
import {sign} from "node:crypto";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private configService = inject(ConfigService);
  private hostUrl = this.configService.getAPIUrl();
  private http = inject(HttpClient)
  private router = inject(Router);

  private baseEndPoint = `${this.hostUrl}/restaurant/api/v1/auth`;

  private TOKEN_KEY = 'auth_token';
  private USER_KEY = 'user_key';

 // currentUserSignal = signals<UserInfo> (this.get)

  currentUserSignal = signal<UserInfo | null>(this.getUserFromLocalStorage());

  currentUser = this.currentUserSignal.asReadonly();

  login(request : LoginRequest){
    return this.http.post<any>(`${this.baseEndPoint}/login`, request).pipe(tap(response => {
      console.log("log : ",response)
      if (response.content){
        localStorage.setItem(this.TOKEN_KEY, response?.content?.token);
        const user: UserInfo = {
          email: response?.content?.email,
          firstName: response?.content?.firstName,
          lastName: response?.content.lastName,
          roles: response?.content?.roles
        };
        localStorage.setItem(this.USER_KEY, JSON.stringify(user))
      }
    }));
  }

  logout():void {
    localStorage.removeItem(this.TOKEN_KEY);
    localStorage.removeItem(this.USER_KEY);
    this.currentUserSignal.set(null);
    this.router.navigate(['/login'])
  }
  getToken():string {
    return localStorage.getItem(this.TOKEN_KEY);
  }
  isAuthenticated():boolean {
    return !!this.getToken();
  }

  hasRole(role:string):boolean {
    return this.currentUserSignal()?.roles.includes(role)?? false ;
  }

  isAdmin() : boolean {
    return this.hasRole('ADMIN');
  }

  private getUserFromLocalStorage(): UserInfo | null {
    const user = localStorage.getItem(this.USER_KEY);
    return user ? JSON.parse(user):null;
  }
}
