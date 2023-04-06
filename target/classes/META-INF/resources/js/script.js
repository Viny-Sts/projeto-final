function register(){
    if (checkInput()) {
        authenticate();
    }
}

function checkInput() {
    if (document.getElementById("email").value === "" &&
        document.getElementById("password").value === "") {
        alert("Os campos email e senha não podem estar em branco");

        return false;
    }

    else if (document.getElementById("email").value === "") {
        alert("O campo de email não pode estar em branco");

        return false;
    }

    else if (document.getElementById("password").value === "") {
        alert("O campo de senha não pode estar em branco");

        return false;
    }

    return true;
}

function authenticate() {

    //var requisicao =
        //criarRequisicao(
            //document.getElementById("email").value, document.getElementById("senha").value);

    //fetch(requisicao)
    //.then(
        //(response) => {
            //if (response === "200") {
                //return response.json();
            //} else {
                //throw new Error("Ocorreu um erro");
            //}
        //});

    //admin
    if (document.getElementById("email").value === "admin@staff.com" &&
        document.getElementById("password").value === "123") {
        alert("Login de administrador realizado com sucesso");

        location.href = "/admin";
    }

    //usuário comum
    else if (document.getElementById("email").value === "rodrigo@gmail.com" &&
        document.getElementById("password").value === "projeto" ||
        document.getElementById("email").value === "vinicius@gmail.com" &&
        document.getElementById("password").value === "123") {
        alert("Login de usuário comum realizado com sucesso");

        location.href = "/main";
    } else {
        alert("Credenciais inválidas");
    }
}

//function criarRequisicao() {
    //return new fetch("http://localhost:8080/autenticar", {
        //method: "POST",
        //headers: {
            //"Accept": "*/*",
            //"Content-Type": "aplication/json",
        //},
        //body: "{" +
            //"email:" + "'email'," +
            //"senha:" + "'senha'" +
            //"}"
    //})
//}
