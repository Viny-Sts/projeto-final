import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NewRequest } from '../../request';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent {
  loginForm: FormGroup;
  request : NewRequest = new NewRequest();

  constructor(private formBuilder: FormBuilder) {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  checkInput() {
    if ((document.getElementById("email") as HTMLInputElement).value === "" &&
        (document.getElementById("password") as HTMLInputElement).value === "") {
      alert("Email and password fields are blank");

      return false;
    }

    else if ((document.getElementById("email") as HTMLInputElement).value === "") {
      alert("Email field is blank");

      return false;
    }

    else if ((document.getElementById("password") as HTMLInputElement).value === "") {
      alert("Password field is blank");

      return false;
    }

    return true;
  }

  login() {
    if (this.checkInput()) {
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

        window.location.href = window.location.origin + json.url;
      });
    }
  }

  clearFields() {
      (document.getElementById("email") as HTMLInputElement).value = "";
      (document.getElementById("password") as HTMLInputElement).value = "";
  }
}
