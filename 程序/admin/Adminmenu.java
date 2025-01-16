package BigHomework.manage;

import BigHomework.student.LogoutExample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static BigHomework.student.LogoutExample.openDialogs;

public class Adminmenu {
    public Adminmenu() {
        // 创建弹窗
        JDialog frame = new JDialog();
        frame.setSize(500, 450);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setTitle("管理员菜单");

        // 添加标题
        JLabel titleLabel = new JLabel("欢迎来到管理员界面", JLabel.CENTER);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel topPanel = new JPanel();
        topPanel.add(titleLabel);
        frame.add(topPanel, BorderLayout.NORTH);

        // 添加图片
        ImageIcon imageIcon = new ImageIcon("tup/管理员端.png"); // 替换为你的图片路径
        JLabel imageLabel = new JLabel(imageIcon);
        JPanel imagePanel = new JPanel();
        imagePanel.add(imageLabel);
        frame.add(imagePanel, BorderLayout.CENTER); // 将图片放在窗口的中间

        // 创建导航栏
        JToolBar toolBar = new JToolBar();
        toolBar.setMargin(new Insets(5, 150, 5, 10)); // 设置工具栏的边距
        toolBar.setFloatable(false); // 禁止拖动导航栏

        // 创建导航栏按钮
        JButton button1 = new JButton("审核");
        JButton button2 = new JButton("挂失与解挂");
        JButton button3 = new JButton("统计");
        JButton button4 = new JButton("退出");

        // 为按钮添加事件监听器
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Admincheck();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminLogou();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Adminfind();
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LogoutExample();
            }
        });

        // 将按钮添加到导航栏
        toolBar.add(button1);
        toolBar.add(button2);
        toolBar.add(button3);
        toolBar.add(button4);

        // 将导航栏添加到窗口的底部
        frame.add(toolBar, BorderLayout.SOUTH);

        // 设置窗口居中显示
        frame.setLocationRelativeTo(null);

        // 显示窗口
        frame.setVisible(true);

        openDialogs.add(frame);
    }
}