function checkInput() {
    if (document.getElementById("email").value === "" &&
        document.getElementById("password").value === "") {
        alert("Email and password fields are blank");

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

function login() {
    if (checkInput()) {
        fetch(newPostRequest("/login/auth", JSON.stringify({
            "email": document.getElementById("email").value,
            "password": document.getElementById("password").value

        }))).then((response) => {
            if (response.ok)
                return response.json();
            else
                throw new Error("An error has occurred" + response.status);

        }).then(json => {
            alert(json.message);

            window.location.href = window.location.origin + json.url;
        });
    }
}

function clearFields() {
    document.getElementById("email").value = "";
    document.getElementById("password").value = "";
}