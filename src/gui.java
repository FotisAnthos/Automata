import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

public class gui {

	private JFrame frame;
	private final ButtonGroup InputButtonGroup = new ButtonGroup();
	private JRadioButton rdbtnDefault;
	private JRadioButton rdbtnCustomInput;
	private JButton browseButton;
	private JTextField LocTextField;
	private String InputLocation;


	public gui() {
		// Set System L&F
		try {
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		frame.setTitle("Automata - it14112");
		inputPanel();//The first panel to choose input file location
		frame.setVisible(true);
	}

	private void inputPanel() {
		File file=new File(".");
		String currDir= file.getAbsolutePath();
		try {
			currDir = file.getCanonicalPath();
		} catch (IOException e1) {
			currDir = file.getAbsolutePath();
		}
		String defaultInputFile = currDir + "\\" +  "input.txt";
		File tmpDir = new File(defaultInputFile);

		JPanel inputPanel = new JPanel();
		GridBagConstraints gbc_inputPanel = new GridBagConstraints();
		gbc_inputPanel.fill = GridBagConstraints.BOTH;
		gbc_inputPanel.gridx = 0;
		gbc_inputPanel.gridy = 0;
		frame.getContentPane().add(inputPanel, gbc_inputPanel);
		SpringLayout sl_inputPanel = new SpringLayout();
		inputPanel.setLayout(sl_inputPanel);

		JTextPane txtpnChooseDefaultTo = new JTextPane();
		txtpnChooseDefaultTo.setBackground(UIManager.getColor("ColorChooser.background"));
		txtpnChooseDefaultTo.setText("Choose Default to use an existing input file, or New for an input file of your own");
		txtpnChooseDefaultTo.setEditable(false);
		sl_inputPanel.putConstraint(SpringLayout.NORTH, txtpnChooseDefaultTo, 10, SpringLayout.NORTH, inputPanel);
		sl_inputPanel.putConstraint(SpringLayout.WEST, txtpnChooseDefaultTo, 10, SpringLayout.WEST, inputPanel);
		sl_inputPanel.putConstraint(SpringLayout.SOUTH, txtpnChooseDefaultTo, 39, SpringLayout.NORTH, inputPanel);
		sl_inputPanel.putConstraint(SpringLayout.EAST, txtpnChooseDefaultTo, 424, SpringLayout.WEST, inputPanel);
		inputPanel.add(txtpnChooseDefaultTo);

		rdbtnDefault = new JRadioButton("Default");

		InputButtonGroup.add(rdbtnDefault);
		sl_inputPanel.putConstraint(SpringLayout.NORTH, rdbtnDefault, 6, SpringLayout.SOUTH, txtpnChooseDefaultTo);
		sl_inputPanel.putConstraint(SpringLayout.WEST, rdbtnDefault, 0, SpringLayout.WEST, txtpnChooseDefaultTo);
		inputPanel.add(rdbtnDefault);

		if(tmpDir.exists()) {
			InputLocation = defaultInputFile;
			rdbtnDefault.setSelected(true);
		}
		else {
			InputLocation = currDir;
			rdbtnCustomInput.setSelected(true);
		}


		rdbtnCustomInput = new JRadioButton("Custom Input");
		rdbtnCustomInput.addItemListener(new ItemListener() {//https://stackoverflow.com/a/1424762
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					LocTextField.setEnabled(true);
					browseButton.setEnabled(true);

				}
				else if (e.getStateChange() == ItemEvent.DESELECTED) {
					LocTextField.setEnabled(false);
					browseButton.setEnabled(false);
					InputLocation = defaultInputFile;
					LocTextField.setText(InputLocation);
				}
			}
		});
		InputButtonGroup.add(rdbtnCustomInput);
		sl_inputPanel.putConstraint(SpringLayout.NORTH, rdbtnCustomInput, 6, SpringLayout.SOUTH, rdbtnDefault);
		sl_inputPanel.putConstraint(SpringLayout.WEST, rdbtnCustomInput, 0, SpringLayout.WEST, txtpnChooseDefaultTo);
		inputPanel.add(rdbtnCustomInput);

		LocTextField = new JTextField();
		sl_inputPanel.putConstraint(SpringLayout.WEST, LocTextField, 0, SpringLayout.WEST, txtpnChooseDefaultTo);
		sl_inputPanel.putConstraint(SpringLayout.SOUTH, LocTextField, 49, SpringLayout.SOUTH, rdbtnCustomInput);
		sl_inputPanel.putConstraint(SpringLayout.EAST, LocTextField, 325, SpringLayout.WEST, inputPanel);
		LocTextField.setEnabled(false);
		LocTextField.setText(defaultInputFile);
		inputPanel.add(LocTextField);
		LocTextField.setColumns(10);

		browseButton = new JButton("...");
		sl_inputPanel.putConstraint(SpringLayout.NORTH, browseButton, 0, SpringLayout.NORTH, LocTextField);
		sl_inputPanel.putConstraint(SpringLayout.WEST, browseButton, 6, SpringLayout.EAST, LocTextField);
		browseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(browseButton.isEnabled()) {
					File file = fileChoose();//TODO incomplete
					InputLocation = file.getAbsolutePath();
					LocTextField.setText(InputLocation);
				}
			}
		});
		browseButton.setEnabled(false);
		inputPanel.add(browseButton);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int choice =JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(choice == JOptionPane.YES_OPTION)
					frame.dispose();
			}
		});
		sl_inputPanel.putConstraint(SpringLayout.SOUTH, btnCancel, -10, SpringLayout.SOUTH, inputPanel);
		sl_inputPanel.putConstraint(SpringLayout.EAST, btnCancel, -10, SpringLayout.EAST, inputPanel);
		inputPanel.add(btnCancel);

		JButton btnNext = new JButton("Next");
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//TODO
				readClass r = new readClass(InputLocation);
				r.getStates();
				//get the read results and use them
			}
		});
		sl_inputPanel.putConstraint(SpringLayout.SOUTH, btnNext, 0, SpringLayout.SOUTH, btnCancel);
		sl_inputPanel.putConstraint(SpringLayout.EAST, btnNext, -6, SpringLayout.WEST, btnCancel);
		inputPanel.add(btnNext);

		JLabel lblSourceFile = new JLabel("Source File:");
		sl_inputPanel.putConstraint(SpringLayout.NORTH, LocTextField, 6, SpringLayout.SOUTH, lblSourceFile);
		sl_inputPanel.putConstraint(SpringLayout.NORTH, lblSourceFile, 6, SpringLayout.SOUTH, rdbtnCustomInput);
		sl_inputPanel.putConstraint(SpringLayout.WEST, lblSourceFile, 0, SpringLayout.WEST, txtpnChooseDefaultTo);
		inputPanel.add(lblSourceFile);
	}

	private File fileChoose() {
		JFileChooser fc = new JFileChooser();

		//File file = new File(".");
		//String currentDirectory = file.getAbsolutePath();
		File file=new File(".");
		System.out.println("Current Working Directory: " + file.getAbsolutePath());

		fc.setCurrentDirectory(file.getAbsoluteFile());
		fc.showDialog(frame, "Select");
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

		return file = fc.getSelectedFile();

	}
}
