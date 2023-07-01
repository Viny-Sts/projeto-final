document.addEventListener('DOMContentLoaded', function updateProfiles() {
    listProfiles();
});

function newPostRequest(url, body){
    return new Request(url, {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: body,
    });
}

function newGetRequest(url){
    return new Request(url, {
        method: "GET",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        }
    });
}

function checkInput() {
    if (document.getElementById("name").value === "" &&
        document.getElementById("email").value === "" &&
        document.getElementById("password").value === "") {
        alert("Name, email and password fields are blank");

        return false;
    }

    else if (document.getElementById("name").value === "") {
        alert("Name field is blank");

        return false;
    }

    else if (document.getElementById("email").value === "") {
        alert("Email field is blank");

        return false;
    }

    else if (document.getElementById("password").value === "") {
        alert("Password field is blank");

        return false;
    }

    return true;
}

function register() {
    if (checkInput()) {
        let postRequest = newPostRequest("/signup/register", JSON.stringify({
            "name": document.getElementById("name").value,
            "email": document.getElementById("email").value,
            "password": document.getElementById("password").value,
            "profile": document.getElementById("profiles") ?
                document.getElementById("profiles").value : "user"
        }));

        fetch(postRequest).then((response) => {
            if (response.ok)
                return response.json();
            else
                throw new Error("An error has occurred " + response.status);

        }).then(json => {
            alert(json.message);

            window.location.href = window.location.origin + json.url;
        });
    }
}

function listProfiles() {
    let getRequest = newGetRequest("/profileManager/list");

    fetch(getRequest).then((response) => {
        if (response.ok)
            return response.json();
        else
            throw new Error("An error has occurred" + ". Error " + response.status);

        }).then(json => {
            let profilesList = document.getElementById("profiles");

            for (let i = 0; i < json.profiles.length; i++) {
                let profile = document.createElement('option');
                profile.text = json.profiles[i].name;

                profilesList.appendChild(profile);
            }
    });
}

function clearFields() {
    document.getElementById("name").value = "";
    document.getElementById("email").value = "";
    document.getElementById("password").value = "";
}
