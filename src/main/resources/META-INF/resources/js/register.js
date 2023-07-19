document.addEventListener('DOMContentLoaded', () => {
    fetch(newGetRequest("/profileManager/list")).then((response) => {
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
    if (checkInput()) {
        fetch(newPostRequest("/signup/register", JSON.stringify({
            "name": document.getElementById("name").value,
            "email": document.getElementById("email").value,
            "password": document.getElementById("password").value,
            "profile": document.getElementById("profiles")
                    ? document.getElementById("profiles").value : "user"

        }))).then((response) => {
            if (response.ok)
                return response.json();
            else
                throw new Error("An error has occurred " + response.status);

        }).then(json => {
            alert(json.message);

            if (!document.getElementById("profiles"))
                window.location.href = window.location.origin + json.url;
            else
                clearFields();
        });
    }
}

function clearFields() {
    document.getElementById("name").value = "";
    document.getElementById("email").value = "";
    document.getElementById("password").value = "";
}
