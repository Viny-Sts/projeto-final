profileIndex = 0;

function addProfile() {
    let table = document.getElementById("profile-table");
    let row = table.insertRow();

    profileIndex++;

    row.insertCell().innerText = document.getElementById("");
    row.insertCell().innerText = document.getElementById("");
    row.insertCell().innerText = document.getElementById("");
    row.insertCell().innerText = document.getElementById("");
    row.insertCell().innerText = document.getElementById("");
    row.insertCell().innerText = document.getElementById("");
    row.insertCell().innerText = document.getElementById("");
    row.insertCell().innerText = document.getElementById("");
    row.insertCell().innerText = document.getElementById("");
    row.insertCell().innerText = document.getElementById("");
    row.insertCell().innerText = document.getElementById("");

    alert("Profile added");
}