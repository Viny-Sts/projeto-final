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

            //only then, connect the user on their respective role (admin or user)
            .then(json => {
                alert(json.message);

                window.location.href = window.location.origin + json.url;

                //updateTable();
            });
    }
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

function clearFields() {
    document.getElementById("name").value = "";
    document.getElementById("email").value = "";
    document.getElementById("password").value = "";
}

/*
function newGetRequest(){
    return new Request("/users", {
        method: "GET",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        }
    });
}
*/
/*
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


} */
