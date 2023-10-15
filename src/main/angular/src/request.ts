export class NewRequest {
    newGetRequest(url: string) : Request {
        return new Request(url, {
            method: "GET",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            }
        });
    }

    newPostRequest(url: string, body: string) : Request {
        return new Request(url, {
            method: "POST",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: body
        })
    }
}
