import { Router } from "@angular/router";

export enum MainOptions {
  main,
  activity,
  profile,
  profileManager,
  userManager,
}

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

export class Change {
    router: Router = new Router();

    page(url : string) {
        this.router.navigateByUrl(url).then(() => {

        }).catch(error => {
            console.error('Something went wrong. ', error);
        });
    }
}
