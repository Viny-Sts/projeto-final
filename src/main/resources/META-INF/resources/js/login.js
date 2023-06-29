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

function newPostRequest(url, body){
    return new Request(url, {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: body
    })
}

function login() {
    if (checkInput()) {
        let postRequest = newPostRequest("/login/auth", JSON.stringify({
            "email": document.getElementById("email").value,
            "password": document.getElementById("password").value
        }));

        fetch(postRequest).then((response) => {
            if (response.ok)
                return response.json();
            else
                throw new Error("An error has occurred" + response.status);

        }).then(json => {
            alert(json.message);
            //updateLog();

            window.location.href = window.location.origin + json.url;
        });
    }
}

function clearFields() {
    document.getElementById("email").value = "";
    document.getElementById("password").value = "";
}