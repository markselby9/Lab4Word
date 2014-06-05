/*
 * Author: Feng Chaoyi
 * SE　lab4
 */

package view;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
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

import model.Word;
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
	JMenuItem recordItem = new JMenuItem("查看词库统计信息");
	//JMenuItem allrecordItem = new JMenuItem("查看历史背单词记录");
	JMenuItem helpItem = new JMenuItem("帮助");
	JMenuItem AboutItem = new JMenuItem("Lab作者信息");

    // Variables declaration - do not modify
    private javax.swing.JTextField Chi=new JTextField();
    private javax.swing.JLabel ChiLabel=new JLabel();
    private javax.swing.JTextField Eng=new JTextField();
    private javax.swing.JLabel EngLabel=new JLabel();;
    private javax.swing.JButton NOTOK=new JButton();
    private javax.swing.JButton OK=new JButton();
    // End of variables declaration

    JFrame selectwordframe=new JFrame();
    JComboBox cmb = new JComboBox();
	JTextField inputfield=(JTextField)cmb.getEditor().getEditorComponent();
    JButton selectworddone=new JButton("确定");
	//JTextField wordtofill=new JTextField(1);
	//JTextArea wordmeaning=new JTextArea();
	//JButton ok=new JButton("确认");
	//JButton skip=new JButton("跳过");
	
	static WordController wordController = null;
	int start;
	int wordnum;
	int rightnum;
	int wrongnum;
	int currindex;
	
	public JMenuBar createMenuBar() {
		JMenuBar menuBar;
		JMenu menu;

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
		
		menu.addSeparator();

		recordItem.setMnemonic(KeyEvent.VK_D);
		recordItem.addActionListener(this);
		menu.add(recordItem);

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
			recordItem.setEnabled(false);
		}

		return menuBar;
	}

	// TODO 每个按键对应的动作
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == chooseItem) {
			chooseFile();
		} else if (e.getSource() == recordItem) {
			// TODO
			int recordcount=wordController.getRecordCount();
			int recordrightcount=wordController.getRecordRightCount();
			new RecordView(wordController.getListName(),wordController.getAllCount(),recordcount,recordrightcount,recordcount-recordrightcount);
		} else if (e.getSource() == helpItem) {
			// TODO
		} else if (e.getSource() == AboutItem) {
			JOptionPane.showMessageDialog(this,
					"SE Lab4 \n Author: fengshao,chenlu,huijie", "About",
					JOptionPane.DEFAULT_OPTION);
		} else if (e.getSource() == OK || e.getSource() == NOTOK){
			if(currindex>=start+wordnum){
				JOptionPane.showMessageDialog(this,
						"您已经背完了选择的单词，如想继续背诵，请重新选择单词", "错误",
						JOptionPane.DEFAULT_OPTION);
				Eng.setText("");
				Chi.setText("");
				return;
			}
			Word currword=wordController.getWordByID(currindex);
			if (e.getSource() == OK){
				String input=Eng.getText();
				if(currword.getWord().equals(input)){
					wordController.addRecord(currindex, 1);
					rightnum++;
				}
				else{
					JOptionPane.showMessageDialog(this,
							"答错，应为："+currword.getWord()+" "+currword.getMeaning(), "错误",
							JOptionPane.DEFAULT_OPTION);
					wordController.addRecord(currindex, 0);
					wrongnum++;
				}
			}
			else{
				JOptionPane.showMessageDialog(this,
						"答案是："+currword.getWord()+" "+currword.getMeaning(), "提醒",
						JOptionPane.DEFAULT_OPTION);
				wordController.addRecord(currindex, 0);
				wrongnum++;
			}
			currindex++;
			if(currindex>=start+wordnum){
				//给出统计信息
				wordController.mergerecord();
				new RecordView(wordController.getListName(),wordnum,wordnum,rightnum,wrongnum);
			}
			else
				Chi.setText(wordController.getWordByID(currindex).getMeaning());
			Eng.setText("");
		} else if(e.getSource() == selectworddone){
			int tmp=wordController.getIDByWord(inputfield.getText());
			if(tmp==-1){
				JOptionPane.showMessageDialog(this,"词库中没有您输入的单词，将默认从第一个单词开始", "出错啦",
						JOptionPane.WARNING_MESSAGE);
				start=0;
			}
			else
				start=tmp;
			selectwordframe.dispose();
			afterselectword();
		} else{
			start=0;
			wordnum=0;
			rightnum=0;
			wrongnum=0;
			if (e.getSource() == startwordItem) {
				afterselectword();
			} else if (e.getSource() == continueItem) {
				// TODO
				start=wordController.getLastto()+1;
				if(start==wordController.getAllCount())
					start=0;//上次背到最后，重新开始背
				afterselectword();
			} else if (e.getSource() == selectItem) {
				// TODO
				selectWord();
			}
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
			recordItem.setEnabled(true);
			// TODO
		} else {
			return;
		}
	}
	
	//TODO 自动匹配和错误判断
	public void selectWord(){
		cmb.setEditable(true);
		   	//cmb.setPopupVisible(true);
		inputfield.removeKeyListener(this);
		inputfield.addKeyListener(this);
		selectworddone.removeKeyListener(this);
		selectworddone.addActionListener(this);
		selectwordframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		selectwordframe.getContentPane().setLayout(new GridLayout(1,2));
		selectwordframe.getContentPane().add(cmb);
		selectwordframe.getContentPane().add(selectworddone);
		selectwordframe.setSize(400,80);
		selectwordframe.setVisible(true);
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
	
	public void afterselectword(){
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
	// 背单词过程 ////////////////////// 这里是界面上的背单词过程
	// controller控制实际上的背单词过程，实现包括保存记录等功能
	public void recitationview(int startint, int duration) {
		//wordController.startReciting(startint, duration);
		contentPane.removeAll();
		/*contentPane.setLayout(new GridLayout(2,2));
		contentPane.add(wordtofill);
		contentPane.add(wordmeaning);
		ok.removeActionListener(this);
		ok.addActionListener(this);
		contentPane.add(ok);
		//contentPane.removeAll();
		contentPane.revalidate();
		*/

        Chi.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        Chi.setText("中文释义");
        Chi.setEditable(false);

        Eng.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        Eng.setText("单词输入");
  
        OK.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        OK.setText("我非常确定^-^");
        OK.setActionCommand("d");
        OK.removeActionListener(this);
        OK.addActionListener(this);

        NOTOK.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        NOTOK.setText("我不记得了T-T");
        NOTOK.removeActionListener(this);
        NOTOK.addActionListener(this);

        ChiLabel.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        ChiLabel.setText("中文释义");

        EngLabel.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        EngLabel.setText("单词输入");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(contentPane);
        contentPane.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(EngLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ChiLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Eng, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                    .addComponent(Chi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(OK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(NOTOK)
                        .addGap(12, 12, 12)))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(ChiLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Chi, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(EngLabel)
                .addGap(3, 3, 3)
                .addComponent(Eng, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NOTOK)
                    .addComponent(OK))
                .addGap(18, 18, 18))
        );
		//for (int tmp = startint; tmp < startint + duration; tmp++) {
		currindex=startint;
		Word word = wordController.getWordByID(currindex);
		Chi.setText(word.getMeaning());
		//}
	}

	// 停止背单词，记录当前位置，进行保存
	public static void saveView() {
		if (wordController.mergerecord()&&wordController.saveRecord()){
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
		//output.setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
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
					saveView();
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
		if(e.getSource()==inputfield){
			int caretPosition = inputfield.getCaretPosition();
			String input=inputfield.getText();
			ArrayList<String> items=wordController.getSimilarWords(input);
			cmb.setMaximumRowCount(11);
			cmb.hidePopup();
			cmb.removeAllItems();
			cmb.addItem(input);
			for(int i=0;i<items.size();i++){
				cmb.addItem(items.get(i));
			}
			cmb.showPopup();
			inputfield.setCaretPosition(caretPosition);
		}
	}

	class comboboxtest extends JFrame {

		JComboBox cmb = new JComboBox();
		JTextField inputfield=(JTextField)cmb.getEditor().getEditorComponent();
		
		public comboboxtest(){
		   	cmb.setEditable(true);
		   	//cmb.setPopupVisible(true);
		   	inputfield.addKeyListener(new autocompleter());
		   	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   	this.getContentPane().setLayout(new GridLayout(1,2));
		    this.getContentPane().add(cmb);
		    this.getContentPane().add(selectworddone);
		    this.setSize(400,80);
		    this.setVisible(true);
		}
		
		
		class autocompleter implements KeyListener {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				ArrayList<String> items=wordController.getSimilarWords(inputfield.getText());
				cmb.removeAllItems();
				for(int i=0;i<items.size();i++){
					cmb.addItem(items.get(i));
				}
				cmb.showPopup();
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		}
	}
}
