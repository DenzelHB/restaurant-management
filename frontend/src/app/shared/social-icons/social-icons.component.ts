import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-social-icons',
  imports: [],
  templateUrl: './social-icons.component.html'
})
export class SocialIconsComponent {
  @Input() iconSize: string = '';
  @Input() iconColor: string = '';
}
