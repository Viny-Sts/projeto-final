document.addEventListener('DOMContentLoaded', function updateProfiles() {
    listProfiles();
});

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
    if (checkInput() === true) {
        var userDTO = getUserDTO();
        let postRequest = newPostRequest(userDTO);

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
    }
}

function listUsers() {
    let getRequest = newGetRequest("/list-users");

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

function listProfiles() {
    let getRequest = newGetRequest("/list-permissions");

    fetch(getRequest)
        .then((response) => {
            if (response.status === 200) {
                return response.json();

            } else {
                throw new Error("An error has occurred" + ". Error " + response.status);
            }

        }).then(json => {

            let profilesList = document.getElementById("profiles");

            for (let i = 0; i < json.profiles.length; i++) {
                let profile = document.createElement('option');
                profile.text = json.profiles[i].name;

                profilesList.appendChild(profile);
            }
    });
}

function getUserDTO() {
    return {
        "name": document.getElementById("name").value,
        "email": document.getElementById("email").value,
        "password": document.getElementById("password").value
    }
}


function newPostRequest(userDTO){
    return new Request("/users", {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify(userDTO),
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

function clearFields() {
    document.getElementById("name").value = "";
    document.getElementById("email").value = "";
    document.getElementById("password").value = "";
}
