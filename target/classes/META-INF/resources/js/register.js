userIndex = 3;
// if any field is blank, the user gets a feedback with a warning, informing which field they forgot

function register() {
    if (checkInput() === true) {
        //fetch api
        //make a new request
        var userDTO = getUserDTO();
        let postRequest = newPostRequest(userDTO);

        // HTTP codes -> https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status <-
        //fetch it and verify if the response status code is HTTP 201 (if not, an error appears)
        fetch(postRequest)
            .then((response) => {
                if (response.status === 201) {
                    return response.json();
                } else {
                    throw new Error("An error has occurred" + response.status);
                }
            })

            //only then, connect the user on their respective role (admin or user)
            .then(json => {

                // Just pretending to be saving the information somewhere else (this variable is not used anywhere)
                let user = {
                    "name": JSON.parse(JSON.stringify(json)).name,
                    "email": JSON.parse(JSON.stringify(json)).email,
                    "password": JSON.parse(JSON.stringify(json)).password,
                    "admin": JSON.parse(JSON.stringify(json)).admin
                }

                console.log("User registered: ", user);

                updateTable();

                alert("Successfully registered. Now you can sign-in");
            });
    }
}

function getUserDTO() {
    return {
        "name": document.getElementById("name").value,
        "email": document.getElementById("email").value,
        "password": document.getElementById("password").value,
        "admin-yes": document.getElementById("admin-yes").checked
    }
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
function newPostRequest(name, email, password, admin){
    return new Request("/users", {
        //creates a new http post request to the server to add a new user.
        //using the POST method
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify(userDTO),
    });
}
function newGetRequest(){
    return new Request("/users", {
        method: "GET",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        }
    });
}
function updateTable() {
    let getRequest = newGetRequest();

    fetch(getRequest)
        .then((response) => {
            if (response.status === 200) {
                return response.json();

            } else {
                throw new Error("An error has occurred" + ". Error " + response.status);
            }
        })

        .then(json => {
            let table = document.getElementById("user-table");
            let row = table.insertRow();

            userIndex++;

            row.insertCell().innerText = userIndex;

            if (JSON.parse(JSON.stringify(json)).admin) {
                row.insertCell().innerText = "Staff";

            } else {
                row.insertCell().innerText = "User";
            }

            row.insertCell().innerText = JSON.parse(JSON.stringify(json)).name;
            row.insertCell().innerText = JSON.parse(JSON.stringify(json)).email;
            row.insertCell().innerText = JSON.parse(JSON.stringify(json)).password;

            console.log("Added the entry", userIndex, "in the registered table below");
        });


}

function clearFields() {
    document.getElementById("name").value = "";
    document.getElementById("email").value = "";
    document.getElementById("password").value = "";
}