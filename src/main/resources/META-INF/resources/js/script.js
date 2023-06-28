const apiKey = '';

function getOpenWheatherData(city) {
    fetch(`https://api.openweathermap.org/data/2.5/weather?q=${city}&units=metric&appid=${apiKey}&lang=pt_br`)
        .then((response) => {
            if (response.status === 200)
                return response.json();
            else
                throw new Error("An error has occurred" + response.status);

    }).then(json => {
        alert(json.message);

        window.location.href = window.location.origin + json.url;
    });
}
