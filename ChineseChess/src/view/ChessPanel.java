package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Set;

import javax.swing.JPanel;

import data.BoardPainter;
import data.Data;
import model.Board;
import model.Checker;
import model.Checker.Player;

public class ChessPanel extends JPanel implements MouseListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final Board board;
	private BufferedImage boardImage;
	private Point selected;

	public ChessPanel(Board board) {
		this.board = board;
		this.boardImage = BoardPainter.board();
		this.setPreferredSize(new Dimension(this.boardImage.getWidth(), this.boardImage.getHeight()));
		this.addMouseListener(this);
	}

	public boolean move(Point src, Point dest) {
		boolean moved = this.board.move(src, dest);
		this.repaint();
		return moved;
	}

	@Override
	public void revalidate() {
		this.boardImage = BoardPainter.board();
		this.setPreferredSize(new Dimension(this.boardImage.getWidth(), this.boardImage.getHeight()));
		super.revalidate();
	}

	@Override
	public void paint(Graphics g) {
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		if (this.board != null) {
			g.drawImage(this.boardImage, 0, 0, this.boardImage.getWidth(), this.boardImage.getHeight(), null);
			g.setFont(Data.getFont());
			int unit = Data.getUnit();
			int border = Data.getBorder();
			int size = unit - 2 * border;
			for (Point point : this.board.getCheckers(null)) {
				int x = point.x * unit + border;
				int y = point.y * unit + border;
				g.setColor(Color.white);
				g.fillOval(x, y, size, size);
				Checker checker = this.board.getChecker(point);
				Player player = checker.player;
				g.setColor(player == Player.RED ? Data.RED : Data.BLACK);
				g.fillOval(x + 2, y + 2, size - 4, size - 4);
				g.setColor(Color.black);
				g.drawOval(x, y, size, size);
				g.drawOval(x + 3, y + 3, size - 6, size - 6);
				FontMetrics m = g.getFontMetrics(Data.getFont());
				Rectangle r = new Rectangle(x, y, size, size);
				String s = checker.toString();
				x = r.x + (r.width - m.stringWidth(s)) / 2;
				y = r.y + (r.height - m.getHeight()) / 2 + m.getAscent();
				g.setColor(Color.white);
				g.drawString(s, x, y);
			}
			if (this.selected != null) {
				int x = this.selected.x * unit + border;
				int y = this.selected.y * unit + border;
				g.drawRect(x - 2, y - 2, size + 4, size + 4);
				g.drawRect(x - 1, y - 1, size + 2, size + 2);
				g.setColor(Color.green);
				Set<Point> availables = this.board.getAvailableMoves(this.selected);
				for (Point available : availables)
					g.fillRect(unit * available.x + (unit - border) / 2 + 1,
							unit * available.y + (unit - border) / 2 + 1, border, border);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point selected = this.selected;
		Point point = e.getPoint();
		point.x = point.x - Data.getUnit() / 2;
		point.y = point.y - Data.getUnit() / 2;
		point.x = Math.round(point.x / (float) Data.getUnit());
		point.y = Math.round(point.y / (float) Data.getUnit());
		Checker checker = this.board.getChecker(point);
		if (this.selected != null && !this.selected.equals(point) && this.move(this.selected, point))
			this.selected = null;
		else if ((this.selected == null || !this.selected.equals(point)) && checker != null
				&& checker.player == this.board.getCurrentPlayer())
			this.selected = point;
		else
			this.selected = null;
		if (selected != this.selected)
			this.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}