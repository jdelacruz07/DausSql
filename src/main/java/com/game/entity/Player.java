package com.game.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Player implements Serializable {

	private int idPlayer;
	@NotNull
    @Size(min=2, max=40)
	private String name;
	private double avg;
	
	
	
	public Player(int idPlayer, @NotNull @Size(min = 2, max = 40) String name, double avg) {
		super();
		this.idPlayer = idPlayer;
		this.name = name;
		this.avg = avg;
	}
	public Player() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the idPlayer
	 */
	public int getIdPlayer() {
		return idPlayer;
	}
	/**
	 * @param idPlayer the idPlayer to set
	 */
	public void setIdPlayer(int idPlayer) {
		this.idPlayer = idPlayer;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the avg
	 */
	public double getAvg() {
		return avg;
	}
	/**
	 * @param avg the avg to set
	 */
	public void setAvg(double avg) {
		this.avg = avg;
	}
	@Override
	public String toString() {
		return "Player [idPlayer=" + idPlayer + ", name=" + name + ", avg=" + avg + "]";
	}
	
	
	
	
	
}
