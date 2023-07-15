const apiKey = "a46706d71d48b5c4c10020f410925b4f";

function getOpenWeatherData() {
    let city = document.getElementById("city").value

    fetch("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=" + apiKey)
        .then((response) => {
            if (response.status === 200)
                return response.json();
            else
                throw new Error("An error has occurred" + response.status);

        }).then(json => {
            let weatherPanel = document.getElementById("weatherPanel");

            let cityInfo = document.createElement("p");
            let weatherDescription = document.createElement("p");
            let weatherTemp = document.createElement("p");
            let weatherFeelsLike = document.createElement("p");
            let weatherMax = document.createElement("p");
            let weatherMin = document.createElement("p");
            let weatherHumidity = document.createElement("p");

            cityInfo.innerHTML = json.name + " - " + json.sys.country;
            weatherDescription.innerHTML = json.weather[0].description;
            weatherTemp.innerHTML = "Temperature: " + json.main.temp + " C°";
            weatherFeelsLike.innerHTML = "Feels Like: " + json.main.feels_like + " C°";
            weatherMin.innerHTML = "Min: " + json.main.temp_min + " C°";
            weatherMax.innerHTML = "max: " + json.main.temp_max + " C°";
            weatherHumidity.innerHTML = "Humidity: " + json.main.humidity;

            weatherPanel.appendChild(cityInfo);
            weatherPanel.appendChild(weatherDescription);
            weatherPanel.appendChild(weatherTemp);
            weatherPanel.appendChild(weatherFeelsLike);
            weatherPanel.appendChild(weatherMax);
            weatherPanel.appendChild(weatherMin);
            weatherPanel.appendChild(weatherHumidity);
        }
    );
}

function clearFields() {
    document.getElementById("city").value = "";
}
