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
  //JUST KIDDIN'. IT STILL DOESN'T WORK. LMAO. I'M YET TO FIND ENOUGH FREE TIME SOLVE THIS.
function clearFields() {
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
        let request = newRequest(document.getElementById("email").value,
            document.getElementById("password").value);

        // HTTP codes -> https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status <-
        //fetch it and verify if the response status code is HTTP 200 (if not, an error appears)
        fetch(request)
            .then((response) => {
                if (response.status === 200) {
                    return response.json();
                } else {
                    throw new Error("An error was occurred" + response.status);
                }
            })

            //only then, connect the user on their respective role (admin or user)
            .then(json => {
                console.log(JSON.stringify(json));

                alert(json.message);

                if (json.message === "Connected as administrator") {
                    location.href = "/admin";
                }

                if (json.message === "Connected as user") {
                    location.href = "/main";
                }
            });
    }
}

// if any field are blank, the user gets a feedback with a warning, informing which field they forgot
function checkInput() {
    if (document.getElementById("email").value === "" &&
        document.getElementById("password").value === "") {
        alert("Email and password fields are blank");

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

function newRequest(email, password){
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

// just warns the user about the ongoing disconnection
function disconnect(url) {
    alert("This action will disconnect you. Click 'Ok' to proceed.");

    location.href = url;
}
