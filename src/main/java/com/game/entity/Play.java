package com.game.entity;

import org.springframework.data.annotation.Id;

public class Play {

	@Id
	private int idPlay;
	private int diceOne;
	private int diceTwo;
	private int isWin;
	
	private Player player;

	public Play(int idPlay, int diceOne, int diceTwo, int isWin, Player player) {
		super();
		this.idPlay = idPlay;
		this.diceOne = diceOne;
		this.diceTwo = diceTwo;
		this.isWin = isWin;
		this.player = player;
	}

	public Play() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the idPlay
	 */
	public int getIdPlay() {
		return idPlay;
	}

	/**
	 * @param idPlay the idPlay to set
	 */
	public void setIdPlay(int idPlay) {
		this.idPlay = idPlay;
	}

	/**
	 * @return the diceOne
	 */
	public int getDiceOne() {
		return diceOne;
	}

	/**
	 * @param diceOne the diceOne to set
	 */
	public void setDiceOne(int diceOne) {
		this.diceOne = diceOne;
	}

	/**
	 * @return the diceTwo
	 */
	public int getDiceTwo() {
		return diceTwo;
	}

	/**
	 * @param diceTwo the diceTwo to set
	 */
	public void setDiceTwo(int diceTwo) {
		this.diceTwo = diceTwo;
	}

	/**
	 * @return the isWin
	 */
	public int getIsWin() {
		return isWin;
	}

	/**
	 * @param isWin the isWin to set
	 */
	public void setIsWin(int isWin) {
		this.isWin = isWin;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public String toString() {
		return "Play [idPlay=" + idPlay + ", diceOne=" + diceOne + ", diceTwo=" + diceTwo + ", isWin=" + isWin
				+ ", player=" + player + "]";
	}

	
	
	
}
