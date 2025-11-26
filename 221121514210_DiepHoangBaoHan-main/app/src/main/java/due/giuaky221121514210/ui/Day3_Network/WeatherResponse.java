package due.giuaky221121514210.ui.Day3_Network;

public class WeatherResponse {
    private Main main;
    private Weather[] weather;
    private String name;
    private Wind wind;
    private Sys sys;

    public static class Main {
        private double temp;
        private int humidity;

        public double getTemp() {
            return temp;
        }

        public int getHumidity() {
            return humidity;
        }
    }

    public static class Weather {
        private String description;
        private String icon;

        public String getDescription() {
            return description;
        }

        public String getIcon() {
            return icon;
        }
    }

    public static class Wind {
        private double speed;

        public double getSpeed() {
            return speed;
        }
    }

    public static class Sys {
        private String country;

        public String getCountry() {
            return country;
        }
    }

    public Main getMain() {
        return main;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public String getName() {
        return name;
    }

    public Wind getWind() {
        return wind;
    }

    public Sys getSys() {
        return sys;
    }
}
