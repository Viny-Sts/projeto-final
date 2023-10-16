import { Component } from '@angular/core';
import { NewRequest } from "../../util";

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.scss']
})
export class ActivityComponent {
  request : NewRequest = new NewRequest();

  ngOnInit() {
    fetch(this.request.newGetRequest("http://localhost:8080/activity/list")).then((response) => {
      if (response.ok)
        return response.json();
      else
        throw new Error("An error has occurred" + ". Error " + response.status);

    }).then(json => {
      let activityTable : HTMLTableElement = (document.getElementById("activity-table") as HTMLTableElement);

      for (let i = 0; i < json.activities.length; i++) {
        let row = activityTable.insertRow();

        row.insertCell().innerText = json.activities[i].ip;
        row.insertCell().innerText = json.activities[i].date;
        row.insertCell().innerText = json.activities[i].name;
        row.insertCell().innerText = json.activities[i].activityLog;
      }
    });
  }
}
