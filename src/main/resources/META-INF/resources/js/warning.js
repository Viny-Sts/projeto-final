function disconnect() {
    if (window.confirm("This action will disconnect you. Click 'Ok' to sign out or 'Cancel' to remain connected.")) {
        fetch(newPostRequest("/logout", JSON.stringify({
            "email": "logout",
            "password": "logout"

        }))).then((response) => {
            if (response.ok)
                return response.json();
            else
                throw new Error("An error has occurred " + response.status);

        }).then(json => {
            alert(json.message);

            window.location.href = window.location.origin + json.url;
        });
    }
}
