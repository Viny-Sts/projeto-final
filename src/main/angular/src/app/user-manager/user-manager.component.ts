import { Component } from '@angular/core';
import { NewRequest } from "../../util";

@Component({
  selector: 'app-user-manager',
  templateUrl: './user-manager.component.html',
  styleUrls: ['./user-manager.component.scss']
})

export class UserManagerComponent {
  request : NewRequest = new NewRequest();

  ngOnInit() {
		fetch(this.request.newGetRequest("http://localhost:8080/userManager/list")).then((response) => {
			if (response.ok)
				return response.json();
			else
				throw new Error("An error has occurred" + ". Error " + response.status);

		}).then(json => {
			let userTable : HTMLTableElement = (document.getElementById("user-table") as HTMLTableElement);

			for (let i = 0; i < json.users.length; i++) {
				let row = userTable.insertRow();

				row.insertCell().innerText = json.users[i].name;
				row.insertCell().innerText = json.users[i].email;
				row.insertCell().innerText = json.users[i].password;
				row.insertCell().innerText = json.users[i].profile;
			}
		});
  }
}
