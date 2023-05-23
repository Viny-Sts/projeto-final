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
    if (checkInput() === true) {
        let postRequest = newPostRequest(document.getElementById("email").value,
            document.getElementById("password").value);

        fetch(postRequest)
            .then((response) => {
                if (response.status === 200) {
                    return response.json();

                } else {
                    throw new Error("An error has occurred" + response.status);
                }
            })
            //only then, connect the user on their respective role (admin or user)
            .then(json => {
                alert(json.message);

                window.location.href = window.location.origin + json.url;
            });
    }
}



// if any field are blank, the user gets a feedback with a warning, informing which field they forgot


function newPostRequest(email, password){
    return new Request("/authenticate", {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            "email": email,
            "password": password
        }),
    });
}


function clearFields() {
    document.getElementById("email").value = "";
    document.getElementById("password").value = "";
}