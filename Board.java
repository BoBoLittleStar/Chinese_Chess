package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Checker.Player;
import model.Checker.Type;

public class Board implements Cloneable {
	private Map<Point, Checker> board;
	private Player currentPlayer;
	private Checker lastTaken;
	private List<Checker> takenCheckers;
	private Player winner;
	private boolean manual;

	public Board() {
		this.board = new HashMap<>();
		this.takenCheckers = new ArrayList<>();
	}

	public void clear() {
		this.board.clear();
		this.board.put(new Point(5, 0), new Checker(Type.SHUAI, Player.BLACK));
		this.board.put(new Point(5, 9), new Checker(Type.SHUAI, Player.RED));
		this.takenCheckers.clear();
		this.winner = null;
	}

	public void toggleMode() {
		this.manual = !this.manual;
	}

	public boolean getMode() {
		return this.manual;
	}

	public void put(Point point, Checker checker) {
		if (this.manual)
			this.board.put(point, checker);
	}

	public void start() {
		this.board.clear();
		this.board.put(new Point(0, 0), new Checker(Type.JU, Player.BLACK));
		this.board.put(new Point(1, 0), new Checker(Type.MA, Player.BLACK));
		this.board.put(new Point(2, 0), new Checker(Type.XIANG, Player.BLACK));
		this.board.put(new Point(3, 0), new Checker(Type.SHI, Player.BLACK));
		this.board.put(new Point(4, 0), new Checker(Type.SHUAI, Player.BLACK));
		this.board.put(new Point(5, 0), new Checker(Type.SHI, Player.BLACK));
		this.board.put(new Point(6, 0), new Checker(Type.XIANG, Player.BLACK));
		this.board.put(new Point(7, 0), new Checker(Type.MA, Player.BLACK));
		this.board.put(new Point(8, 0), new Checker(Type.JU, Player.BLACK));
		this.board.put(new Point(1, 2), new Checker(Type.PAO, Player.BLACK));
		this.board.put(new Point(7, 2), new Checker(Type.PAO, Player.BLACK));
		this.board.put(new Point(0, 3), new Checker(Type.BING, Player.BLACK));
		this.board.put(new Point(2, 3), new Checker(Type.BING, Player.BLACK));
		this.board.put(new Point(4, 3), new Checker(Type.BING, Player.BLACK));
		this.board.put(new Point(6, 3), new Checker(Type.BING, Player.BLACK));
		this.board.put(new Point(8, 3), new Checker(Type.BING, Player.BLACK));
		this.board.put(new Point(0, 9), new Checker(Type.JU, Player.RED));
		this.board.put(new Point(1, 9), new Checker(Type.MA, Player.RED));
		this.board.put(new Point(2, 9), new Checker(Type.XIANG, Player.RED));
		this.board.put(new Point(3, 9), new Checker(Type.SHI, Player.RED));
		this.board.put(new Point(4, 9), new Checker(Type.SHUAI, Player.RED));
		this.board.put(new Point(5, 9), new Checker(Type.SHI, Player.RED));
		this.board.put(new Point(6, 9), new Checker(Type.XIANG, Player.RED));
		this.board.put(new Point(7, 9), new Checker(Type.MA, Player.RED));
		this.board.put(new Point(8, 9), new Checker(Type.JU, Player.RED));
		this.board.put(new Point(1, 7), new Checker(Type.PAO, Player.RED));
		this.board.put(new Point(7, 7), new Checker(Type.PAO, Player.RED));
		this.board.put(new Point(0, 6), new Checker(Type.BING, Player.RED));
		this.board.put(new Point(2, 6), new Checker(Type.BING, Player.RED));
		this.board.put(new Point(4, 6), new Checker(Type.BING, Player.RED));
		this.board.put(new Point(6, 6), new Checker(Type.BING, Player.RED));
		this.board.put(new Point(8, 6), new Checker(Type.BING, Player.RED));
		this.currentPlayer = Player.RED;
	}

	public boolean move(Point src, Point dest) {
		if (this.winner != null || !this.checkMove(src, dest))
			return false;
		this.lastTaken = this.board.get(dest);
		if (this.lastTaken != null)
			this.takenCheckers.add(this.lastTaken);
		this.board.put(dest, this.board.get(src));
		this.board.remove(src);
		this.currentPlayer = this.currentPlayer != Player.BLACK ? Player.BLACK : Player.RED;
		if (this.lastTaken != null && this.lastTaken.type == Type.SHUAI)
			this.winner = this.currentPlayer;
		return true;
	}

	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}

	public Player getWinner() {
		return this.winner;
	}

	public Checker lastTaken() {
		return this.lastTaken;
	}

	public Set<Point> getAvailableMoves(Point point) {
		Checker checker = this.board.get(point);
		if (checker == null)
			return null;
		Point dest;
		Set<Point> set = new HashSet<>();
		if (checker.type == Type.BING) {
			if (checker.player == Player.BLACK) {
				dest = new Point(point.x, point.y + 1);
				if (this.checkMove(point, dest))
					set.add(dest);
				if (point.y > 4) {
					dest = new Point(point.x + 1, point.y);
					if (this.checkMove(point, dest))
						set.add(dest);
					dest = new Point(point.x - 1, point.y);
					if (this.checkMove(point, dest))
						set.add(dest);
				}
			} else if (checker.player == Player.RED) {
				dest = new Point(point.x, point.y - 1);
				if (this.checkMove(point, dest))
					set.add(dest);
				if (point.y < 5) {
					dest = new Point(point.x + 1, point.y);
					if (this.checkMove(point, dest))
						set.add(dest);
					dest = new Point(point.x - 1, point.y);
					if (this.checkMove(point, dest))
						set.add(dest);
				}
			}
		} else if (checker.type == Type.JU) {
			for (int x = point.x - 1; x >= 0; x--) {
				dest = new Point(x, point.y);
				if (this.checkMove(point, dest))
					set.add(dest);
				else
					break;
			}
			for (int x = point.x + 1; x <= 8; x++) {
				dest = new Point(x, point.y);
				if (this.checkMove(point, dest))
					set.add(dest);
				else
					break;
			}
			for (int y = point.y - 1; y >= 0; y--) {
				dest = new Point(point.x, y);
				if (this.checkMove(point, dest))
					set.add(dest);
				else
					break;
			}
			for (int y = point.y + 1; y <= 9; y++) {
				dest = new Point(point.x, y);
				if (this.checkMove(point, dest))
					set.add(dest);
				else
					break;
			}
		} else if (checker.type == Type.MA) {
			dest = new Point(point.x - 1, point.y - 2);
			if (this.checkMove(point, dest))
				set.add(dest);
			dest = new Point(point.x - 1, point.y + 2);
			if (this.checkMove(point, dest))
				set.add(dest);
			dest = new Point(point.x + 1, point.y - 2);
			if (this.checkMove(point, dest))
				set.add(dest);
			dest = new Point(point.x + 1, point.y + 2);
			if (this.checkMove(point, dest))
				set.add(dest);
			dest = new Point(point.x - 2, point.y - 1);
			if (this.checkMove(point, dest))
				set.add(dest);
			dest = new Point(point.x - 2, point.y + 1);
			if (this.checkMove(point, dest))
				set.add(dest);
			dest = new Point(point.x + 2, point.y - 1);
			if (this.checkMove(point, dest))
				set.add(dest);
			dest = new Point(point.x + 2, point.y + 1);
			if (this.checkMove(point, dest))
				set.add(dest);
		} else if (checker.type == Type.PAO) {
			for (int x = point.x - 1; x >= 0; x--) {
				dest = new Point(x, point.y);
				if (this.checkMove(point, dest))
					set.add(dest);
			}
			for (int x = point.x + 1; x <= 8; x++) {
				dest = new Point(x, point.y);
				if (this.checkMove(point, dest))
					set.add(dest);
			}
			for (int y = point.y - 1; y >= 0; y--) {
				dest = new Point(point.x, y);
				if (this.checkMove(point, dest))
					set.add(dest);
			}
			for (int y = point.y + 1; y <= 9; y++) {
				dest = new Point(point.x, y);
				if (this.checkMove(point, dest))
					set.add(dest);
			}
		} else if (checker.type == Type.SHI) {
			if (point.x == 4) {
				dest = new Point(point.x - 1, point.y - 1);
				if (this.checkMove(point, dest))
					set.add(dest);
				dest = new Point(point.x + 1, point.y - 1);
				if (this.checkMove(point, dest))
					set.add(dest);
				dest = new Point(point.x - 1, point.y + 1);
				if (this.checkMove(point, dest))
					set.add(dest);
				dest = new Point(point.x + 1, point.y + 1);
				if (this.checkMove(point, dest))
					set.add(dest);
			} else {
				dest = checker.player == Player.BLACK ? new Point(4, 1) : new Point(4, 8);
				if (this.checkMove(point, dest))
					set.add(dest);
			}
		} else if (checker.type == Type.SHUAI) {
			dest = new Point(point.x, point.y - 1);
			if (this.checkMove(point, dest))
				set.add(dest);
			dest = new Point(point.x, point.y + 1);
			if (this.checkMove(point, dest))
				set.add(dest);
			dest = new Point(point.x - 1, point.y);
			if (this.checkMove(point, dest))
				set.add(dest);
			dest = new Point(point.x + 1, point.y);
			if (this.checkMove(point, dest))
				set.add(dest);
		} else if (checker.type == Type.XIANG) {
			dest = new Point(point.x - 2, point.y - 2);
			if (this.checkMove(point, dest))
				set.add(dest);
			dest = new Point(point.x - 2, point.y + 2);
			if (this.checkMove(point, dest))
				set.add(dest);
			dest = new Point(point.x + 2, point.y - 2);
			if (this.checkMove(point, dest))
				set.add(dest);
			dest = new Point(point.x + 2, point.y + 2);
			if (this.checkMove(point, dest))
				set.add(dest);
		}
		return set;
	}

	private boolean checkMove(Point srcPoint, Point destPoint) {
		if (srcPoint.equals(destPoint))
			return false;
		Checker src = this.board.get(srcPoint);
		if (src == null)
			return false;
		if (this.currentPlayer != null && src.player != this.currentPlayer)
			return false;
		Checker dest = this.board.get(destPoint);
		if (dest != null && src.player == dest.player)
			return false;
		if (destPoint.x < 0 || destPoint.x > 9 || destPoint.y < 0 || destPoint.y > 10)
			return false;
		if (src.type == Type.BING) {
			int x = Math.abs(destPoint.x - srcPoint.x);
			int y = Math.abs(destPoint.y - srcPoint.y);
			if (src.player == Player.BLACK)
				return x + y == 1 && destPoint.y >= srcPoint.y && (srcPoint.y <= 4 && x == 0 || srcPoint.y > 4);
			else
				return x + y == 1 && destPoint.y <= srcPoint.y && (srcPoint.y >= 5 && x == 0 || srcPoint.y < 5);
		} else if (src.type == Type.JU) {
			if (destPoint.x == srcPoint.x) {
				for (int n = Math.abs(destPoint.y - srcPoint.y) - 1; n > 0; n--) {
					Checker pass = this.board
							.get(new Point(srcPoint.x, srcPoint.y + (destPoint.y > srcPoint.y ? n : -n)));
					if (pass != null)
						return false;
				}
				return true;
			} else if (destPoint.y == srcPoint.y) {
				for (int n = Math.abs(destPoint.x - srcPoint.x) - 1; n > 0; n--) {
					Checker pass = this.board
							.get(new Point(srcPoint.x + (destPoint.x > srcPoint.x ? n : -n), srcPoint.y));
					if (pass != null)
						return false;
				}
				return true;
			}
		} else if (src.type == Type.MA) {
			int x = Math.abs(destPoint.x - srcPoint.x);
			int y = Math.abs(destPoint.y - srcPoint.y);
			if (x + y != 3 || x == 0 || y == 0)
				return false;
			else if (x == 2)
				return this.board.get(new Point(srcPoint.x + (destPoint.x > srcPoint.x ? 1 : -1), srcPoint.y)) == null;
			else if (y == 2)
				return this.board.get(new Point(srcPoint.x, srcPoint.y + (destPoint.y > srcPoint.y ? 1 : -1))) == null;
		} else if (src.type == Type.PAO) {
			if (destPoint.x != srcPoint.x && destPoint.y != srcPoint.y)
				return false;
			else if (dest == null) {
				if (destPoint.x == srcPoint.x)
					for (int n = Math.abs(destPoint.y - srcPoint.y) - 1; n > 0; n--) {
						Checker pass = this.board
								.get(new Point(srcPoint.x, srcPoint.y + (destPoint.y > srcPoint.y ? n : -n)));
						if (pass != null)
							return false;
					}
				else
					for (int n = Math.abs(destPoint.x - srcPoint.x) - 1; n > 0; n--) {
						Checker pass = this.board
								.get(new Point(srcPoint.x + (destPoint.x > srcPoint.x ? n : -n), srcPoint.y));
						if (pass != null)
							return false;
					}
				return true;
			} else {
				boolean passed = false;
				if (destPoint.x == srcPoint.x)
					for (int n = Math.abs(destPoint.y - srcPoint.y) - 1; n > 0; n--) {
						Checker pass = this.board
								.get(new Point(srcPoint.x, srcPoint.y + (destPoint.y > srcPoint.y ? n : -n)));
						if (pass != null)
							if (!passed)
								passed = true;
							else
								return false;
					}
				else
					for (int n = Math.abs(destPoint.x - srcPoint.x) - 1; n > 0; n--) {
						Checker pass = this.board
								.get(new Point(srcPoint.x + (destPoint.x > srcPoint.x ? n : -n), srcPoint.y));
						if (pass != null)
							if (!passed)
								passed = true;
							else
								return false;
					}
				return passed;
			}
		} else if (src.type == Type.SHI) {
			int x = Math.abs(destPoint.x - srcPoint.x);
			int y = Math.abs(destPoint.y - srcPoint.y);
			if (x != 1 || y != 1)
				return false;
			else if (destPoint.x >= 3 && destPoint.x <= 5)
				if (src.player == Player.BLACK)
					return destPoint.y <= 2;
				else
					return destPoint.y >= 7;
		} else if (src.type == Type.SHUAI) {
			int x = Math.abs(destPoint.x - srcPoint.x);
			int y = Math.abs(destPoint.y - srcPoint.y);
			if (dest != null && dest.type == Type.SHUAI) {
				if (x != 0)
					return false;
				for (int y2 = y - 1; y2 > 0; y2--)
					if (this.board
							.get(new Point(srcPoint.x, srcPoint.y + (destPoint.y > srcPoint.y ? y2 : -y2))) != null)
						return false;
				return true;
			}
			if (x + y != 1)
				return false;
			else if (destPoint.x >= 3 && destPoint.x <= 5)
				if (src.player == Player.BLACK)
					return destPoint.y <= 2;
				else
					return destPoint.y >= 7;
		} else if (src.type == Type.XIANG) {
			int x = Math.abs(destPoint.x - srcPoint.x);
			int y = Math.abs(destPoint.y - srcPoint.y);
			if (x + y != 4 || x != 2)
				return false;
			else if (src.player == Player.BLACK && destPoint.y <= 4 || src.player == Player.RED && destPoint.y >= 5)
				return this.board.get(new Point(srcPoint.x + (destPoint.x > srcPoint.x ? 1 : -1),
						srcPoint.y + (destPoint.y > srcPoint.y ? 1 : -1))) == null;
		}
		return false;
	}

	public Checker getChecker(Point loc) {
		return this.board.get(loc);
	}

	public Set<Point> getCheckers(Player player) {
		Set<Point> set = this.board.keySet();
		Set<Point> result = new HashSet<>();
		for (Point point : set)
			if (player == null || this.board.get(point).player == player)
				result.add(point);
		return result;
	}

	@Override
	public Board clone() {
		Board board = new Board();
		board.board.putAll(this.board);
		board.currentPlayer = this.currentPlayer;
		board.lastTaken = this.lastTaken;
		board.takenCheckers.addAll(this.takenCheckers);
		board.winner = this.winner;
		return board;
	}
}