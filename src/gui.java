import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;




public class gui {

	private JFrame frame;

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
		frame.setTitle("Automata - it14112");
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		frame.setResizable(false);
		frame.getContentPane().add(new inputPanel(frame));
		frame.setVisible(true);
	}

	private void inputPanel() {
		//TODO
	}
	
	class inputPanel extends JPanel {
		private JTextField locTextField;
		private final ButtonGroup buttonGroup = new ButtonGroup();
		private String InputLocation;
		private String defaultInputFile;
		private JButton browseButton;
		private JTextPane infoTextPane;
		private JRadioButton rdbtnDefault;
		private JRadioButton rdbtnCustom;
		private JButton btnNext;
		private JButton btnCancel;
		private JFrame frame;


		public inputPanel(JFrame frame) {
			this.frame = frame;
			SpringLayout springLayout = new SpringLayout();
			setLayout(springLayout);

			//Find current directory
			File file=new File(".");
			String currDir= file.getAbsolutePath();
			try {
				currDir = file.getCanonicalPath();
			} catch (IOException e1) {
				currDir = file.getAbsolutePath();
			}
			defaultInputFile = currDir + "\\" +  "input.txt";
			File tmpDir = new File(defaultInputFile);


			infoTextPane = new JTextPane();
			infoTextPane.setBackground(UIManager.getColor("ColorChooser.background"));
			infoTextPane.setText("Choose \"Default\" to use an existing input file, or \"Custom\" for an input file of your own");
			infoTextPane.setEditable(false);
			springLayout.putConstraint(SpringLayout.NORTH, infoTextPane, 10, SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, infoTextPane, 10, SpringLayout.WEST, this);
			springLayout.putConstraint(SpringLayout.SOUTH, infoTextPane, 44, SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.EAST, infoTextPane, 440, SpringLayout.WEST, this);
			add(infoTextPane);

			rdbtnDefault = new JRadioButton("Default");
			buttonGroup.add(rdbtnDefault);
			rdbtnDefault.setSelected(true);
			springLayout.putConstraint(SpringLayout.WEST, rdbtnDefault, 0, SpringLayout.WEST, infoTextPane);
			add(rdbtnDefault);

			rdbtnCustom = new JRadioButton("Custom");
			rdbtnCustom.addItemListener(new ItemListener() {//https://stackoverflow.com/a/1424762
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						locTextField.setEnabled(true);
						browseButton.setEnabled(true);
						locTextField.setEditable(true);

					}
					else if (e.getStateChange() == ItemEvent.DESELECTED) {
						locTextField.setEnabled(false);
						browseButton.setEnabled(false);
						InputLocation = defaultInputFile;
						locTextField.setText(InputLocation);
					}
				}
			});
			buttonGroup.add(rdbtnCustom);
			springLayout.putConstraint(SpringLayout.NORTH, rdbtnCustom, 124, SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.SOUTH, rdbtnDefault, -6, SpringLayout.NORTH, rdbtnCustom);
			springLayout.putConstraint(SpringLayout.WEST, rdbtnCustom, 0, SpringLayout.WEST, infoTextPane);
			add(rdbtnCustom);

			locTextField = new JTextField();
			springLayout.putConstraint(SpringLayout.NORTH, locTextField, 6, SpringLayout.SOUTH, rdbtnCustom);
			springLayout.putConstraint(SpringLayout.WEST, locTextField, 0, SpringLayout.WEST, infoTextPane);
			springLayout.putConstraint(SpringLayout.EAST, locTextField, -122, SpringLayout.EAST, this);
			locTextField.setText(defaultInputFile);
			add(locTextField);
			locTextField.setColumns(10);


			browseButton = new JButton("...");
			springLayout.putConstraint(SpringLayout.NORTH, browseButton, -1, SpringLayout.NORTH, locTextField);
			springLayout.putConstraint(SpringLayout.WEST, browseButton, 6, SpringLayout.EAST, locTextField);
			browseButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(browseButton.isEnabled()) {
						File file = fileChoose();//TODO incomplete
						InputLocation = file.getAbsolutePath();
						locTextField.setText(InputLocation);
					}
				}
			});
			browseButton.setEnabled(false);
			add(browseButton);

			btnCancel = new JButton("Cancel");
			springLayout.putConstraint(SpringLayout.SOUTH, btnCancel, -10, SpringLayout.SOUTH, this);
			springLayout.putConstraint(SpringLayout.EAST, btnCancel, -29, SpringLayout.EAST, this);
			btnCancel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int choice =JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if(choice == JOptionPane.YES_OPTION)
						frame.dispose();
				}
			});
			add(btnCancel);

			btnNext = new JButton("Next");
			springLayout.putConstraint(SpringLayout.NORTH, btnNext, 0, SpringLayout.NORTH, btnCancel);
			springLayout.putConstraint(SpringLayout.EAST, btnNext, -6, SpringLayout.WEST, btnCancel);
			btnNext.addMouseListener(new MouseAdapter() {//TODO sent to frame a signal to move on
				@Override
				public void mouseClicked(MouseEvent arg0) {
					//TODO
					readClass r = new readClass(InputLocation);
					r.getStates();
					//get the read results and use them
				}
			});
			add(btnNext);
			
			//Set the initial input location to the locTextField
			if(tmpDir.exists()) {
				InputLocation = defaultInputFile;
				rdbtnDefault.setSelected(true);
				locTextField.setEditable(false);
			}
			else {
				InputLocation = currDir;
				rdbtnCustom.setSelected(true);
				locTextField.setEditable(true);
			}

			
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
}
