/*
 * Author: Feng Chaoyi
 * SE　lab4
 */

package view;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;

import model.Record;
import model.Word;
import controller.RecordController;
import controller.WordController;

/* MenuLookDemo.java requires images/middle.gif. */

/*
 * This class exists solely to show you what menus look like.
 * It has no menu-related event handling.
 */
@SuppressWarnings("serial")
public class MainView extends JFrame implements KeyListener, ActionListener {
	JTextArea output;
	JScrollPane scrollPane;

	String wordPath = "./wordlist/word.txt";
	String recordPath = "./record/record.dat";

	JMenuItem chooseItem = new JMenuItem("选择词库");
	JMenuItem startwordItem = new JMenuItem("从头开始背！");
	JMenuItem continueItem = new JMenuItem("从上次停止的地方继续！");
	JMenuItem listrecordItem = new JMenuItem("查看词库统计信息");
	JMenuItem allrecordItem = new JMenuItem("查看历史背单词记录");
	JMenuItem helpItem = new JMenuItem("帮助");
	JMenuItem AboutItem = new JMenuItem("Lab作者信息");

	WordController wordController = null;

	public JMenuBar createMenuBar() {
		JMenuBar menuBar;
		JMenu menu, submenu;

		// Create the menu bar.
		menuBar = new JMenuBar();

		// Build the first menu.
		menu = new JMenu("开始(B)");
		menu.setMnemonic('B');
		menu.getAccessibleContext().setAccessibleDescription("System menu");
		menuBar.add(menu);

		// 开始菜单项
		// ImageIcon icon = createImageIcon("/images/start.jpg");
		chooseItem.setMnemonic(KeyEvent.VK_D);
		chooseItem.addActionListener(this);
		menu.add(chooseItem);

		startwordItem.setMnemonic(KeyEvent.VK_D);
		startwordItem.addActionListener(this);
		menu.add(startwordItem);

		continueItem.setMnemonic(KeyEvent.VK_D);
		continueItem.addActionListener(this);
		menu.add(continueItem);
		/*
		 * JMenuItem wordnumberItem = new JMenuItem("选择要背的单词个数");
		 * wordnumberItem.setMnemonic(KeyEvent.VK_D);
		 * wordnumberItem.addActionListener(this); menu.add(wordnumberItem);
		 */
		menu.addSeparator();

		listrecordItem.setMnemonic(KeyEvent.VK_D);
		listrecordItem.addActionListener(this);
		menu.add(listrecordItem);

		allrecordItem.setMnemonic(KeyEvent.VK_D);
		allrecordItem.addActionListener(this);
		menu.add(allrecordItem);

		// Build second menu in the menu bar.
		menu = new JMenu("更多(M)");
		menu.setMnemonic('M');
		menu.getAccessibleContext().setAccessibleDescription("Menu More");
		menuBar.add(menu);

		helpItem.addActionListener(this);
		menu.add(helpItem);

		AboutItem.addActionListener(this);
		menu.add(AboutItem);

		if (wordController == null) {
			startwordItem.setEnabled(false);
			continueItem.setEnabled(false);
			listrecordItem.setEnabled(false);
		}

		return menuBar;
	}

	// TODO 每个按键对应的动作
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == chooseItem) {
			chooseFile();
		} else if (e.getSource() == startwordItem) {
			setStartWord();
		} else if (e.getSource() == allrecordItem) {
			new RecordView();
		} else if (e.getSource() == AboutItem) {
			JOptionPane.showMessageDialog(this,
					"SE Lab4 \n Author: fengshao,chenlu,huijie", "About",
					JOptionPane.DEFAULT_OPTION);
		}
	}

	public void chooseFile() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("./wordlist"));
		chooser.setDialogTitle("选择词库");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			wordPath = chooser.getSelectedFile().getPath();
			wordController = new WordController(wordPath);
			chooseItem.setText("重新选择词库");
			startwordItem.setEnabled(true);
			continueItem.setEnabled(true);
			listrecordItem.setEnabled(true);
			// TODO
		} else {
			return;
		}
	}

	//似乎这一段我理解错意思了，应该不用做从中间开始背这个功能，只用做重头开始和从上次停止处开始两个即可。代码还是有用的，留给你看看
	public void setStartWord() {
		String input1 = "0", input2 = "100";
		if (wordController == null) {
			JOptionPane.showMessageDialog(this, "请先选择词库！", "hahahaaaa",
					JOptionPane.WARNING_MESSAGE);
		} else {
			int allcount = wordController.getAllCount();
			input1 = JOptionPane.showInputDialog("（好像这个地方我理解错了，你看看代码注释）您想从第几个单词开始?现在总共" + allcount
					+ "个单词，过大或者过小会报错的：");
			input2 = JOptionPane.showInputDialog("您今天想背多少个单词?");
			if (input1 == null || input2 == null) {
				JOptionPane.showMessageDialog(this, "您忘记输入了吧。。。", "出错啦",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (!wordController.isValid(input1, input2)) {
				JOptionPane.showMessageDialog(this, "您的输入太坑爹，臣妾办不到啊。。。", "出错啦",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		// 设置起止单词
		int startint = Integer.parseInt(input1);
		int duration = Integer.parseInt(input2);
		recitationview(startint, duration);
	}

	// 背单词过程 ////////////////////// 这里是界面上的背单词过程
	// controller控制实际上的背单词过程，实现包括保存记录等功能
	public void recitationview(int startint, int duration) {
		wordController.startReciting(startint, duration);
		for (int tmp = startint; tmp < startint + duration; tmp++) {
			Word word = wordController.getWordByID(tmp);
			// TODO 绘制界面

		}
	}

	// 停止背单词，记录当前位置，进行保存
	public void saveView(int reciteTo) {
		if (wordController.finishReciting(reciteTo)) {
			System.out.println("保存成功！");
		} else {
			System.out.println("出错了！");
		}
	}

	public Container createContentPane() {
		// Create the content-pane-to-be.
		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.setOpaque(true);

		// Create a scrolled text area.
		output = new JTextArea(5, 30);
		output.setEditable(false);
		output.setText("此处为TextArea");
		scrollPane = new JScrollPane(output);

		// Add the text area to the content pane.
		contentPane.add(scrollPane, BorderLayout.CENTER);

		return contentPane;
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = MainView.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	public static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("WordMaster!");
		ImageIcon image = new ImageIcon("./images/icon.jpg");
		frame.setIconImage(image.getImage());
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int i = JOptionPane.showConfirmDialog(null,
						"确定要退出系统吗？退出的时候要做一个保存当前记录，不过我现在还没做", "退出系统",
						JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

		// Create and set up the content pane.

		// 主界面
		MainView demo = new MainView();
		frame.setJMenuBar(demo.createMenuBar());
		frame.setContentPane(demo.createContentPane());
		// 使窗口居中
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width / 2; // 获取屏幕的宽
		int screenHeight = screenSize.height / 2; // 获取屏幕的高
		int height = 650;
		int width = 650;
		frame.setLocation(screenWidth - width / 2, screenHeight - height / 2);
		// Display the window.
		frame.setSize(height, width);
		frame.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}