package punneria;
import java.awt.Color;
public class BooleanSaver {
private Boolean day;
    private Boolean night;
    private Boolean moon;
    private Boolean slug;
    private Boolean selector;
    private Boolean tunnels;
    private Boolean missionComplete;
    private Color sky;
    private Color grass;
private String words;
    public Boolean getDay() {
        return day;
    }
    public Boolean getNight() {
        return night;
    }
    public Boolean getMoon() {
        return moon;
    }
    public Boolean getSlug() {
        return slug;
    }
    public Boolean getSelector() {
        return selector;
    }
    public Boolean getTunnels() {
        return tunnels;
    }
    public Boolean getMissionComplete() {
        return missionComplete;
    }
    public Color getSky() {
        return sky;
    }
    public Color getGrass() {
        return grass;
    }
public String getWords(){
	return words;
}
    public void setDay(Boolean day) {
        this.day = day;
    }
    public void setNight(Boolean night) {
        this.night = night;
    }
    public void setMoon(Boolean moon) {
        this.moon = moon;
    }
    public void setSlug(Boolean slug) {
        this.slug = slug;
    }
    public void setSelector(Boolean selector) {
        this.selector = selector;
    }
    public void setTunnels(Boolean tunnels) {
        this.tunnels = tunnels;
    }
    public void setMissionComplete(Boolean missionComplete) {
        this.missionComplete = missionComplete;
    }
    public void setSky(Color sky) {
        this.sky = sky;
    }
    public void setGrass(Color grass) {
        this.grass = grass;
    }
public void setWords(String words){
	this.words = words;
}
}
