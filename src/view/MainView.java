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
import javax.swing.border.Border;

import model.Word;
import controller.WordController;

/* MenuLookDemo.java requires images/middle.gif. */

/*
 * This class exists solely to show you what menus look like.
 * It has no menu-related event handling.
 */
@SuppressWarnings("serial")
public class MainView extends JFrame implements KeyListener, ActionListener {
	//JTextArea output;
	//JScrollPane scrollPane;
	JPanel contentPane = new JPanel(new BorderLayout());
	
	String wordPath = "./wordlist/word.txt";
	String recordPath = "./record/record.dat";

	//JMenuItem chooseItem = new JMenuItem("选择词库");
	//JMenuItem startwordItem = new JMenuItem("从头开始背！");
	//JMenuItem continueItem = new JMenuItem("从上次停止的地方继续！");
	//JMenuItem selectItem = new JMenuItem("自己选择从哪里开始背！");
	JMenuItem recordItem = new JMenuItem("查看词库统计信息");
	//JMenuItem allrecordItem = new JMenuItem("查看历史背单词记录");
	JMenuItem helpItem = new JMenuItem("帮助");
	JMenuItem AboutItem = new JMenuItem("Lab作者信息");

	//mainview2
    private javax.swing.JButton FileChooser=new JButton();
    private javax.swing.JButton stats=new JButton();
    private javax.swing.JTextField Number=new JTextField();
    private javax.swing.JButton begin=new JButton();
    private javax.swing.JLabel jLabel1=new JLabel();
    private javax.swing.JLabel jLabel2=new JLabel();
    private javax.swing.JLabel jLabel3=new JLabel();
    private javax.swing.JLabel jLabel4=new JLabel();
    private javax.swing.JPanel jPanel2=new JPanel();
    private javax.swing.JPanel logo=new JPanel();
    private javax.swing.JComboBox type=new JComboBox();
    private javax.swing.JComboBox range=new JComboBox();
    private javax.swing.JLabel filename=new JLabel();
	//
	
    // Variables declaration - do not modify
    private JTextField Chi=new JTextField();
    private JLabel ChiLabel=new JLabel();
    private JLabel logoIcon = new JLabel();
    private JTextField Eng=new JTextField();
    private JLabel EngLabel=new JLabel();;
    private JButton NOTOK=new JButton();
    private JButton OK=new JButton();
    private JButton ReturnButton=new JButton();
    // End of variables declaration

    JFrame selectwordframe=new JFrame();
    JComboBox cmb = new JComboBox();
	JTextField inputfield=(JTextField)cmb.getEditor().getEditorComponent();
    JButton selectworddone=new JButton("确定");
	
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
		//chooseItem.setMnemonic(KeyEvent.VK_D);
		//chooseItem.addActionListener(this);
		//menu.add(chooseItem);
		/*
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
		*/
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

		/*
		if (wordController == null) {
			startwordItem.setEnabled(false);
			continueItem.setEnabled(false);
			selectItem.setEnabled(false);
			recordItem.setEnabled(false);
		}*/

		return menuBar;
	}
	
	public void addlistener(){
		inputfield.addKeyListener(this);
		selectworddone.addActionListener(this);
        OK.addActionListener(this);
        NOTOK.addActionListener(this);
        ReturnButton.addActionListener(this);
        FileChooser.addActionListener(this);
        begin.addActionListener(this);
        type.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                int index = type.getSelectedIndex();
                if (index == 2) { // ==0表示选中的是第一个
                	selectWord();
                }
              }
        });
	}
	
	// TODO 每个按键对应的动作
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == FileChooser) {
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
		} else if(e.getSource() == begin){
			//start=0;
			wordnum=0;
			rightnum=0;
			wrongnum=0;
			int index=type.getSelectedIndex();
			if(index == 0){
				start=0;
			} else if (index == 1) {
				start=wordController.getLastto()+1;
				if(start==wordController.getAllCount())
					start=0;//上次背到最后，重新开始背
				//afterselectword();
			} //else if (index == 2) {//自己选从哪里背
				// TODO
				//selectWord();
			//}
			String input = Number.getText();
			if (input.equals("")) {
				JOptionPane.showMessageDialog(this, "您忘记背诵单词数了吧。。。", "出错啦",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			else{
				int num=0;
				try{
					num = Integer.parseInt(input);
				} catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(this, "亲，请输入数字，不可过长（如几十亿。。。）", "出错啦",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				wordnum=num;
				afterselectword();
			}
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
		} else if(e.getSource() == ReturnButton){
			contentPane.removeAll();
			createContentPane();
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
			//afterselectword();
		} /*else{
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
		}*/
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
			FileChooser.setText("重新选择词库");
			begin.setEnabled(true);
			type.setEnabled(true);
			Number.setEnabled(true);
			//startwordItem.setEnabled(true);
			//continueItem.setEnabled(true);
			//selectItem.setEnabled(true);
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
		selectwordframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		selectwordframe.getContentPane().setLayout(new GridLayout(1,2));
		selectwordframe.getContentPane().add(cmb);
		selectwordframe.getContentPane().add(selectworddone);
		selectwordframe.setSize(400,80);
		selectwordframe.setVisible(true);
	}
	
	/*
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
	}*/
	
	public void afterselectword(){
		int valid=wordController.isValid(start, wordnum);
		if (valid==-1) {
			JOptionPane.showMessageDialog(this, "您输入的单词数太坑爹，臣妾办不到啊。。。", "出错啦",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		else if(valid>0){
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
        //Eng.setText("单词输入");
  
        OK.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        OK.setText("我非常确定^-^");
        OK.setActionCommand("d");

        NOTOK.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        NOTOK.setText("我不记得了T-T");

        ChiLabel.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        ChiLabel.setText("中文释义");

        EngLabel.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        EngLabel.setText("单词输入");
        
        ReturnButton.setText("返回主界面");
        
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
                            .addComponent(OK, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(42, 42, 42)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ReturnButton, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(NOTOK, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                    .addGap(52, 52, 52)
                    .addComponent(ReturnButton)
                    .addContainerGap())
            );
		//for (int tmp = startint; tmp < startint + duration; tmp++) {
		currindex=startint;
		Word word = wordController.getWordByID(currindex);
		Chi.setText(word.getMeaning());
		//}
	}

	// 停止背单词，记录当前位置，进行保存
	public static void saveView() {
		if(wordController==null)
			return;
		if (wordController.mergerecord()&&wordController.saveRecord()){
				System.out.println("保存成功！");
		} else {
			System.out.println("出错了！");
		}
	}

	public void createContentPane() {
		// Create the content-pane-to-be.
		//JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.setOpaque(true);
		
		if (wordController == null) {
			FileChooser.setText("选择词库");
			begin.setEnabled(false);
			type.setEnabled(false);
			Number.setEnabled(false);
		}
		else{
			FileChooser.setText("重新选择词库");
		}
		
		contentPane.setBackground(Color.white);
		
		
		javax.swing.GroupLayout logoLayout = new javax.swing.GroupLayout(logo);
        logo.setLayout(logoLayout);
        logo.setBackground(Color.white);

        ImageIcon logoPNG = new ImageIcon("./images/logo.png");
        logoIcon.setIcon(logoPNG);
        
        logoLayout.setHorizontalGroup(
            logoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoIcon)
            .addGap(0, 177, Short.MAX_VALUE)
        );
        logoLayout.setVerticalGroup(
            logoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoIcon)
            .addGap(0, 89, Short.MAX_VALUE)
        );

        ImageIcon startButton = new ImageIcon("./images/start_button2.png");
        begin.setIcon(startButton);
        begin.setDisabledIcon(startButton);
        begin.setBorder(null);
        
        ImageIcon statsButton = new ImageIcon("./images/data.png");
        stats.setIcon(statsButton);
        stats.setDisabledIcon(statsButton);
        stats.setBorder(null);

        //stats.setBorder(null);
        //stats.setIcon(new ImageIcon("./images/data.png"));
        //stats.setDisabledIcon(new ImageIcon("./images/data.png"));

        type.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "重新开始", "上次结束单词开始", "选定单词开始" }));

        Number.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        Number.setForeground(new java.awt.Color(102, 102, 102));
        Number.setText("number");

        jLabel2.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel2.setText("路径");

        jLabel1.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel1.setText("方式");

        FileChooser.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        FileChooser.setText("filechooser");

        jLabel3.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel3.setText("单词数");


        jLabel4.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel4.setText("词库");

        range.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        range.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" }));

        filename.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        filename.setText("filename");
        
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2.setBackground(Color.white);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(96, 96, 96)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(begin, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(31, 31, 31)
                            .addComponent(stats, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel4)
                                .addComponent(jLabel2))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(type, 0, 244, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(FileChooser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(range, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                                            .addComponent(jLabel3)))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(filename)
                                        .addComponent(Number, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGap(0, 0, 0))
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(FileChooser)
                        .addComponent(jLabel2)
                        .addComponent(filename))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(range, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(Number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(begin, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(stats, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)))
            );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(contentPane);
        contentPane.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
		// Create a scrolled text area.
		//output = new JTextArea(5, 5);
		//output.setEditable(false);
		//output.setText("此处为TextArea");
		//output.setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
		//scrollPane = new JScrollPane(output);
		//scrollPane.add();
		// Add the text area to the content pane.
		//contentPane.add(scrollPane, BorderLayout.CENTER);

		//return contentPane;
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
						"确定要退出系统吗？", "退出",
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
		demo.addlistener();
		frame.setJMenuBar(demo.createMenuBar());
		demo.createContentPane();
		frame.setContentPane(demo.contentPane);
		// 使窗口居中
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width / 2; // 获取屏幕的宽
		int screenHeight = screenSize.height / 2; // 获取屏幕的高
		int height = 420;
		int width = 400;
		frame.setLocation(screenWidth - width / 2, screenHeight - height / 2);
		// Display the window.
		frame.setSize(width, height);
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
			if(cmb.getItemCount()>1&&cmb.getItemAt(0).toString().equals(cmb.getItemAt(1).toString()))
				cmb.removeItemAt(0);
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
