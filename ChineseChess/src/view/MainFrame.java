package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

import controller.GameController;
import data.Data;
import model.Board;
import model.Checker;
import model.Checker.Player;

public class MainFrame extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private ChessPanel chessPanel;
	private ManualPanel manualPanel;
	private OptionPanel optionPanel;
	private OptionPanel zoomPanel;
	private ListPanel redListPanel;
	private ListPanel blackListPanel;
	private GameController controller;

	public MainFrame() {
		this.setVisible(false);
		this.setBackground(Color.white);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagConstraints cons = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		Board board = new Board();
		this.controller = new GameController(board, this);
		this.chessPanel = new ChessPanel(board, this.controller);
		this.manualPanel = new ManualPanel(board, this.controller);
		this.optionPanel = new OptionPanel(new String[] { Data.NEW, Data.SET });
		this.optionPanel.addActionListener(this.controller);
		this.redListPanel = new ListPanel();
		this.blackListPanel = new ListPanel();
		this.zoomPanel = new OptionPanel(new String[] { Data.ZOOMIN, Data.ZOOMOUT });
		this.zoomPanel.addActionListener(this.controller);
		cons.fill = GridBagConstraints.BOTH;
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth = 1;
		cons.gridheight = 3;
		this.add(this.zoomPanel, cons);
		cons.fill = GridBagConstraints.BOTH;
		cons.gridx = 1;
		cons.gridy = 0;
		cons.gridwidth = 1;
		cons.gridheight = 3;
		this.add(this.chessPanel, cons);
		cons.fill = GridBagConstraints.BOTH;
		cons.gridx = 2;
		cons.gridy = 0;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		this.add(this.redListPanel, cons);
		cons.fill = GridBagConstraints.BOTH;
		cons.gridx = 2;
		cons.gridy = 1;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		this.add(this.optionPanel, cons);
		cons.fill = GridBagConstraints.BOTH;
		cons.gridx = 2;
		cons.gridy = 2;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		this.add(this.blackListPanel, cons);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
	}

	public void put(Player player, Checker checker) {
		if (player == Player.RED)
			this.redListPanel.add(checker);
		else
			this.blackListPanel.add(checker);
	}

	@Override
	public void revalidate() {
		this.setVisible(false);
		this.chessPanel.revalidate();
		this.optionPanel.revalidate();
		this.zoomPanel.revalidate();
		this.redListPanel.revalidate();
		this.blackListPanel.revalidate();
		super.revalidate();
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}