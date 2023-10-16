import { Component } from '@angular/core';
import { NewRequest } from "../../util";

@Component({
  selector: 'app-profile-manager',
  templateUrl: './profile-manager.component.html',
  styleUrls: ['./profile-manager.component.scss']
})
export class ProfileManagerComponent {
  request : NewRequest = new NewRequest();

  ngOnInit() {
    fetch(this.request.newGetRequest("http://localhost:8080/profileManager/list")).then((response) => {
      if (response.ok)
        return response.json();
      else
        throw new Error("An error has occurred" + ". Error " + response.status);

    }).then(json => {
      let profileTable : HTMLTableElement = (document.getElementById("profile-table") as HTMLTableElement);

      for (let i = 0; i < json.profiles.length; i++) {
        let row = profileTable.insertRow();

        row.insertCell().innerText = json.profiles[i].name;
        row.insertCell().innerText = json.profiles[i].mainAccess;
        row.insertCell().innerText = json.profiles[i].activityAccess;
        row.insertCell().innerText = json.profiles[i].userManagement;
        row.insertCell().innerText = json.profiles[i].profileManagement;
      }
    });
  }
}
