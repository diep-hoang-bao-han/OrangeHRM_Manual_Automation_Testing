package due.giuaky221121514210.ui.Day3_Network.model;

import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("title")
    private String title;

    @SerializedName("release_date")
    private String date;

    @SerializedName("overview")
    private String description;

    @SerializedName("poster_path")
    private String image;

    // Getter và Setter
    public String getTitle() { return title; }
    public String getDate() { return date; }
    public String getDescription() { return description; }
    public String getImage() { return image; }

    public void setTitle(String title) { this.title = title; }
    public void setDate(String date) { this.date = date; }
    public void setDescription(String description) { this.description = description; }
    public void setImage(String image) { this.image = image; }
}