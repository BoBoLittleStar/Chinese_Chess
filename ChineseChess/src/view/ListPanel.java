package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import data.BoardPainter;
import data.Data;
import model.Checker;

public class ListPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<Checker> list;

	public ListPanel() {
		int unit = Data.getUnit();
		int border = Data.getBorder();
		int size = unit - 2 * border;
		this.setPreferredSize(new Dimension(size * 3, size * 5));
		this.setBackground(Color.white);
		this.list = new ArrayList<>();
	}

	public void add(Checker checker) {
		this.list.add(checker);
		this.repaint();
	}

	public void clear() {
		this.list.clear();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0, length = this.list.size(); i < length; i++) {
			int x = i / 5;
			int y = i % 5;
			BoardPainter.checker(g, this.list.get(i), x, y);
		}
	}

	@Override
	public void revalidate() {
		int unit = Data.getUnit();
		int border = Data.getBorder();
		int size = unit - 2 * border;
		this.setPreferredSize(new Dimension(size * 3, size * 5));
		super.revalidate();
	}
}