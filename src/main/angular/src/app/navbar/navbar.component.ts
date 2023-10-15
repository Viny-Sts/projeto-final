import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})

export class NavbarComponent {
  constructor(private router: Router) { }

  changePage(url : string) {
    this.router.navigate([url]).then(() => {
      //alert("ggwp");

    }).catch(error => {
      console.error('Something went wrong. ', error);
    });
  }
}
