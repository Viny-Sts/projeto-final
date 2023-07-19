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
            clearWeatherPanel();

            updateWeatherPanel(json.name + " " + json.sys.country, capitalizeFirstLetter(json.weather[0].description),
                json.main.temp + " C°" + " - Feels Like " + json.main.feels_like + " C°",
                "Min " + json.main.temp_min + " C°" + " - " + "Max " + json.main.temp_max + " C°",
                "Humidity " + json.main.humidity)
        }
    );
}

function getFutureOpenWeatherData() {
    let city = document.getElementById("city").value

    fetch("https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&units=metric&appid=" + apiKey)
        .then((response) => {
            if (response.status === 200)
                return response.json();
            else
                throw new Error("An error has occurred" + response.status);

        }).then(json => {
            clearWeatherPanel();

            for (let i = 0; i < json.list.length; i++) {
                updateWeatherPanel(json.city.name + " " + json.city.country + " - " + json.list[i].dt_txt, capitalizeFirstLetter(json.list[i].weather[0].description),
                    json.list[i].main.temp + " C°" + " - Feels Like " + json.list[i].main.feels_like + " C°",
                    "Min " + json.list[i].main.temp_min + " C°" + " - Max " + json.list[i].main.temp_max + " C°",
                    "Humidity " + json.list[i].main.humidity);
            }
    });
}

function updateWeatherPanel(city, description, temp, minMax, humidity) {
    let weatherPanel = document.getElementById("weatherPanel");

    let cityInfo = document.createElement("p");
    let weatherDescription = document.createElement("p");
    let weatherTemp = document.createElement("p");
    let weatherMinMax = document.createElement("p");
    let weatherHumidity = document.createElement("p");

    let breakLine = document.createElement("br");

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

    weatherPanel.appendChild(cityInfo);
    weatherPanel.appendChild(weatherDescription);
    weatherPanel.appendChild(weatherTemp);
    weatherPanel.appendChild(weatherMinMax);
    weatherPanel.appendChild(weatherHumidity);

    weatherPanel.appendChild(breakLine);
}

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

function clearWeatherPanel() {
    while (document.getElementById("city-info")) {
        document.getElementById("city-info").remove();
        document.getElementById("weather-description").remove();
        document.getElementById("weather-temp").remove();
        document.getElementById("weather-min-max").remove();
        document.getElementById("weather-humidity").remove();
        document.getElementById("break-line").remove();
    }
}

function clearFields() {
    document.getElementById("city").value = "";
}
