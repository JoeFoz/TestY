async function getWeather() {
    const city = document.getElementById('city-input').value;
    const response = await fetch(`/weather/${city}`);

    if (response.ok) {
        const weatherData = await response.json();

        document.getElementById('city-name').textContent = `Weather in ${weatherData.city}`;
        document.getElementById('temperature').textContent = `Temperature: ${weatherData.temperature}°C`;
        document.getElementById('description').textContent = `Condition: ${weatherData.description}`;
        document.getElementById('humidity').textContent = `Humidity: ${weatherData.humidity}%`;
        document.getElementById('wind-speed').textContent = `Wind Speed: ${weatherData.windSpeed} km/h`;

        document.getElementById('weather-info').style.display = 'block';
    } else {
        alert('City not found or API error.');
    }
}
