import { Component } from '@angular/core';
import { Change } from '../../util';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})

export class NavbarComponent {
  change : Change = new Change();
}
