package com.game.domain;

import org.springframework.data.annotation.Id;

public class Play {

	@Id
	private int idPlay;
	private int diceOne;
	private int diceTwo;
	private int isWin;
	private int player;

	public Play(int idPlay, int diceOne, int diceTwo, int isWin, int player) {
		super();
		this.idPlay = idPlay;
		this.diceOne = diceOne;
		this.diceTwo = diceTwo;
		this.isWin = isWin;
		this.player = player;
	}

	public int getIdPlay() {
		return idPlay;
	}

	public void setIdPlay(int idPlay) {
		this.idPlay = idPlay;
	}

	public int getDiceOne() {
		return diceOne;
	}

	public void setDiceOne(int diceOne) {
		this.diceOne = diceOne;
	}

	public int getDiceTwo() {
		return diceTwo;
	}

	public void setDiceTwo(int diceTwo) {
		this.diceTwo = diceTwo;
	}

	public int getIsWin() {
		return isWin;
	}

	public void setIsWin(int isWin) {
		this.isWin = isWin;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	@Override
	public String toString() {
		return "Play [idPlay=" + idPlay + ", diceOne=" + diceOne + ", diceTwo=" + diceTwo + ", isWin=" + isWin
				+ ", player=" + player + "]";
	}

}
