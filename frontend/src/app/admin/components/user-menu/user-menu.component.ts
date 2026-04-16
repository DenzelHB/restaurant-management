import {Component, inject} from '@angular/core';
import { FlexLayoutModule } from '@ngbracket/ngx-layout';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterModule } from '@angular/router';
import { MatDividerModule } from '@angular/material/divider';
import { Settings, SettingsService } from '@services/settings.service';
import {AuthService} from "@services/auth.service";

@Component({
    selector: 'app-user-menu',
    imports: [
        RouterModule,
        FlexLayoutModule,
        MatButtonModule,
        MatIconModule,
        MatMenuModule,
        MatToolbarModule,
        MatDividerModule
    ],
    templateUrl: './user-menu.component.html'
})
export class UserMenuComponent {
  public userImage = 'images/others/admin.jpg';
  public settings: Settings;
  private authService = inject(AuthService);
  constructor(public settingsService: SettingsService) {
    this.settings = this.settingsService.settings;
  }

    logOut(){
      this.authService.logout();
    }
}
