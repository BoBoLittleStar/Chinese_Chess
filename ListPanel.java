package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import data.Data;
import model.Checker;
import model.Checker.Player;

public class ListPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Player player;
	private List<Checker> list;

	public ListPanel(Player player) {
		this.setPreferredSize(new Dimension(Data.getUnit() * 2, Data.getUnit() * 8));
		this.setBackground(Color.white);
		this.player = player;
		this.list = new ArrayList<>();
	}

	public void add(Checker checker) {
		this.list.add(checker);
	}

	public void clear() {
		this.list.clear();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(this.player == Player.RED ? Data.RED : Data.BLACK);
	}
}