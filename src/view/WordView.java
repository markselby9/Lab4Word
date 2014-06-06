package view;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * 背单词.java
 *
 * Created on 2014-6-5, 9:47:25
 */

/**
 *
 * @author Administrator
 */
public class WordView extends javax.swing.JPanel {

    /** Creates new form 背单词 */
    public WordView() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        Chi = new javax.swing.JTextField();
        Eng = new javax.swing.JTextField();
        OK = new javax.swing.JButton();
        NOTOK = new javax.swing.JButton();
        ChiLabel = new javax.swing.JLabel();
        EngLabel = new javax.swing.JLabel();
        ReturnButton = new javax.swing.JButton();

        Chi.setFont(new java.awt.Font("微软雅黑", 0, 12));
        Chi.setText("中文释义");
        Chi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChiActionPerformed(evt);
            }
        });

        Eng.setFont(new java.awt.Font("微软雅黑", 0, 12));
        Eng.setText("单词输入");
        Eng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EngActionPerformed(evt);
            }
        });

        OK.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        OK.setText("我非常确定^-^");
        OK.setActionCommand("d");
        OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKActionPerformed(evt);
            }
        });

        NOTOK.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        NOTOK.setText("我不记得了T-T");
        NOTOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NOTOKActionPerformed(evt);
            }
        });

        ChiLabel.setFont(new java.awt.Font("微软雅黑", 0, 12));
        ChiLabel.setText("中文释义");

        EngLabel.setFont(new java.awt.Font("微软雅黑", 0, 12));
        EngLabel.setText("单词输入");

        ReturnButton.setText("返回主界面");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
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
    }

    private void ChiActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void EngActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void OKActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void NOTOKActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void CorrectEngActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    // Variables declaration - do not modify
    private javax.swing.JTextField Chi;
    private javax.swing.JLabel ChiLabel;
    private javax.swing.JTextField CorrectEng;
    private javax.swing.JTextField Eng;
    private javax.swing.JLabel EngLabel;
    private javax.swing.JButton NOTOK;
    private javax.swing.JButton OK;
    private javax.swing.JButton ReturnButton;
    // End of variables declaration

}
