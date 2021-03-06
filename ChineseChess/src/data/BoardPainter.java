package data;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import model.Checker;
import model.Checker.Player;

public class BoardPainter {
	public static void board(Graphics g) {
		int unit = Data.getUnit();
		g.setColor(Color.white);
		g.fillRect(0, 0, unit * 9, unit * 10);
		g.setColor(Color.black);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		for (int x = 0; x < 9; x++)
			g.drawLine(x * unit + unit / 2, 0, x * unit + unit / 2, unit * 19);
		for (int y = 0; y < 10; y++)
			g.drawLine(0, y * unit + unit / 2, unit * 9, y * unit + unit / 2);
		g.drawLine(unit * 7 / 2, unit / 2, unit * 11 / 2, unit * 5 / 2);
		g.drawLine(unit * 11 / 2, unit / 2, unit * 7 / 2, unit * 5 / 2);
		g.drawLine(unit * 7 / 2, unit * 15 / 2, unit * 11 / 2, unit * 19 / 2);
		g.drawLine(unit * 11 / 2, unit * 15 / 2, unit * 7 / 2, unit * 19 / 2);
		BoardPainter.mark(g, 1, 2, unit);
		BoardPainter.mark(g, 7, 2, unit);
		BoardPainter.mark(g, 0, 3, unit);
		BoardPainter.mark(g, 2, 3, unit);
		BoardPainter.mark(g, 4, 3, unit);
		BoardPainter.mark(g, 6, 3, unit);
		BoardPainter.mark(g, 8, 3, unit);
		BoardPainter.mark(g, 1, 7, unit);
		BoardPainter.mark(g, 7, 7, unit);
		BoardPainter.mark(g, 0, 6, unit);
		BoardPainter.mark(g, 2, 6, unit);
		BoardPainter.mark(g, 4, 6, unit);
		BoardPainter.mark(g, 6, 6, unit);
		BoardPainter.mark(g, 8, 6, unit);
		g.setColor(Color.white);
		g.fillRect(0, 0, unit * 9, unit / 2);
		g.fillRect(0, 0, unit / 2, unit * 10);
		g.fillRect(unit * 17 / 2 + 1, 0, unit / 2, unit * 10);
		g.fillRect(0, unit * 19 / 2 + 1, unit * 9, unit / 2);
		g.fillRect(unit / 2 + 1, unit * 9 / 2 + 1, unit * 8 - 1, unit - 1);
		g.setColor(Color.black);
		g.drawLine(unit / 2 - 5, unit / 2 - 5, unit * 17 / 2 + 5, unit / 2 - 5);
		g.drawLine(unit / 2 - 4, unit / 2 - 4, unit * 17 / 2 + 4, unit / 2 - 4);
		g.drawLine(unit / 2 - 3, unit / 2 - 3, unit * 17 / 2 + 3, unit / 2 - 3);
		g.drawLine(unit / 2 - 5, unit / 2 - 5, unit / 2 - 5, unit * 19 / 2 + 5);
		g.drawLine(unit / 2 - 4, unit / 2 - 4, unit / 2 - 4, unit * 19 / 2 + 4);
		g.drawLine(unit / 2 - 3, unit / 2 - 3, unit / 2 - 3, unit * 19 / 2 + 3);
		g.drawLine(unit * 17 / 2 + 5, unit / 2 - 5, unit * 17 / 2 + 5, unit * 19 / 2 + 5);
		g.drawLine(unit * 17 / 2 + 4, unit / 2 - 4, unit * 17 / 2 + 4, unit * 19 / 2 + 4);
		g.drawLine(unit * 17 / 2 + 3, unit / 2 - 3, unit * 17 / 2 + 3, unit * 19 / 2 + 3);
		g.drawLine(unit / 2 - 5, unit * 19 / 2 + 5, unit * 17 / 2 + 5, unit * 19 / 2 + 5);
		g.drawLine(unit / 2 - 4, unit * 19 / 2 + 4, unit * 17 / 2 + 4, unit * 19 / 2 + 4);
		g.drawLine(unit / 2 - 3, unit * 19 / 2 + 3, unit * 17 / 2 + 3, unit * 19 / 2 + 3);
		g.setFont(Data.getFont());
		BoardPainter.draw(g, Data.CHU, unit, unit * 9 / 2, unit, unit);
		BoardPainter.draw(g, Data.HE, unit * 2, unit * 9 / 2, unit, unit);
		BoardPainter.draw(g, Data.HAN, unit * 6, unit * 9 / 2, unit, unit);
		BoardPainter.draw(g, Data.JIE, unit * 7, unit * 9 / 2, unit, unit);
	}

	public static void checker(Graphics g, Checker checker, int x, int y) {
		int unit = Data.getUnit();
		int border = Data.getBorder();
		int size = unit - 2 * border;
		x = x * unit + border;
		y = y * unit + border;
		g.setColor(Color.white);
		g.fillOval(x, y, size, size);
		g.setColor(checker.player == Player.RED ? Data.RED : Data.BLACK);
		g.fillOval(x + 2, y + 2, size - 4, size - 4);
		g.setColor(Color.black);
		g.drawOval(x, y, size, size);
		g.drawOval(x + 3, y + 3, size - 6, size - 6);
		FontMetrics m = g.getFontMetrics(Data.getFont());
		String s = checker.toString();
		x = x + (size - m.stringWidth(s)) / 2;
		y = y + (size - m.getHeight()) / 2 + m.getAscent();
		g.setColor(Color.white);
		g.setFont(Data.getFont());
		g.drawString(s, x, y);
	}

	private static void draw(Graphics g, String s, int x, int y, int width, int height) {
		FontMetrics m = g.getFontMetrics(Data.getFont());
		x = x + (width - m.stringWidth(s)) / 2;
		y = y + (height - m.getHeight()) / 2 + m.getAscent();
		g.drawString(s, x, y);
	}

	private static void mark(Graphics g, int x, int y, int unit) {
		x = x * unit + unit / 2;
		y = y * unit + unit / 2;
		int size = unit / 5;
		int scale = unit / 20;
		g.drawLine(x - scale, y - size, x - scale, y - scale);
		g.drawLine(x - size, y - scale, x - scale, y - scale);
		g.drawLine(x + scale, y - size, x + scale, y - scale);
		g.drawLine(x + size, y - scale, x + scale, y - scale);
		g.drawLine(x - scale, y + size, x - scale, y + scale);
		g.drawLine(x - size, y + scale, x - scale, y + scale);
		g.drawLine(x + scale, y + size, x + scale, y + scale);
		g.drawLine(x + size, y + scale, x + scale, y + scale);
	}
}