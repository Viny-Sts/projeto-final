import {Component} from '@angular/core';

import {Change, MainOptions} from "../../util";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})

export class MainComponent {
  apiKey : string = 'a46706d71d48b5c4c10020f410925b4f';

  city : string = '';

  current : MainOptions = MainOptions.main;

  change : Change = new Change();

  mainOptionsButton(option : MainOptions) {
    if (option == this.current) {
      option = MainOptions.main;
    }

    this.current = option;
  }

  getOpenWeatherData() {
    this.city = (document.getElementById("city") as HTMLInputElement).value;

    fetch("https://api.openweathermap.org/data/2.5/weather?q=" + this.city + "&units=metric&appid=" + this.apiKey)
        .then((response) => {
          if (response.status === 200)
            return response.json();
          else
            throw new Error("An error has occurred" + response.status);

        }).then(json => {
          this.clearWeatherPanel();

          this.updateWeatherPanel(json.name + " " + json.sys.country, this.capitalizeFirstLetter(json.weather[0].description),
              json.main.temp + " C°" + " - Feels Like " + json.main.feels_like + " C°",
              "Min " + json.main.temp_min + " C°" + " - " + "Max " + json.main.temp_max + " C°",
              "Humidity " + json.main.humidity)
        }
    );
  }

  getFutureOpenWeatherData() {
    this.city = (document.getElementById("city") as HTMLInputElement).value;

    fetch("https://api.openweathermap.org/data/2.5/forecast?q=" + this.city + "&units=metric&appid=" + this.apiKey)
        .then((response) => {
          if (response.status === 200)
            return response.json();
          else
            throw new Error("An error has occurred" + response.status);

        }).then(json => {
      this.clearWeatherPanel();

      for (let i = 0; i < json.list.length; i++) {
        this.updateWeatherPanel(json.city.name + " " + json.city.country + " - " + json.list[i].dt_txt, this.capitalizeFirstLetter(json.list[i].weather[0].description),
            json.list[i].main.temp + " C°" + " - Feels Like " + json.list[i].main.feels_like + " C°",
            "Min " + json.list[i].main.temp_min + " C°" + " - Max " + json.list[i].main.temp_max + " C°",
            "Humidity " + json.list[i].main.humidity);
      }
    });
  }

  updateWeatherPanel(city : string, description : string, temp : string, minMax : string, humidity : string) {
    let weatherPanel = document.getElementById("weatherPanel");

    let cityInfo : HTMLParagraphElement = document.createElement("p");
    let weatherDescription : HTMLParagraphElement = document.createElement("p");
    let weatherTemp : HTMLParagraphElement = document.createElement("p");
    let weatherMinMax : HTMLParagraphElement = document.createElement("p");
    let weatherHumidity : HTMLParagraphElement = document.createElement("p");

    let breakLine : HTMLBRElement = document.createElement("br");

    cityInfo.id = "city-info";
    weatherDescription.id = "weather-description";
    weatherTemp.id = "weather-temp";
    weatherMinMax.id = "weather-min-max";
    weatherHumidity.id = "weather-humidity";

    breakLine.id = "break-line";

    cityInfo.innerHTML = city;
    weatherDescription.innerHTML = description;
    weatherTemp.innerHTML = temp;
    weatherMinMax.innerHTML = minMax;
    weatherHumidity.innerHTML = humidity;

    if (weatherPanel != null) {
      weatherPanel.appendChild(cityInfo);
      weatherPanel.appendChild(weatherDescription);
      weatherPanel.appendChild(weatherTemp);
      weatherPanel.appendChild(weatherMinMax);
      weatherPanel.appendChild(weatherHumidity);

      weatherPanel.appendChild(breakLine);
    }
  }

  capitalizeFirstLetter(text : string) {
    return text.charAt(0).toUpperCase() + text.slice(1);
  }

  clearWeatherPanel() {
    while (document.getElementById("city-info")) {
      (document.getElementById("city-info") as HTMLInputElement).remove();
      (document.getElementById("weather-description") as HTMLInputElement).remove();
      (document.getElementById("weather-temp") as HTMLInputElement).remove();
      (document.getElementById("weather-min-max") as HTMLInputElement).remove();
      (document.getElementById("weather-humidity") as HTMLInputElement).remove();
      (document.getElementById("break-line") as HTMLInputElement).remove();
    }
  }

  clearFields() {
    (document.getElementById("city") as HTMLInputElement).value = "";
  }

  protected readonly MainOptions = MainOptions;
}
