document.addEventListener('DOMContentLoaded', () => {
    fetch(newGetRequest("/activity/list")).then((response) => {
        if (response.ok)
            return response.json();
        else
            throw new Error("An error has occurred" + ". Error " + response.status);

    }).then(json => {
        let activityTable = document.getElementById("activity-table");

        for (let i = 0; i < json.activities.length; i++) {
            let row = activityTable.insertRow();

            row.insertCell().innerText = json.activities[i].ip;
            row.insertCell().innerText = json.activities[i].date;
            row.insertCell().innerText = json.activities[i].name;
            row.insertCell().innerText = json.activities[i].activityLog;
        }
    });
});
