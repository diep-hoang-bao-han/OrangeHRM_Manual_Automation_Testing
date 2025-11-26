package due.giuaky221121514210.ui.Day3_Network;

import java.util.List;

public class ForecastResponse {
    private List<ForecastItem> list;

    public static class ForecastItem {
        private String dt_txt;
        private Main main;
        private List<Weather> weather;

        public static class Main {
            private double temp;
            public double getTemp() { return temp; }
        }

        public static class Weather {
            private String icon;
            public String getIcon() { return icon; }
        }

        // Getters
        public String getDt_txt() { return dt_txt; }
        public Main getMain() { return main; }
        public List<Weather> getWeather() { return weather; }
    }

    public List<ForecastItem> getList() { return list; }
}