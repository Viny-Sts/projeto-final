document.addEventListener('DOMContentLoaded', function updateProfileTable() {
    listProfiles();
});

function newGetRequest(url){
    return new Request(url, {
        method: "GET",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        }
    });
}

function listProfiles() {
    let getRequest = newGetRequest("/profileManager/list");

    fetch(getRequest).then((response) => {
        if (response.ok)
            return response.json();
        else
            throw new Error("An error has occurred" + ". Error " + response.status);

    }).then(json => {
        let profileTable = document.getElementById("profile-table");

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
