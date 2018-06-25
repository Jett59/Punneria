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
public Boolean retreveDay(){
	return day;
}
public Boolean retreveNight(){
	return night;
}
public Boolean retreveMoon(){
	return moon;
}
public Boolean retreveSlug(){
	return slug;
}
public Boolean retreveSelector(){
	return selector;
}
public Boolean retreveTunnels(){
	return tunnels;
}
public Boolean retreveMissionComplete(){
	return missionComplete;
}
public Color retreveSky(){
	return sky;
}
public Color retreveGrass(){
	return grass;
}
public void saveDay(Boolean day){
	this.day = day;
}
public void saveNight(Boolean night){
	this.night = night;
}
public void saveMoon(Boolean moon){
	this.moon = moon;
}
public void saveSlug(Boolean slug){
	this.slug = slug;
}
public void saveSelector(Boolean selector){
this.selector = selector;	
}
public void saveTunnels(Boolean tunnels){
	this.tunnels = tunnels;
}
public void saveMissionComplete(Boolean missionComplete){
	this.missionComplete = missionComplete;
}
public void saveSky(Color sky){
	this.sky = sky;
}
public void saveGrass(Color grass){
	this.grass = grass;
}
}
