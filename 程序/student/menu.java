package BigHomework.student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static BigHomework.student.LogoutExample.openDialogs;

public class menu {

    public static boolean result = true;

    menu() {
        // 控制一开始的卡的状态
        String s = reportTenloss.selectcardState();
        if (s.equals("挂失")) {
            result = false;
        } else {
            result = true;
        }

        JDialog jDialog = new JDialog();
        jDialog.setSize(AbaseWondns.mainWindowHeight, AbaseWondns.mainWindowHeight);
        jDialog.setLayout(new BorderLayout());
        jDialog.setResizable(false);
        jDialog.setTitle("学生端菜单");

        // 添加标题
        JLabel jLabel = new JLabel("欢迎来到菜单", JLabel.CENTER);
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel topPanel = new JPanel();
        topPanel.add(jLabel);
        jDialog.add(topPanel, BorderLayout.NORTH);

        ImageIcon imageIcon = new ImageIcon("tup/学生端.png"); // 替换为你的图片路径
        JLabel imageLabel = new JLabel(imageIcon);
        JPanel imagePanel = new JPanel();
        imagePanel.add(imageLabel);
        jDialog.add(imagePanel, BorderLayout.CENTER); // 将图片放在窗口的中间

        // 创建导航栏
        JToolBar toolBar = new JToolBar();
        toolBar.setMargin(new Insets(5, 50, 5, 10)); // 设置工具栏的边距
        toolBar.setFloatable(false); // 禁止拖动导航栏

        // 创建按钮并添加到导航栏
        JButton jButton2 = new JButton("修改密码");
        JButton jButton3 = new JButton("充值");
        JButton jButton4 = new JButton("消费");
        JButton jButton5 = new JButton("查询");
        JButton jButton6 = new JButton("挂失与解挂");
        JButton jButton7 = new JButton("退出登录");

        // 为按钮添加事件监听器
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new updatePW();
            }
        });

        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (result) {
                    new topUp();
                } else {
                    JOptionPane.showMessageDialog(null, "此账号已经挂失，暂时无法充值，请联系管理员", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        jButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (result) {
                    new cast();
                } else {
                    JOptionPane.showMessageDialog(null, "此账号已经挂失，暂时无法消费，请联系管理员", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        jButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new find();
            }
        });

        jButton6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new reportTenloss();
            }
        });

        jButton7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LogoutExample();
            }
        });

        // 将按钮添加到导航栏
        toolBar.add(jButton2);
        toolBar.add(jButton3);
        toolBar.add(jButton4);
        toolBar.add(jButton5);
        toolBar.add(jButton6);
        toolBar.add(jButton7);

        // 将导航栏添加到窗口的中间
        jDialog.add(toolBar, BorderLayout.SOUTH);

        // 设置窗口居中显示
        jDialog.setLocationRelativeTo(null);

        // 显示窗口
        jDialog.setVisible(true);

        openDialogs.add(jDialog);
    }
}