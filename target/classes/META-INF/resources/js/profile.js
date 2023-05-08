let profileIndex = 0;

function addProfile() {
    let permissions = [document.getElementById("direct-support").checked,
        document.getElementById("search-filter-data").checked,
        document.getElementById("submit-reports").checked,
        document.getElementById("manage-user-account").checked,
        document.getElementById("climatic-consult").checked,
        document.getElementById("view-data").checked,
        document.getElementById("website-management").checked,
        document.getElementById("manage-content").checked,
        document.getElementById("manage-settings").checked,
        document.getElementById("monitor-activity").checked,
        document.getElementById("manage-security").checked,
        document.getElementById("manage-traffic").checked];

    let table = document.getElementById("profile-table");
    let row = table.insertRow();

    profileIndex++;

    row.insertCell().innerText = profileIndex.toString();
    row.insertCell().innerText = document.getElementById("profile-name").value;

    for (let i = 0; i < permissions.length; i++) {
        if (permissions[i] === true) {
            row.insertCell().innerText = "Yes"

        } else {
            row.insertCell().innerText = "No";
        }
    }

    window.alert("Profile added.");
}
