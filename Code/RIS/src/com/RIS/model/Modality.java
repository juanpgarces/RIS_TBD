package com.RIS.model;
import javafx.beans.property.SimpleStringProperty;

public class Modality {

	private SimpleStringProperty name;
	private int modId, duration;
	private double cost;
	
	public Modality(int modId, String name, double cost, int duration) {
		this.modId  = modId;
		this.name  = new SimpleStringProperty(name);
		this.cost  = cost;
		this.duration  = duration;	
	}
	
	/* Start of GETTERS AND SETTERS */
	public int getModId() {
		return modId;
	}

	public void setModId(int modId) {
		this.modId = modId;
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Modality [name=" + name + ", modId=" + modId + ", duration=" + duration + ", cost=" + cost + "]";
	}

	
}
