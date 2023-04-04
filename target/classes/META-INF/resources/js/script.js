function cadastrar(){
    if (verificarEntrada()) {
        autenticar();
    }

}

function verificarEntrada() {
    if (document.getElementById("email").value === "" &&
        document.getElementById("senha").value === "") {
        alert("Os campos email e senha não podem estar em branco");

        return false;
    }

    else if (document.getElementById("email").value === "") {
        alert("O campo de email não pode estar em branco");

        return false;
    }

    else if (document.getElementById("senha").value === "") {
        alert("O campo de senha não pode estar em branco");

        return false;
    }

    return true;
}

function autenticar() {

    location.href = "/principal";
    /*

    var requisicao =
        criarRequisicao(
            document.getElementById("email").value, document.getElementById("senha").value);

    fetch(requisicao)
    .then(
        (response) => {
            if (response == "200") {
                //
            }
        }); {

    }
*/

    //admin
    if (document.getElementById("email").value === "admin@staff.com" &&
        document.getElementById("senha").value === "123") {

        alert("Login de administrador realizado com sucesso");
    }

    //usuário comum
    else if (document.getElementById("email").value === "rodrigo@gmail.com" &&
        document.getElementById("senha").value === "projeto" ||
        document.getElementById("email").value === "vinicius@gmail.com" &&
        document.getElementById("senha").value === "123") {
        alert("Login de usuário comum realizado com sucesso");

    } else {
        alert("Credenciais inválidas");
    }

    let dados = {
        "email": document.getElementById("email").value,
        "senha": document.getElementById("senha").value
    }
}

function criarRequisicao() {
    return new fetch("http://localhost:8080/autenticar", {
        method: "POST",
        headers: {
            "Accept": "*/*",
            "Content-Type": "aplication/json",
        },
        body: "{" +
            "email:" + "+'email'+" +
            "senha:" + "+'senha'+" +
            "}"
    })
}
