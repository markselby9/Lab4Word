/*
 * Author: Feng Chaoyi
 * SE　lab4
 */

package view;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

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
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.Lexicon;
import model.Timer;
import model.Word;
import controller.WordController;

/* MenuLookDemo.java requires images/middle.gif. */

/*
 * This class exists solely to show you what menus look like.
 * It has no menu-related event handling.
 */
@SuppressWarnings("serial")
public class MainView extends JFrame implements KeyListener, ActionListener {
	JPanel contentPane = new JPanel(new BorderLayout());
	
	String wordPath = "./wordlist/word.txt";
	String recordPath = "./record/record.dat";

	JMenuItem recordItem = new JMenuItem("查看词库统计信息");
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
    private javax.swing.JTextField clock=new javax.swing.JTextField();
    Timer timer=new Timer(clock);
    private JPanel jPanel1=new JPanel();
    private JButton delete=new JButton();
    private JScrollPane jScrollPane1=new JScrollPane();
	
    // Variables declaration - do not modify
    private JTextField Chi=new JTextField();
    private JLabel ChiLabel=new JLabel();
    private JLabel logoIcon = new JLabel();
    private JTextField Eng=new JTextField();
    private JLabel EngLabel=new JLabel();
    private JButton NOTOK=new JButton();
    private JButton OK=new JButton();
    private JButton ReturnButton=new JButton();
    // End of variables declaration

    //select word frame
    JFrame selectwordframe=new JFrame();
    JComboBox cmb = new JComboBox();
	JTextField inputfield=(JTextField)cmb.getEditor().getEditorComponent();
    JButton selectworddone=new JButton("确定");
   	JLabel inputLabel=new JLabel();
	//
    
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

		// Build second menu in the menu bar.
		menu = new JMenu("更多(M)");
		menu.setMnemonic('M');
		menu.getAccessibleContext().setAccessibleDescription("Menu More");
		menuBar.add(menu);

		helpItem.addActionListener(this);
		menu.add(helpItem);

		AboutItem.addActionListener(this);
		menu.add(AboutItem);

		return menuBar;
	}
	
	public void addlistener(){
		inputfield.addKeyListener(this);
		selectworddone.addActionListener(this);
        OK.addActionListener(this);
        NOTOK.addActionListener(this);
        ReturnButton.addActionListener(this);
        FileChooser.addActionListener(this);
        stats.addActionListener(this);
        begin.addActionListener(this);
        delete.addActionListener(this);
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
		} else if (e.getSource() == helpItem) {
			JOptionPane.showMessageDialog(this,
					"使用方法：选择词汇文件后，选择词库，背诵方式和需要背诵的单词数。\n背完指定单词后，会显示统计信息。\n更详细的统计信息请点击主界面的“统计”按钮。\n如需更多帮助，请联系：markselbyfcy@163.com", "帮助",
					JOptionPane.DEFAULT_OPTION);
		} else if(e.getSource()==stats){
			//int pre_total,int pre_already,int pre_correct,int all_total,int all_already,int all_correct
    		ArrayList<Lexicon> lexiconstats=wordController.getLexiconStats();
    		char start=range.getSelectedItem().toString().toUpperCase().charAt(0);
			Lexicon pre=lexiconstats.get(start-65);
			Data_all.createAndShowGUI(pre.getLexiconName(),pre.getTotalNum(), pre.getAlreadyNum(), pre.getAlreadyNum()-pre.getWrongNum(), wordController.getAllCount(), wordController.getRecordCount(), wordController.getRecordRightCount(), lexiconstats);
		} else if (e.getSource() == AboutItem) {
			JOptionPane.showMessageDialog(this,
					"WordMater!\n作者: 沈慧捷  陈璐  冯超逸", "关于",
					JOptionPane.DEFAULT_OPTION);
		} else if(e.getSource() == delete){
			Eng.setText("");
			int count = jPanel1.getComponentCount();
			for (int i = 0; i < count; i++) {
				Component comp = jPanel1.getComponent(i);
				if(comp instanceof JButton){
					JButton btn = (JButton)comp;
	                btn.setEnabled(true);
				}
			}
		} else if(e.getSource() == begin){
			//start=0;
			wordnum=0;
			rightnum=0;
			wrongnum=0;
			int index=type.getSelectedIndex();
			if(index == 0){
				start=wordController.getStartInLexicon(range.getSelectedItem().toString());
				if(start==-1){
					JOptionPane.showMessageDialog(this, "您选择的文件中没有该词库", "出错啦",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				//start=0;
			} else if (index == 1) {
				start=wordController.getLasttoInLexicon(range.getSelectedItem().toString());
				if(start==-1)
					start=wordController.getStartInLexicon(range.getSelectedItem().toString());
				if(start==wordController.getLastInLexicon(range.getSelectedItem().toString())){
					start=wordController.getStartInLexicon(range.getSelectedItem().toString());//上次背到最后，重新开始背
					if(start==-1){
						JOptionPane.showMessageDialog(this, "您选择的文件中没有该词库", "出错啦",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				else{
					start++;//从没背过该词库，从头开始
				}
			} 
			String input = Number.getText();
			if (input.equals("")) {
				JOptionPane.showMessageDialog(this, "您忘记输入背诵单词数了吧。。。", "出错啦",
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
				jPanel1.removeAll();
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
				//wordController.mergerecord();
				Data_small.createAndShowGUI(range.getSelectedItem().toString().toUpperCase(), wordController.getTotalInLexicon(range.getSelectedItem().toString().toUpperCase()), wordnum, rightnum, timer.gethms());
				Eng.setText("");
				Chi.setText("");
				jPanel1.removeAll();
			}
			else{
				Word nextword=wordController.getWordByID(currindex);
				Chi.setText(nextword.getMeaning());
				EngLabel.setText("点击按钮输入单词 ("+nextword.getWord().length()+"个字母)");
				jPanel1.removeAll();
				GridLayout buttonlayout=new GridLayout(3,6,5,5);
		        jPanel1.setLayout(buttonlayout);
				ArrayList<Character> list = nextword.alphaToChoose();
				Collections.shuffle(list);
				final int nextwordlength=nextword.getWord().length();
		        for(int i=0;i<list.size();i++){
		        	JButton letterbutton=new JButton(list.get(i)+"");
		        	letterbutton.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							if(Eng.getText().length()>=nextwordlength){
								JOptionPane.showMessageDialog(contentPane,"亲输入的单词长度已达正确答案长度，无法继续输入", "提醒",JOptionPane.DEFAULT_OPTION);
							}
							else{
								Eng.setText(Eng.getText()+((JButton)arg0.getSource()).getText());
								((JButton)arg0.getSource()).setEnabled(false);
							}
						}});
		        	jPanel1.add(letterbutton);
		        }
		        jPanel1.revalidate();
		        jPanel1.repaint();
			}
			Eng.setText("");
		} else if(e.getSource() == ReturnButton){
			timer.stop();
			contentPane.removeAll();
			createContentPane();
		} else if(e.getSource() == selectworddone){
			if(inputfield.getText().length()==0||inputfield.getText().toUpperCase().charAt(0)!=range.getSelectedItem().toString().toUpperCase().charAt(0)){
				JOptionPane.showMessageDialog(this,"词库中没有您输入的单词，将默认从第一个单词开始", "出错啦",
						JOptionPane.WARNING_MESSAGE);
				start=wordController.getStartInLexicon(range.getSelectedItem().toString());
			}
			else{
				int tmp=wordController.getIDByWord(inputfield.getText());
				if(tmp==-1){
					JOptionPane.showMessageDialog(this,"词库中没有您输入的单词，将默认从第一个单词开始", "出错啦",
							JOptionPane.WARNING_MESSAGE);
					start=wordController.getStartInLexicon(range.getSelectedItem().toString());
				}
				else
					start=tmp;
			}
			selectwordframe.dispose();
			//afterselectword();
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
			FileChooser.setText("重新选择词库");
			filename.setText(wordController.getListName());
			begin.setEnabled(true);
			stats.setEnabled(true);
			type.setEnabled(true);
			Number.setEnabled(true);
			recordItem.setEnabled(true);
		} else {
			return;
		}
	}
	
	public void selectWord(){
		cmb.setEditable(true);
		   	//cmb.setPopupVisible(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        inputLabel.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        inputLabel.setText("输入单词");

        selectworddone.setText("确认");
        
        selectwordframe.setTitle("从指定单词开始");
        selectwordframe.setResizable(false);
        selectwordframe.setSize(380,80);
        int w = (Toolkit.getDefaultToolkit().getScreenSize().width - selectwordframe.getWidth()) / 2;
        int h = (Toolkit.getDefaultToolkit().getScreenSize().height - selectwordframe.getHeight()) / 2;
        selectwordframe.setLocation(w, h);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(selectwordframe.getContentPane());
        selectwordframe.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(inputLabel)
                .addGap(39, 39, 39)
                .addComponent(cmb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(selectworddone)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputLabel)
                    .addComponent(selectworddone)
                    .addComponent(cmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        pack();
        selectwordframe.setVisible(true);
	}
	
	public void afterselectword(){
		int valid=wordController.isValid(start, wordnum, range.getSelectedItem().toString());
		if (valid==-1) {
			JOptionPane.showMessageDialog(this, "您输入的单词数太坑爹，臣妾办不到啊。。。", "出错啦",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		else if(valid==-2){
			JOptionPane.showMessageDialog(this, "您输入的单词不在词库内，臣妾办不到啊。。。", "出错啦",
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
		recitationview(start);
	}
	
	// 背单词过程 ////////////////////// 这里是界面上的背单词过程
	// controller控制实际上的背单词过程，实现包括保存记录等功能
	public void recitationview(int startint) {
		//wordController.startReciting(startint, duration);
		contentPane.removeAll();

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
        
        Eng.setEditable(false);
        
        ReturnButton.setText("返回主界面");
        
        delete.setText("删除");
        
		currindex=startint;
		Word word = wordController.getWordByID(currindex);
		Chi.setText(word.getMeaning());
		
		EngLabel.setText("点击按钮输入单词 ("+word.getWord().length()+"个字母)");
		
        ArrayList<Character> list = word.alphaToChoose();
        
        //TODO 计时器，把print出来的东西放到label上
        timer.init();
        Thread t = new Thread(timer);
        t.start();
        //TODO 需要加入计时器的timerlabel和timertitle到Layout中，逻辑已经写好
        JLabel clockLabel = new JLabel("已用时：");
        
        clock.setEditable(false);
        
        jScrollPane1.setViewportView(jPanel1);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        
        GridLayout buttonlayout=new GridLayout(3,6,5,5);
        jPanel1.setLayout(buttonlayout);
        Collections.shuffle(list);
        final int currwordlength=word.getWord().length();
        for(int i=0;i<list.size();i++){
        	JButton letterbutton=new JButton(list.get(i)+"");
        	letterbutton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if(Eng.getText().length()>=currwordlength){
						JOptionPane.showMessageDialog(contentPane,"亲输入的单词长度已达正确答案长度，无法继续输入", "提醒",JOptionPane.DEFAULT_OPTION);
					}
					else{
						Eng.setText(Eng.getText()+((JButton)arg0.getSource()).getText());
						((JButton)arg0.getSource()).setEnabled(false);
					}
				}});
        	jPanel1.add(letterbutton);
        }
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(contentPane);
        contentPane.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(117, Short.MAX_VALUE)
                    .addComponent(ReturnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(109, 109, 109))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(64, 64, 64)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                        .addComponent(EngLabel, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ChiLabel, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Chi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(OK, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                            .addComponent(NOTOK, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(Eng, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(delete, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)))
                    .addGap(36, 36, 36))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(254, Short.MAX_VALUE)
                    .addComponent(clockLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(clock, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(clockLabel)
                        .addComponent(clock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(19, 19, 19)
                    .addComponent(ChiLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(Chi, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(EngLabel)
                    .addGap(3, 3, 3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Eng, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(OK)
                        .addComponent(NOTOK))
                    .addGap(18, 18, 18)
                    .addComponent(ReturnButton)
                    .addContainerGap(37, Short.MAX_VALUE))
            );
	}

	// 停止背单词，记录当前位置，进行保存
	public static void saveView() {
		if(wordController==null)
			return;
		if (wordController.saveRecord()){
				System.out.println("保存成功！");
		} else {
			System.out.println("出错了！");
		}
	}

	public void createContentPane() {
		// Create the content-pane-to-be.
		contentPane.setOpaque(true);
		
		if (wordController == null) {
			FileChooser.setText("选择词库");
			begin.setEnabled(false);
			stats.setEnabled(false);
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

        type.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "重新开始", "上次结束单词开始", "选定单词开始" }));

        Number.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        Number.setForeground(new java.awt.Color(102, 102, 102));
        //Number.setText("number");

        jLabel2.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel2.setText("路径");

        jLabel1.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel1.setText("方式");

        FileChooser.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        FileChooser.setText("选择文件");

        jLabel3.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel3.setText("单词数");


        jLabel4.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel4.setText("词库");

        range.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        range.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" }));

        filename.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        //filename.setText("filename");
        
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
		frame.setResizable(false);
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
		int height = 460;
		int width = 400;
		frame.setLocation(screenWidth - width / 2, screenHeight - height / 2);
		// Display the window.
		frame.setSize(width, height);
		frame.setVisible(true);
	}


	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getSource()==inputfield){
			int caretPosition = inputfield.getCaretPosition();
			String input=inputfield.getText();
			ArrayList<String> items=wordController.getSimilarWords(input,range.getSelectedItem().toString());
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

}
