package BigHomework.student;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static BigHomework.student.logIN.password;

public class LogoutExample {
    // 保存所有弹窗引用,窗口管理
   public static final List<JDialog> openDialogs = new ArrayList<>();

    public LogoutExample() {
        // 显示确认对话框
        int response = JOptionPane.showConfirmDialog(
                null,
                "确定要退出登录吗？",
                "确认退出",
                JOptionPane.YES_NO_OPTION
        );

        if (response == JOptionPane.YES_OPTION) {
            // 执行退出逻辑
            System.out.println("用户选择退出登录。");
            // 关闭所有弹窗，但保留主窗口
            for (JDialog dialog : openDialogs) {
                dialog.dispose();
            }
            // 清空弹窗列表,清空IO流读取的密码
            openDialogs.clear();
            password="";
            // 示例2：返回登录界面
            new logIN();
        }
    }
}