let profileIndex = 0;

function addProfile() {
    var permissionDTO = getPermissionDTO();
    let postRequest = newPostRequest(permissionDTO);

    fetch(postRequest)
        .then((response) => {
            if (response.status === 200) {
                return response.json();

            } else {
                throw new Error("An error has occurred " + response.status);
            }
        })

        .then(json => {
            alert(json.message);

            window.location.href = window.location.origin + json.url;
        });

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

function getPermissionDTO() {
    return {
        "permission-level-1": document.getElementById("direct-support").checked,
        "permission-level-2": document.getElementById("search-filter-data").checked,
        "permission-level-3": document.getElementById("submit-reports").checked,
        "permission-level-4": document.getElementById("manage-user-account").checked,
        "permission-level-5": document.getElementById("climatic-consult").checked,
        "permission-level-6": document.getElementById("view-data").checked,
        "permission-level-7": document.getElementById("website-management").checked,
        "permission-level-8": document.getElementById("manage-content").checked,
        "permission-level-9": document.getElementById("manage-settings").checked,
        "permission-level-10": document.getElementById("monitor-activity").checked,
        "permission-level-11": document.getElementById("manage-security").checked,
        "permission-level-12": document.getElementById("manage-traffic").checked
    }
}

function newPostRequest(permissionDTO){
    return new Request("/permissions", {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify(permissionDTO),
    });
}
