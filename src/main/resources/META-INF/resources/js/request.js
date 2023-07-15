function newGetRequest(url){
    return new Request(url, {
        method: "GET",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        }
    });
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
