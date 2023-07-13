function addProfile() {
    let postRequest = newPostRequest("/profile/register", JSON.stringify({
        "name": document.getElementById("profile-name").value,

        "mainAccess": document.getElementById("main-access").checked,
        "activityAccess": document.getElementById("activity-access").checked,
        "userManagement": document.getElementById("user-management").checked,
        "profileManagement": document.getElementById("profile-management").checked
    }));

    fetch(postRequest).then((response) => {
        if (response.ok)
            return response.json();
        else
            throw new Error("An error has occurred " + response.status);

    }).then(json => {
        alert(json.message);
    });
}

function newPostRequest(url, body) {
    return new Request(url, {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: body
    });
}
