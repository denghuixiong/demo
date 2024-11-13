package winterText.git;

package winterText.git;



import java.sql.*;
import java.util.Scanner;

public class bollmanage {

    static double money = 10000; // 初始金额
    static final String DB_URL = "jdbc:mysql://localhost:3306/bill_management"; // MySQL数据库的URL
    static final String DB_USER = "root"; // 数据库用户名
    static final String DB_PASSWORD = "your_password"; // 数据库密码

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("======================================");
                System.out.println("欢迎使用个人账单管理系统");
                System.out.println("======================================");
                System.out.println("请选择操作：");
                System.out.println("1:记录收入");
                System.out.println("2：记录支出");
                System.out.println("3：查询所有的账单");
                System.out.println("4：查询账单");
                System.out.println("5：退出系统");

                System.out.println("请你输入选项序号：");
                int i = sc.nextInt();

                switch (i) {
                    case 1:
                        income();
                        break;
                    case 2:
                        expense();
                        break;
                    case 3:
                        showAllBills();
                        break;
                    case 4:
                        displayBillDetails();
                        break;
                    case 5:
                        System.out.println("退出系统...");
                        System.exit(0); // 退出程序
                        break;
                    default:
                        System.out.println("输入有误，请重新选择！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 记录收入
    public static void income() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("请输入收入金额：");
            double amount = sc.nextDouble();
            money += amount; // 增加账户余额

            String query = "INSERT INTO bills (type, amount) VALUES ('收入', ?)";
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setDouble(1, amount);
                pstmt.executeUpdate();
                System.out.println("收入记录成功！当前余额：" + money);
            }
        } catch (SQLException e) {
            System.out.println("记录收入时出错：" + e.getMessage());
        }
    }

    // 记录支出
    public static void expense() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("请输入支出金额：");
            double amount = sc.nextDouble();
            if (amount > money) {
                System.out.println("余额不足，无法记录支出！");
                return;
            }
            money -= amount; // 减少账户余额

            String query = "INSERT INTO bills (type, amount) VALUES ('支出', ?)";
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setDouble(1, amount);
                pstmt.executeUpdate();
                System.out.println("支出记录成功！当前余额：" + money);
            }
        } catch (SQLException e) {
            System.out.println("记录支出时出错：" + e.getMessage());
        }
    }

    // 查询所有账单
    public static void showAllBills() {
        String query = "SELECT * FROM bills";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("所有账单：");
            while (rs.next()) {
                String type = rs.getString("type");
                double amount = rs.getDouble("amount");
                System.out.println(type + "：" + amount);
            }
        } catch (SQLException e) {
            System.out.println("查询账单时出错：" + e.getMessage());
        }
    }

    // 查询指定账单
    public static void displayBillDetails() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("请输入账单ID：");
            int billId = sc.nextInt();

            String query = "SELECT * FROM bills WHERE id = ?";
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, billId);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    String type = rs.getString("type");
                    double amount = rs.getDouble("amount");
                    System.out.println("账单ID: " + billId + " 类型: " + type + " 金额: " + amount);
                } else {
                    System.out.println("未找到该账单！");
                }
            }
        } catch (SQLException e) {
            System.out.println("查询账单时出错：" + e.getMessage());
        }
    }
}

