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
        });
}

function listProfiles() {
    let getRequest = newGetRequest();

    fetch(getRequest)
        .then((response) => {
            if (response.status === 200) {
                return response.json();

            } else {
                throw new Error("An error has occurred" + ". Error " + response.status);
            }

        }).then(json => {

        console.log(json);
    });
}

function getPermissionDTO() {
    return {
        "name": document.getElementById("profile-name").value,

        "permissionLevel1": document.getElementById("direct-support").checked,
        "permissionLevel2": document.getElementById("search-filter-data").checked,
        "permissionLevel3": document.getElementById("submit-reports").checked,
        "permissionLevel4": document.getElementById("manage-users-account").checked,
        "permissionLevel5": document.getElementById("climatic-consult").checked,
        "permissionLevel6": document.getElementById("view-data").checked,
        "permissionLevel7": document.getElementById("website-management").checked,
        "permissionLevel8": document.getElementById("manage-content").checked,
        "permissionLevel9": document.getElementById("manage-settings").checked,
        "permissionLevel10": document.getElementById("monitor-activity").checked,
        "permissionLevel11": document.getElementById("manage-security").checked,
        "permissionLevel12": document.getElementById("manage-traffic").checked
    }
}

function newGetRequest() {
    return new Request("/list-permissions", {
        method: "GET",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        }
    });
}

function newPostRequest(permissionDTO) {
    return new Request("/permissions", {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify(permissionDTO),
    });
}
