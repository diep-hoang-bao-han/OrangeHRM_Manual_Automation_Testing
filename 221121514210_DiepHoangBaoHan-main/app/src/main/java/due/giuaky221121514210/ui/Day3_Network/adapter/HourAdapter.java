package due.giuaky221121514210.ui.Day3_Network.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import due.giuaky221121514210.R;
import due.giuaky221121514210.ui.Day3_Network.ForecastResponse;

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourHolder> {
    private Context context;
    private List<ForecastResponse.ForecastItem> forecastItems;

    public HourAdapter(Context context, List<ForecastResponse.ForecastItem> forecastItems) {
        this.context = context;
        this.forecastItems = forecastItems;
    }

    @NonNull
    @Override
    public HourHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_hour, parent, false);
        return new HourHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HourHolder holder, int position) {
        ForecastResponse.ForecastItem item = forecastItems.get(position);

        // Set time
        holder.tvTime.setText(convertTime(item.getDt_txt()));

        // Set temperature
        if (item.getMain() != null) {
            holder.tvTem.setText(String.format("%.0f°", item.getMain().getTemp()));
        }

        // Set weather icon
        if (item.getWeather() != null && !item.getWeather().isEmpty()) {
            String iconCode = item.getWeather().get(0).getIcon();
            String iconUrl = "https://openweathermap.org/img/wn/" + iconCode + "@2x.png";
            Glide.with(context).load(iconUrl).into(holder.icon);
        }
    }

    @Override
    public int getItemCount() {
        return forecastItems != null ? forecastItems.size() : 0;
    }

    public static class HourHolder extends RecyclerView.ViewHolder {
        TextView tvTime;
        ImageView icon;
        TextView tvTem;

        public HourHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tvTime);
            icon = itemView.findViewById(R.id.icon);
            tvTem = itemView.findViewById(R.id.tvTem);
        }
    }

    private String convertTime(String inputTime) {
        SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = inFormat.parse(inputTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return inputTime; // return original if parsing fails
        }
        SimpleDateFormat outFormat = new SimpleDateFormat("ha");
        return outFormat.format(date);
    }
}