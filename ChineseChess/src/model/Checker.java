package model;

import data.Data;

public final class Checker {
	public final Type type;
	public final Player player;

	public Checker(Type type, Player player) {
		if (type == null || player == null)
			throw new NullPointerException();
		this.type = type;
		this.player = player;
	}

	public enum Type {
		JU, MA, PAO, XIANG, SHI, BING, SHUAI;
	}

	public enum Player {
		RED, BLACK;
	}

	@Override
	public String toString() {
		switch (this.type) {
		case BING:
			return this.player == Player.RED ? Data.HONG_BING : Data.HEI_ZU;
		case JU:
			return this.player == Player.RED ? Data.HONG_JU : Data.HEI_JU;
		case MA:
			return this.player == Player.RED ? Data.HONG_MA : Data.HEI_MA;
		case PAO:
			return this.player == Player.RED ? Data.HONG_PAO : Data.HEI_PAO;
		case SHI:
			return this.player == Player.RED ? Data.HONG_SHI : Data.HEI_SHI;
		case SHUAI:
			return this.player == Player.RED ? Data.HONG_SHUAI : Data.HEI_JIANG;
		case XIANG:
			return this.player == Player.RED ? Data.HONG_XIANG : Data.HEI_XIANG;
		default:
			throw new RuntimeException();
		}
	}
}