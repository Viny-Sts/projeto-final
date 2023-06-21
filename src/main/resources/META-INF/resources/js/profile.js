function addProfile() {
    let permissionDTO = getPermissionDTO();
    let postRequest = newPostRequest(permissionDTO);

    console.log(permissionDTO);

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

function getPermissionDTO() {
    return {
        "name": document.getElementById("profile-name").value,

        "mainAccess": document.getElementById("main-access").checked,
        "activityAccess": document.getElementById("activity-access").checked,
        "userManagement": document.getElementById("user-management").checked,
        "profileManagement": document.getElementById("profile-management").checked
    }
}

function newPostRequest(permissionDTO) {
    return new Request("/profiles", {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify(permissionDTO),
    });
}
