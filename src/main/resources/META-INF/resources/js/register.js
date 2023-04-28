let userIndex = 3;

let users = []

/*function click(){
    const inputField = document.getElementById('password');
    const submitButton = document.getElementById('submit-button');

    inputField.addEventListener('keyup', function(event) {
        if (event.key === 13) {
            event.preventDefault();
            submitButton.register();
        }
    })
}*/

function clearFields() {
    document.getElementById("name").value = "";
    document.getElementById("email").value = "";
    document.getElementById("password").value = "";
}

// when it's called, it checks if the user forgot any fields (checkInput) and then verify if the account exists (authenticate)
// there are two types of account, admin and user.
// admin account -> takes you to administration page;
// user account -> takes you to main page (probably where the API goes);
function register() {
    if (checkInput()) {
        //fetch api
        //make a new request

        let request = newRequest(document.getElementById("name").value,
            document.getElementById("email").value,
            document.getElementById("password").value,
            document.getElementById("admin-yes"));

        // HTTP codes -> https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status <-
        //fetch it and verify if the response status code is HTTP 200 (if not, an error appears)
        fetch(request)
            .then((response) => {
                if (response.status === 201) {
                    return response.json();
                } else {
                    throw new Error("An error was occurred" + response.status);
                }
            })

            //only then, send that information inside this request
            .then(json => {
                console.log(JSON.stringify(json));

                let user = {
                    "name": JSON.parse(JSON.stringify(json)).name,
                    "email": JSON.parse(JSON.stringify(json)).email,
                    "password": JSON.parse(JSON.stringify(json)).password,
                    "admin": JSON.parse(JSON.stringify(json)).admin
                };

                users.push(user);

                updateTable();

                alert("Successfully registered. Now you can sign-in");
            });
    }
}

// Create new table entries from HTTP GET Method
function updateTable() {
    //fetch api
    //make a new request
    let getRequest = newGetRequest();

    // HTTP codes -> https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status <-
    //fetch it and verify if the response status code is HTTP 200 (if not, an error appears)
    fetch(getRequest)
        .then((response) => {
            if (response.status === 200) {
                return response.json();

            } else {
                throw new Error("An error was occurred" + ". Error " + response.status);
            }
        })

        // and, only then, store the information inside the table
        .then(json => {
            let table = document.getElementById("user-table");
            let row = table.insertRow();

            userIndex++;

            row.insertCell().innerText = userIndex.toString();

            if (JSON.parse(JSON.stringify(json)).admin) {
                row.insertCell().innerText = "Staff";
            } else {
                row.insertCell().innerText = "User";
            }

            row.insertCell().innerText = JSON.parse(JSON.stringify(json)).name;
            row.insertCell().innerText = JSON.parse(JSON.stringify(json)).email;
            row.insertCell().innerText = JSON.parse(JSON.stringify(json)).password;
        });


}

// if any field are blank, the user gets a feedback with a warning, informing which field they forgot
function checkInput() {
    if (document.getElementById("name").value === "" &&
        document.getElementById("email").value === "" &&
        document.getElementById("password").value === "") {
        alert("Email and password fields are blank");

        return false;
    }

    else if (document.getElementById("name").value === "") {
        alert("Email fields are blank");

        return false;
    }

    else if (document.getElementById("email").value === "") {
        alert("Email fields are blank");

        return false;
    }

    else if (document.getElementById("password").value === "") {
        alert("Password fields are blank");

        return false;
    }

    return true;
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

function newRequest(name, email, password, admin){
    return new Request("/users", {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            "name": name,
            "email": email,
            "password": password,
            "admin": admin.checked
        }),
    });
}
