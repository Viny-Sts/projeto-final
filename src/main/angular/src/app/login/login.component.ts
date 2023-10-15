import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Change, NewRequest } from '../../util';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent {
  loginForm: FormGroup;

  change : Change = new Change();
  request : NewRequest = new NewRequest();

  email: string = '';
  password: string = '';

  constructor(private formBuilder: FormBuilder) {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  checkInput() {
    if (this.loginForm.invalid) {
      alert("Please fill in both email and password fields.");

      return;
    }

    fetch(this.request.newPostRequest("http://localhost:8080/login/auth", JSON.stringify({
      "email": (document.getElementById("email") as HTMLInputElement).value,
      "password": (document.getElementById("password") as HTMLInputElement).value

    }))).then((response) => {
      if (response.ok)
        return response.json();
      else
        throw new Error("An error has occurred" + response.status);

    }).then(json => {
      alert(json.message);

      this.change.page(json.url);
    });
  }

  clearFields() {
      (document.getElementById("email") as HTMLInputElement).value = "";
      (document.getElementById("password") as HTMLInputElement).value = "";
  }
}
