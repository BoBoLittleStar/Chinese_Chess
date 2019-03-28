package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import data.Data;

public class OptionPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final JButton[] options;

	public OptionPanel(String[] options) {
		this.setBackground(Color.white);
		JPanel layer = new JPanel();
		layer.setBackground(Color.white);
		layer.setLayout(new GridLayout(options.length, 1));
		this.options = new JButton[options.length];
		for (int i = 0; i < options.length; i++) {
			this.options[i] = new JButton(options[i]);
			this.options[i].setFont(Data.getFont());
			layer.add(this.options[i]);
		}
		this.add(layer);
	}

	public void addActionListener(ActionListener listener) {
		for (JButton option : this.options)
			option.addActionListener(listener);
	}

	@Override
	public void revalidate() {
		if (this.options != null)
			for (JButton option : this.options)
				option.setFont(Data.getFont());
		super.revalidate();
	}
}