package due.giuaky221121514210.ui.Day3_Network.model;

public class Weather {
    private String DateTime;
    private int WeatherIcon;
    private String IconPhrase;
    private Temperature Temperature;

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public int getWeatherIcon() {
        return WeatherIcon;
    }

    public void setWeatherIcon(int weatherIcon) {
        WeatherIcon = weatherIcon;
    }

    public String getIconPhrase() {
        return IconPhrase;
    }

    public void setIconPhrase(String iconPhrase) {
        IconPhrase = iconPhrase;
    }
    public due.giuaky221121514210.ui.Day3_Network.model.Temperature getTemperature() {return Temperature;}

    public void setTemperature(due.giuaky221121514210.ui.Day3_Network.model.Temperature temperature) {
        Temperature = temperature;
    }
}