package punneria;

import java.awt.Color;

public class PunnSet {
private Color Triangle;
private Integer across;
private String name;
private long p$;
private Integer missions;
public Color getTriangle(){
	return Triangle;
}
public void setTriangle(Color Triangle){
	this.Triangle = Triangle;
}
public Integer getAcross(){
	return across;
}
public void setAcross(Integer across){
	this.across = across;
}
public String getName(){
	return name;
}
public void setName(String name){
	this.name = name;
}
public long getP$() {
	return this.p$;
}
public void setP$(long p$) {
	this.p$ = p$;
}
public Integer getMissions() {
	return missions;
}
public void setMissions(Integer missions) {
	this.missions = missions;
}
}