package com.game.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Player {

	private int idPlayer;
	@NotNull
	@Size(min = 2, max = 40)
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

	public int getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(int idPlayer) {
		this.idPlayer = idPlayer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	@Override
	public String toString() {
		return "Player [idPlayer=" + idPlayer + ", name=" + name + ", avg=" + avg + "]";
	}

}
