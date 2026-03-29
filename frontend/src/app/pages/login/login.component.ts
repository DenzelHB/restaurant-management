import {Component, inject, OnInit} from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { DomSanitizer } from '@angular/platform-browser';
import { Router, RouterLink } from '@angular/router';
import { FlexLayoutModule } from '@ngbracket/ngx-layout';
import { Settings, SettingsService } from '@services/settings.service';
import {AuthService} from "@services/auth.service";
import {LoginRequest} from "@models/auth.model";
import {response} from "express";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
    selector: 'app-login',
    imports: [
        MatCardModule,
        ReactiveFormsModule,
        MatInputModule,
        MatButtonModule,
        MatIconModule,
        FlexLayoutModule,
        RouterLink,
        MatSlideToggleModule
    ],
    templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
  public loginForm!: FormGroup;
  public hide = true;
  public bgImage: any;
  public settings: Settings;

  private  authService = inject(AuthService);

  constructor(public fb: FormBuilder, public router: Router, private sanitizer: DomSanitizer, public settingsService: SettingsService, public snackBar: MatSnackBar) {
    this.settings = this.settingsService.settings;
  }

  ngOnInit(): void {
    this.bgImage = this.sanitizer.bypassSecurityTrustStyle('url(images/others/login.jpg)');
    this.loginForm = this.fb.group({
      username: [null, Validators.compose([Validators.required, Validators.minLength(6)])],
      password: [null, Validators.compose([Validators.required, Validators.minLength(6)])],
      rememberMe: false
    });
  }

  public onLoginFormSubmit(): void {
    if (this.loginForm.valid) {

        this.authService.login(this.loginForm.value as LoginRequest).subscribe({
            next:(response)=>{
                console.log(this.loginForm.value)
                if (response?.content?.roles.includes('ADMIN') || response?.content?.roles.includes('STAFF')){
                    this.router.navigate(['/admin/reservations'])
                }else {
                    this.snackBar.open(response?.message, '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
                    this.router.navigate(['/login']);
                }
            },
            error:(err)=>{
                console.log("erreur survenu lors de l'authentification", err);
            }
            }
        )

    }
  }

}