package BigHomework.manage;

import BigHomework.SQLD.AdminDao;
import BigHomework.SQLD.AdminTable;
import BigHomework.student.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class AdminLogou {
    public static String  stuID;
    AdminLogou(){
        JDialog dialog = new JDialog();
        dialog.setTitle("挂失解挂");
        dialog.setSize(400, 300);
        dialog.setResizable(false);

        // 创建文本框和查询按钮
        JTextField textField = new JTextField(20);
        JButton queryButton = new JButton("查询");
        queryButton.setBackground(new Color(100, 237, 159)); // 设置按钮背景颜色为浅蓝色
        queryButton.setForeground(Color.WHITE); // 设置按钮文字颜色为白色
        queryButton.setFocusPainted(false); // 去掉按钮的焦点边框
        queryButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                String id = textField.getText();
                if(id.length()==12){
                    JTextArea textArea = new JTextArea();
                    textArea.setText("");
                    stuID=id;
                    showTextArea(id,textArea);

                }else {
                    JOptionPane.showMessageDialog(null, "学号错误", "提示", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        // 按钮1: 挂失
        JButton lossButton = new JButton("挂失");
        lossButton.setBackground(new Color(118, 100, 237)); // 设置按钮背景颜色为浅蓝色
        lossButton.setForeground(Color.WHITE); // 设置按钮文字颜色为白色
        lossButton.setFocusPainted(false); // 去掉按钮的焦点边框
        lossButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textField.getText();
                if(id.length()==12){
// 点击挂失按钮时弹出操作成功的窗口
                    showSuccessDialog("挂失","挂失操作成功");
                    menu.result=false;
                }else {
                    JOptionPane.showMessageDialog(null, "学号错误", "提示", JOptionPane.INFORMATION_MESSAGE);
                }


            }
        });

        // 按钮2: 解挂
        JButton unfreezeButton = new JButton("解挂");
        unfreezeButton.setBackground(new Color(237, 148, 100)); // 设置按钮背景颜色为浅蓝色
        unfreezeButton.setForeground(Color.WHITE); // 设置按钮文字颜色为白色
        unfreezeButton.setFocusPainted(false); // 去掉按钮的焦点边框
        unfreezeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textField.getText();
                if(id.length()==12){
                    // 点击解挂按钮时弹出操作成功的窗口
                    showSuccessDialog("解挂","解挂操作成功");
                    menu.result=true;
                }else {
                    JOptionPane.showMessageDialog(null, "学号错误", "提示", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        // 布局设置
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1,10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 设置面板边距

        panel.add(textField);
        panel.add(queryButton);
        panel.add(lossButton);
        panel.add(unfreezeButton);


        dialog.add(panel);
        dialog.setVisible(true);

    }
    private static void showSuccessDialog(String set,String message) {
        //设置状态
           new AdminDao().Admset(set,stuID);
        // 创建成功的提示对话框
        JOptionPane.showMessageDialog(null, message, "操作成功", JOptionPane.INFORMATION_MESSAGE);
    }
    private static void showTextArea(String id,JTextArea textArea) {
        List<AdminTable> admin = new AdminDao().admin(id);
        System.out.println(admin);

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("学号"+"                              "+"\t"+"卡的状态"+"     "+"\t"+"学生的请求"+"\n");
        for (int i = 0; i < admin.size(); i++) {
            AdminTable adminTable = admin.get(i);
            String stuId = adminTable.getStu_id();
            String cardState = adminTable.getCard_state();
            String stuBeg = adminTable.getStu_beg();
            stringBuffer.append(stuId+" "+"\t"+cardState+" "+"\t "+stuBeg+"\n");
        }
        System.out.println(stringBuffer.toString());
        // 创建一个新弹窗显示文本域
        JDialog textDialog = new JDialog();
        textDialog.setTitle("查询结果");
        textDialog.setSize(400, 200);
        textDialog.setLayout(new BorderLayout());


        textArea.setText(" ");
        textArea.setText(stringBuffer.toString());
        stringBuffer.setLength(0);
        textArea.setEditable(false); // 设置文本域为只读
        JScrollPane scrollPane = new JScrollPane(textArea); // 加入滚动条

        textDialog.add(scrollPane, BorderLayout.CENTER);
        textDialog.setVisible(true);
    }

}
