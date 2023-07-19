function addProfile() {
    fetch(newPostRequest("/profile/register", JSON.stringify({
        "name": document.getElementById("profile-name").value,

        "mainAccess": document.getElementById("main-access").checked,
        "activityAccess": document.getElementById("activity-access").checked,
        "userManagement": document.getElementById("user-management").checked,
        "profileManagement": document.getElementById("profile-management").checked

    }))).then((response) => {
        if (response.ok)
            return response.json();
        else
            throw new Error("An error has occurred " + response.status);

    }).then(json => {
        alert(json.message);
    });
}
