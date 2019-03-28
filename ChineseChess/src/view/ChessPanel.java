package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Set;

import javax.swing.JPanel;

import controller.GameController;
import data.BoardPainter;
import data.Data;
import model.Board;
import model.Checker;

public class ChessPanel extends JPanel implements MouseListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Board board;
	private Point selected;
	private GameController controller;

	public ChessPanel(Board board, GameController controller) {
		this.board = board;
		this.controller = controller;
		this.setPreferredSize(new Dimension(Data.getUnit() * 9, Data.getUnit() * 10));
		this.addMouseListener(this);
	}

	@Override
	public void revalidate() {
		this.setPreferredSize(new Dimension(Data.getUnit() * 9, Data.getUnit() * 10));
		super.revalidate();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		if (this.board != null) {
			BoardPainter.board(g);
			int unit = Data.getUnit();
			int border = Data.getBorder();
			int size = unit - 2 * border;
			Set<Point> checkers = this.board.getCheckers(null);
			for (Point point : checkers)
				BoardPainter.checker(g, this.board.getChecker(point), point.x, point.y);
			if (this.selected != null) {
				int x = this.selected.x * unit + border;
				int y = this.selected.y * unit + border;
				g.setColor(Color.black);
				g.drawRect(x - 2, y - 2, size + 4, size + 4);
				g.drawRect(x - 1, y - 1, size + 2, size + 2);
				g.setColor(Color.green);
				Set<Point> availables = this.board.getAvailableMoves(this.selected);
				if (availables != null)
					for (Point available : availables)
						g.fillRect(unit * available.x + (unit - border) / 2 + 1,
								unit * available.y + (unit - border) / 2 + 1, border, border);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point point = e.getPoint();
		point.x = point.x - Data.getUnit() / 2;
		point.y = point.y - Data.getUnit() / 2;
		point.x = Math.round(point.x / (float) Data.getUnit());
		point.y = Math.round(point.y / (float) Data.getUnit());
		Checker checker = this.board.getChecker(point);
		Point selected = this.selected;
		if (this.selected != null && !this.selected.equals(point) && this.controller.move(this.selected, point))
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