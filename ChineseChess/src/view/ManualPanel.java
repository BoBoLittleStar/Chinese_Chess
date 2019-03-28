package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import controller.GameController;
import data.BoardPainter;
import data.Data;
import model.Board;
import model.Checker;
import model.Checker.Player;

public class ManualPanel extends JPanel implements MouseListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Board board;
	private GameController controller;
	private AddPanel addPanel;

	public ManualPanel(Board board, GameController controller) {
		this.board = board;
		this.controller = controller;
		this.addPanel = new AddPanel();
		this.setLayout(null);
		this.add(this.addPanel);
		this.addPanel.setVisible(false);
		this.setPreferredSize(new Dimension(Data.getUnit() * 9, Data.getUnit() * 10));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		if (this.board != null) {
			BoardPainter.board(g);
			g.setFont(Data.getFont());
			FontMetrics m = g.getFontMetrics(Data.getFont());
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
				String s = checker.toString();
				x = x + (size - m.stringWidth(s)) / 2;
				y = y + (size - m.getHeight()) / 2 + m.getAscent();
				g.setColor(Color.white);
				g.drawString(s, x, y);
			}
		}
	}

	@Override
	public void revalidate() {
		this.setPreferredSize(new Dimension(Data.getUnit() * 9, Data.getUnit() * 10));
		super.revalidate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point point = e.getPoint();
		point.x = point.x - Data.getUnit() / 2;
		point.y = point.y - Data.getUnit() / 2;
		point.x = Math.round(point.x / (float) Data.getUnit());
		point.y = Math.round(point.y / (float) Data.getUnit());

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

	class AddPanel extends JPanel implements MouseListener, MouseMotionListener {

		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		private Player player;

		AddPanel() {
		}

		@Override
		public void paintComponent(Graphics g) {

		}

		@Override
		public void mouseDragged(MouseEvent e) {
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			Point p = e.getPoint();
			p.x = p.x / 3;
			p.y = p.y / 3;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
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
}