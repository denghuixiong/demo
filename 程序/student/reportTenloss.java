package BigHomework.student;
import BigHomework.SQLD.AdminDao;
import BigHomework.SQLD.AdminTable;
import BigHomework.student.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static BigHomework.student.LogoutExample.openDialogs;

public class reportTenloss {
    reportTenloss(){
        JDialog frame = new JDialog();
        frame.setTitle("挂失与解挂");
        // 设置窗口大小
        frame.setSize(400, 200);



        // 设置布局管理器为 FlowLayout
        frame.setLayout(new FlowLayout());

        // 创建标题标签
        JLabel titleLabel = new JLabel("欢迎来到挂失与解挂");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setPreferredSize(new Dimension(350, 30));

        // 创建挂失按钮
        JButton reportLossButton = new JButton("挂失");
        reportLossButton.setPreferredSize(new Dimension(150, 40));

        // 创建解挂按钮
        JButton unfreezeButton = new JButton("解挂");
        unfreezeButton.setPreferredSize(new Dimension(150, 40));

        // 给挂失按钮添加事件监听
        reportLossButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                new AdminDao().stu("挂失",logIN.ID);
                System.out.println(logIN.ID);
                boolean adminset = selectcardState().equals("挂失");
                if(adminset){
                    JOptionPane.showMessageDialog(null, "挂失操作失败，当前卡在挂失状态", "提示", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null, "正在向管理员申挂失，请耐心等待", "提示", JOptionPane.INFORMATION_MESSAGE);
                }



            }
        });

        // 给解挂按钮添加事件监听器





        unfreezeButton.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {

                new AdminDao().stu("解挂",logIN.ID);
                boolean adminSet = selectcardState().equals("解挂");
                System.out.println(adminSet);

                if(adminSet){
                    JOptionPane.showMessageDialog(null, "解挂操作失败，当前卡在解挂状态", "提示", JOptionPane.INFORMATION_MESSAGE);
                }else {

                    JOptionPane.showMessageDialog(null, "正在向管理员申请解挂，请耐心等待", "提示", JOptionPane.INFORMATION_MESSAGE);
                }


            }
        });

        // 将标题和按钮添加到窗口中
        frame.add(titleLabel);
        frame.add(reportLossButton);
        frame.add(unfreezeButton);



        // 显示窗口
        frame.setVisible(true);
        openDialogs.add(frame);
    }

//这个是查询当前卡的状态的
    public static String selectcardState(){
        ArrayList<String> list = new ArrayList<>();
        List<AdminTable> admin = new AdminDao().admin(logIN.ID);
        for (int i = 0; i < admin.size(); i++) {
            AdminTable adminTable = admin.get(i);
            list.add(adminTable.getCard_state());
        }
        return list.get(0);
    }

    }

