import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";

import { Change, NewRequest } from "../../util";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent {
    loginForm: FormGroup;

    change: Change = new Change();
    request: NewRequest = new NewRequest();

    name: string = '';
    email: string = '';
    password: string = '';

    constructor(private formBuilder: FormBuilder) {
        this.loginForm = this.formBuilder.group({
            name: ['', [Validators.required]],
            email: ['', [Validators.required]],
            password: ['', [Validators.required]]
        });
    }
    checkInput() {
        if (this.loginForm.invalid) {
            alert("Please fill in name, email and password fields.");

            return;
        }

        fetch(this.request.newPostRequest("http://localhost:8080/signup/register", JSON.stringify({
            "name": (document.getElementById("name") as HTMLInputElement).value,
            "email": (document.getElementById("email") as HTMLInputElement).value,
            "password": (document.getElementById("password") as HTMLInputElement).value,
            "profile": (document.getElementById("profiles") as HTMLInputElement)
                ? (document.getElementById("profiles") as HTMLInputElement).value : "user"

        }))).then((response) => {
            if (response.ok)
                return response.json();
            else
                throw new Error("An error has occurred " + response.status);

        }).then(json => {
            alert(json.message);

            if (!document.getElementById("profiles"))
                this.change.page(json.url);
            else
                this.clearFields();
        });
    }

    clearFields() {
        (document.getElementById("name") as HTMLInputElement).value = "";
        (document.getElementById("email") as HTMLInputElement).value = "";
        (document.getElementById("password") as HTMLInputElement).value = "";
    }
}
