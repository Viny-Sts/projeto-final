import { Component } from '@angular/core';
import { Change, NewRequest } from '../../util';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})

export class NavbarComponent {
  request : NewRequest = new NewRequest();
  change : Change = new Change();

  disconnect() {
    if (window.confirm("This action will disconnect you. Click 'Ok' to sign out or 'Cancel' to remain connected.")) {
      fetch(this.request.newPostRequest("http://localhost:8080/logout", JSON.stringify({
        "email": "logout",
        "password": "logout"

      }))).then((response) => {
        if (response.ok)
          return response.json();
        else
          throw new Error("An error has occurred " + response.status);

      }).then(json => {
        alert(json.message);

        this.change.page(json.url);
      });
    }
  }
}
