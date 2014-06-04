/*
 * Author: Feng Chaoyi
 * SE　lab4
 */

package view;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.JButton;
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
import javax.swing.JTextField;

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
	JPanel contentPane = new JPanel(new BorderLayout());
	
	String wordPath = "./wordlist/word.txt";
	String recordPath = "./record/record.dat";

	JMenuItem chooseItem = new JMenuItem("选择词库");
	JMenuItem startwordItem = new JMenuItem("从头开始背！");
	JMenuItem continueItem = new JMenuItem("从上次停止的地方继续！");
	JMenuItem selectItem = new JMenuItem("自己选择从哪里开始背！");
	JMenuItem listrecordItem = new JMenuItem("查看词库统计信息");
	//JMenuItem allrecordItem = new JMenuItem("查看历史背单词记录");
	JMenuItem helpItem = new JMenuItem("帮助");
	JMenuItem AboutItem = new JMenuItem("Lab作者信息");

	JTextField wordtofill=new JTextField(1);
	JTextArea wordmeaning=new JTextArea();
	JButton ok=new JButton("确认");
	//JButton skip=new JButton("跳过");
	
	WordController wordController = null;
	int start;
	int wordnum;
	int currindex;
	
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
		
		selectItem.setMnemonic(KeyEvent.VK_D);
		selectItem.addActionListener(this);
		menu.add(selectItem);
		
		/*
		 * JMenuItem wordnumberItem = new JMenuItem("选择要背的单词个数");
		 * wordnumberItem.setMnemonic(KeyEvent.VK_D);
		 * wordnumberItem.addActionListener(this); menu.add(wordnumberItem);
		 */
		menu.addSeparator();

		listrecordItem.setMnemonic(KeyEvent.VK_D);
		listrecordItem.addActionListener(this);
		menu.add(listrecordItem);

		//allrecordItem.setMnemonic(KeyEvent.VK_D);
		//allrecordItem.addActionListener(this);
		//menu.add(allrecordItem);

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
			selectItem.setEnabled(false);
			listrecordItem.setEnabled(false);
		}

		return menuBar;
	}

	// TODO 每个按键对应的动作
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == chooseItem) {
			chooseFile();
		} else if (e.getSource() == listrecordItem) {
			// TODO
		} //else if (e.getSource() == allrecordItem) {
			//new RecordView();}
		else if (e.getSource() == helpItem) {
			// TODO
		} else if (e.getSource() == AboutItem) {
			JOptionPane.showMessageDialog(this,
					"SE Lab4 \n Author: fengshao,chenlu,huijie", "About",
					JOptionPane.DEFAULT_OPTION);
		} else if (e.getSource() == ok){
			
			if(currindex>=start+wordnum){
				JOptionPane.showMessageDialog(this,
						"您已经背完了选择的单词，如想继续背诵，请重新选择单词", "错误",
						JOptionPane.DEFAULT_OPTION);
				wordtofill.setText("");
				wordmeaning.setText("");
				return;
			}
			String input=wordtofill.getText();
			Word currword=wordController.getWordByID(currindex);
			if(currword.getWord().equals(input)){
				//JOptionPane.showMessageDialog(this,
						//"答对："+currword.getWord()+" "+currword.getMeaning(), "正确",
						//JOptionPane.DEFAULT_OPTION);
				wordController.addRecord(currindex, 1);
				currindex++;
				if(currindex>=start+wordnum){
					//给出统计信息
					//contentPane.removeAll();
					//contentPane.revalidate();
					new RecordView();
				}
				else
					wordmeaning.setText(wordController.getWordByID(currindex).getMeaning());
			}
			else{
				JOptionPane.showMessageDialog(this,
						"答错，应为："+currword.getWord()+" "+currword.getMeaning(), "错误",
						JOptionPane.DEFAULT_OPTION);
				wordController.addRecord(currindex, 0);
				currindex++;
				if(currindex>=start+wordnum){
					//给出统计信息
					//contentPane.removeAll();
					//contentPane.revalidate();
					new RecordView();
				}
				else
					wordmeaning.setText(wordController.getWordByID(currindex).getMeaning());
			}
			wordtofill.setText("");
		} //else if (e.getSource() == skip){
			
		//}
		else{
			start=0;
			if (e.getSource() == startwordItem) {
				//setStartWord();
				//int start=0;
			} else if (e.getSource() == continueItem) {
				// TODO
				start=wordController.getLastto()+1;
				if(start==wordController.getAllCount())
					start=0;//上次背到最后，重新开始背
			} else if (e.getSource() == selectItem) {
				// TODO
				start=selectWord();
			}
			wordnum=setWordNum();
			int valid=wordController.isValid(start, wordnum);
			if (valid==-1) {
				JOptionPane.showMessageDialog(this, "您的输入太坑爹，臣妾办不到啊。。。", "出错啦",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			else if(valid==0){
				JOptionPane.showMessageDialog(this, "选择成功", "成功",
						JOptionPane.WARNING_MESSAGE);
			}
			else{
				wordnum=valid;
				JOptionPane.showMessageDialog(this, "您选择的单词量达到词库末尾，自动修正为："+valid+"个单词", "提示",
						JOptionPane.WARNING_MESSAGE);
			}
			System.out.println("从第"+start+"个开始,背"+wordnum+"个");
			currindex=start;
			recitationview(start, wordnum);
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
			selectItem.setEnabled(true);
			listrecordItem.setEnabled(true);
			// TODO
		} else {
			return;
		}
	}
	
	//TODO 自动匹配和错误判断
	public int selectWord(){
		String input = JOptionPane.showInputDialog("您想从哪个单词开始背?");
		return wordController.getIDByWord(input);
	}
	public int setWordNum(){
		int num=0;
		while(true){
			String input = JOptionPane.showInputDialog("您今天想背多少个单词?");
			if (input == null) {
				JOptionPane.showMessageDialog(this, "您忘记输入了吧。。。", "出错啦",
						JOptionPane.WARNING_MESSAGE);
				continue;
			}
			try{
				num = Integer.parseInt(input);
				break;
			} catch(NumberFormatException e){
				JOptionPane.showMessageDialog(this, "亲，请输入数字，不可过长（如几十亿。。。）", "出错啦",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		return num;
	}
	// 背单词过程 ////////////////////// 这里是界面上的背单词过程
	// controller控制实际上的背单词过程，实现包括保存记录等功能
	public void recitationview(int startint, int duration) {
		//wordController.startReciting(startint, duration);
		contentPane.removeAll();
		contentPane.setLayout(new GridLayout(2,2));
		
		contentPane.add(wordtofill);
		
		contentPane.add(wordmeaning);
		
		ok.removeActionListener(this);
		ok.addActionListener(this);
		contentPane.add(ok);
		
		//JButton skip=new JButton("跳过");
		//skip.addActionListener(this);
		//contentPane.add(skip);

		//contentPane.removeAll();
		contentPane.revalidate();
		//for (int tmp = startint; tmp < startint + duration; tmp++) {
		currindex=startint;
			Word word = wordController.getWordByID(currindex);
			// TODO 绘制界面
			wordmeaning.setText(word.getMeaning());
		//}
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
		//JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.setOpaque(true);

		// Create a scrolled text area.
		output = new JTextArea(5, 5);
		output.setEditable(false);
		output.setText("此处为TextArea");
		output.setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
		scrollPane = new JScrollPane(output);
		//scrollPane.add();
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
