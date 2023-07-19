document.addEventListener('DOMContentLoaded', () => {
    fetch(newGetRequest("/userManager/list")).then((response) => {
        if (response.ok)
            return response.json();
        else
            throw new Error("An error has occurred" + ". Error " + response.status);

    }).then(json => {
        let userTable = document.getElementById("user-table");

        for (let i = 0; i < json.users.length; i++) {
            let row = userTable.insertRow();

            row.insertCell().innerText = json.users[i].name;
            row.insertCell().innerText = json.users[i].email;
            row.insertCell().innerText = json.users[i].password;
            row.insertCell().innerText = json.users[i].profile;
        }
    });
});
