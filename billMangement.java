package winterText.git;

import java.sql.*;
import java.util.Scanner;

public class billMangement {


    static  double money=10000;
    public static void main(String[] args) throws Exception{
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
        Scanner sc=new Scanner(System.in);
        int i = sc.nextInt();
        switch (i){
            case 1:
                income();
                break;
            case 2:
                expense();
                break;
            case 3:
                System.out.println("收入");
                break;
            case 4:
               dispalyAll();
                break;
            case 5:
                System.out.println("收入");
                break;
            default:
                System.out.println("输入有误");

        }
    }
    public static void income() {
        //连接，connection接口：
        String url="jdbc:mysql://localhost:3306/deng";// jdbc:mysql://这是固定的；// jdbc:mysql:///deng；本机IP
        String user="root";
        String passwork="88888888";
        //注册驱动，获取Class文件对象，可以不写
        Scanner sc = new Scanner(System.in);
        System.out.println("======================================");
        System.out.println("记录收入");
        System.out.println("======================================");
        System.out.print("请输入收入日期（YYYY-MM-DD）：");
        String date = sc.nextLine();
        System.out.print("请输入收入金额：");
        double incomeAmount = sc.nextDouble();
        sc.nextLine();  // 消费换行符
        System.out.print("请输入收入类别（如工资、奖金等）：");
        String category = sc.nextLine();
        System.out.print("请输入备注：");
        String remark = sc.nextLine();

        // 更新余额
        money += incomeAmount;

        // 保存收入记录到数据库
        try (Connection connection = DriverManager.getConnection(url, user, passwork);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO money (日期, 收入金额, 收入类型, 备注) VALUES (?, ?, ?, ?)");) {
            preparedStatement.setString(1, date);
            preparedStatement.setDouble(2, incomeAmount);
            preparedStatement.setString(3, category);
            preparedStatement.setString(4, remark);
            preparedStatement.executeUpdate();
            System.out.println("收入记录成功！当前余额：" + money);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void expense() {
        //连接，connection接口：
        String url="jdbc:mysql://localhost:3306/deng";// jdbc:mysql://这是固定的；// jdbc:mysql:///deng；本机IP
        String user="root";
        String passwork="88888888";
        //注册驱动，获取Class文件对象，可以不写
        Scanner sc = new Scanner(System.in);
        System.out.println("======================================");
        System.out.println("记录支出");
        System.out.println("======================================");
        System.out.print("请输入支出日期（YYYY-MM-DD）：");
        String date = sc.nextLine();
        System.out.print("请输入支出金额：");
        double expenseAmount = sc.nextDouble();
        sc.nextLine();  // 消费换行符
        System.out.print("请输入支出类别（如购物、餐饮等）：");
        String category = sc.nextLine();
        System.out.print("请输入备注：");
        String remark = sc.nextLine();

        // 检查余额是否足够
        if (expenseAmount > money) {
            System.out.println("余额不足，无法记录支出！当前余额：" + money);
            return;
        }

        // 更新余额
        money -= expenseAmount;

        // 保存支出记录到数据库
        try (Connection connection = DriverManager.getConnection(url, user, passwork);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO money (日期, 支出金额, 支出类型, 备注) VALUES (?, ?, ?, ?)");) {
            preparedStatement.setString(1, date);
            preparedStatement.setDouble(2, expenseAmount);
            preparedStatement.setString(3, category);
            preparedStatement.setString(4, remark);
            preparedStatement.executeUpdate();
            System.out.println("支出记录成功！当前余额：" + money);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void dispalyAll()throws Exception{
        //连接，connection接口：
        String url="jdbc:mysql://localhost:3306/deng";// jdbc:mysql://这是固定的；// jdbc:mysql:///deng；本机IP
        String user="root";
        String passwork="88888888";
        //注册驱动，获取Class文件对象，可以不写

        Connection connection = DriverManager.getConnection(url, user, passwork);
        //获取sql语句 编写sql，执行，接收结果集合
        PreparedStatement preparedStatement = connection.prepareStatement("select *from money where 日期=?");//?占位
        String datel="2013-10-01";
        preparedStatement.setString(1,datel);
        ResultSet resultSet = preparedStatement.executeQuery();

        //遍历结果集
        while (resultSet.next()) {
           String date = resultSet.getString("日期");
            double money=resultSet.getDouble("收入金额");
            String valour=resultSet.getString("收入类型");
            String ad=resultSet.getString("备注");
            System.out.println(date+"\t"+money+"\t"+valour+"\t"+ad);
        }
        resultSet.close();
       preparedStatement.close();//用于sql与数据库进行交互的，当查询语句是动态时，需要语句连接，‘“+n+"’"，防止sql注入
        connection.close();


    }
}
