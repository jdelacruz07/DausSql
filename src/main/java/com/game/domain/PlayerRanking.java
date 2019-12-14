package com.game.domain;

public class PlayerRanking {

	private int idPlayer;
	private double avg;

	public PlayerRanking(int idPlayer, double avg) {
		this.idPlayer = idPlayer;
		this.avg = avg;
	}

	public int getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(int idPlayer) {
		this.idPlayer = idPlayer;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

}
