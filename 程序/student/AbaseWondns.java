package BigHomework.student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AbaseWondns extends JFrame {
    // 静态变量用于存储主窗口的宽度和高度
    static int mainWindowWidth;
    static int mainWindowHeight;

    public AbaseWondns() {
        super("学生一卡通"); // 设置窗口标题
        setBounds(20, 30, 500, 450); // 设置窗口位置和大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置关闭操作

        // 获取窗口的宽度和高度
        mainWindowWidth = getWidth();
        mainWindowHeight = getHeight();

        // 创建内容面板
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // 创建自定义背景面板
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // 加载背景图片
                ImageIcon backgroundImage = new ImageIcon("tup/主页面背景.png"); // 替换为你的图片路径
                Image image = backgroundImage.getImage();
                // 绘制背景图片
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // 创建欢迎标签
        JLabel welcomeLabel = new JLabel("欢迎来到学生一卡通服务端", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("宋体", Font.BOLD, 24)); // 设置字体样式
        welcomeLabel.setForeground(Color.black); // 设置标签文字颜色

        // 创建登录按钮
        JButton loginButton = new JButton("登录");
        loginButton.setBackground(new Color(100, 149, 237)); // 设置按钮背景颜色为浅蓝色
        loginButton.setForeground(Color.WHITE); // 设置按钮文字颜色为白色
        loginButton.setFocusPainted(false); // 去掉按钮的焦点边框
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                new logIN(); // 打开登录界面
            }
        });

        // 创建注册按钮
        JButton registerButton = new JButton("注册");
        registerButton.setBackground(new Color(0, 51, 255)); // 设置按钮背景颜色为紫色
        registerButton.setForeground(Color.WHITE); // 设置按钮文字颜色为白色
        registerButton.setFocusPainted(false); // 去掉按钮的焦点边框
        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // 显示注册提示信息
                JOptionPane.showMessageDialog(null, "不用注册，账号为学号\n初始密码为：123", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // 创建布局面板
        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 10, 10)); // 3行1列，间距为10px
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 设置面板边距
        mainPanel.setOpaque(false); // 设置面板透明，以便显示背景图片

        // 添加组件到面板
        mainPanel.add(welcomeLabel);
        mainPanel.add(loginButton);
        mainPanel.add(registerButton);

        // 将主面板添加到背景面板
        backgroundPanel.add(mainPanel, BorderLayout.CENTER);

        // 将背景面板添加到内容面板
        contentPane.add(backgroundPanel, BorderLayout.CENTER);

        // 设置窗口居中显示
        setLocationRelativeTo(null);

        // 显示窗口
        setVisible(true);
    }


}