// Function to fetch weather data from the API and display it
function getWeather() {
    const city = document.getElementById('city').value;
    if (city.trim() === '') {
        alert('Please enter a city name');
        return;
    }

    // Make a request to the Java API
    fetch(`/v4weather/${city}`)
        .then(response => response.json())
        .then(data => {
            // Display the weather information
            document.getElementById('city-name').innerText = data.city;
            document.getElementById('temperature').innerText = data.temperature;
            document.getElementById('description').innerText = data.description;
            document.getElementById('humidity').innerText = data.humidity;
            document.getElementById('wind-speed').innerText = data.windSpeed;
            document.getElementById('weather').style.display = 'block';
        })
        .catch(error => {
            console.error('Error fetching weather data:', error);
            alert('Could not fetch weather data. Please try again later.');
        });
}
