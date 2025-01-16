package BigHomework.student;

import BigHomework.SQLD.AdminDao;
import BigHomework.student.logIN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;

import static BigHomework.student.LogoutExample.openDialogs;

public class updatePW {

    updatePW(){
        JDialog jDialog = new JDialog();
        jDialog.setTitle("修改密码");
        jDialog.setBounds(30, 30, 600, 590);
        JPanel jPanel = new JPanel(new GridLayout(4,1));


        JLabel jLabel = new JLabel("密码修改");
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel jPanel1 = new JPanel();
        jPanel1.add(jLabel);


        JTextField jTextFieldOld = new JTextField(25);
        JTextField jTextFieldNew = new JTextField(25);

        JPanel TPanel1 = new JPanel(new FlowLayout());
        TPanel1.add(new JLabel("旧密码："));
        TPanel1.add(jTextFieldOld);

        JPanel TPanel2 = new JPanel(new FlowLayout());
        TPanel2.add(new JLabel("新密码："));
        TPanel2.add(jTextFieldNew);


        JButton jButton = new JButton("提交");

        jButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                String password = new AdminDao().AdmsetPS(logIN.ID);
                String text1 = jTextFieldOld.getText();
                boolean matches = password.matches(text1);

                if(matches){
                    String text = jTextFieldNew.getText();
                    try {
                    new AdminDao().setPS(text,logIN.ID);
                        jTextFieldOld.setText("修改成功");
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }

                }else {
                    System.out.println("我 是 pass"+password);
                   jTextFieldOld.setText("修改失败");
                }


            }
        });
        JPanel jPanelBT = new JPanel();
        jPanel1.add(jButton);

        jPanel.add(jPanel1);
        jPanel.add(TPanel1);
        jPanel.add(TPanel2);
        jPanel.add(jPanelBT);


        jDialog.add(jPanel);
        jDialog.pack();
        openDialogs.add(jDialog);
        jDialog.setVisible(true);
    }
}
