package task.model.ovvatv;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Program {

    private Image image;
    @JsonProperty("realtime_begin")
    private long realtimeBegin;
    @JsonProperty("realtime_end")
    private long realtimeEnd;
    @JsonProperty("will_broadcast_available")
    private Boolean willBroadcastAvailable;
    @JsonProperty("is_on_the_air")
    private Boolean isOnTheAir;
    private String title;
    private String subtitle;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public long getRealtimeBegin() {
        return realtimeBegin;
    }

    public void setRealtimeBegin(long realtimeBegin) {
        this.realtimeBegin = realtimeBegin;
    }

    public long getRealtimeEnd() {
        return realtimeEnd;
    }

    public void setRealtimeEnd(long realtimeEnd) {
        this.realtimeEnd = realtimeEnd;
    }

    public Boolean getWillBroadcastAvailable() {
        return willBroadcastAvailable;
    }

    public void setWillBroadcastAvailable(Boolean willBroadcastAvailable) {
        this.willBroadcastAvailable = willBroadcastAvailable;
    }

    public Boolean getOnTheAir() {
        return isOnTheAir;
    }

    public void setOnTheAir(Boolean onTheAir) {
        isOnTheAir = onTheAir;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @Override
    public String toString() {
        return "Program{" +
                "image=" + image +
                ", realtimeBegin=" + realtimeBegin +
                ", realtimeEnd=" + realtimeEnd +
                ", willBroadcastAvailable=" + willBroadcastAvailable +
                ", isOnTheAir=" + isOnTheAir +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                '}';
    }
}
