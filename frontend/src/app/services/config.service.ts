import {inject, Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {AuthService} from "@services/auth.service";
import {HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root',
})
export class ConfigService {

  getAPIUrl():string {
    return environment.apiUrl ?? '';
  }

  isProd():boolean {
    return environment.production;
  }




}
