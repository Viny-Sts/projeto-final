const apiKey = 'a46706d71d48b5c4c10020f410925b4f';

function getOpenWheatherData() {
     let city = document.getElementById("city").value
    fetch(`https://api.openweathermap.org/data/2.5/weather?q=${city}&units=metric&appid=a46706d71d48b5c4c10020f410925b4f&lang=pt_br`)
        .then((response) => {
            if (response.status === 200)
                return response.json();
            else
                throw new Error("An error has occurred" + response.status);

    }).then(json => {
       console.log(json)
    });
}
