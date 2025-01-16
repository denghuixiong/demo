package BigHomework.student;

import BigHomework.SQLD.AdminDao;
import BigHomework.SQLD.cosumeDao;
import jdbc.tool.ThreadLocalTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicBoolean;

import static BigHomework.student.LogoutExample.openDialogs;
import static java.lang.Thread.sleep;

public class cast  {
    cosumeDao Dao;
    String id = logIN.ID;
    JTextField moneyRemark;
    cast(){

        JDialog jDialog = new JDialog();

        JLabel jLabel = new JLabel("欢迎来到消费");
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel jLabelT = new JLabel("金额：");
        JTextField moneyTxt = new JTextField(30);

        JLabel jLabelmk = new JLabel("备注(不能为空)");
       moneyRemark = new JTextField(30);

        JButton jButton = new JButton("确定");




        LocalDate now = LocalDate.now();

        String date = now.toString();

        Dao = new cosumeDao();
        double sumMoney = Dao.seleatAllMoney(id);
//moneyTxt不能立即调用getText方法不然为空，要调用一个内部类或者调用事件让他有时间去调整
        jButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                //今天消费

                double casttodaySum = new casT(date,id).castSum;
                String textM = moneyTxt.getText();
                String textR= moneyRemark.getText();
                //输入的文本不能为空并且输入的金额要小于总金额
                if(textM.length()!=0&&textR.length()!=0&& sumMoney>Double.parseDouble(textM)){

                double money = Double.parseDouble(textM);

                if(casttodaySum<-50){
                   //限制消费
                    boolean passwd = passwd();
                    if(passwd){
                        Dao.insertOUt(money ,date,textR,id);
                        moneyTxt.setText("支付成功");
                    }
                }
                      //小于50
                else { Dao.insertOUt(money ,date,textR,id);
                      moneyTxt.setText("支付成功");}

            }
                else {
                    moneyTxt.setText("支付失败");
                }

            }
        });




        JPanel jPanel1 = new JPanel(new GridLayout(4,1));
        JPanel jPanelT = new JPanel();
        JPanel jPanel2 = new JPanel(new FlowLayout());
        JPanel jPanel3 = new JPanel(new FlowLayout());
        JPanel jPanelB = new JPanel();


        jPanelT.add(jLabel);

        jPanel2.add(jLabelT);
        jPanel2.add(moneyTxt);

        jPanel3.add(jLabelmk);
        jPanel3.add(moneyRemark);

        jPanelB.add(jButton);


        jPanel1.add( jPanelT);
        jPanel1.add(jPanel2);
        jPanel1.add(jPanel3);
        jPanel1.add(jPanelB);

        jDialog.add(jPanel1);
        jDialog.pack();
        jDialog.setVisible(true);
        openDialogs.add(jDialog);

    }
    class casT{
        double castSum;
        casT(String nowdate ,String STid){
           try {
               Connection getconnection = ThreadLocalTool.getconnection();
               PreparedStatement preparedStatement = getconnection.prepareStatement("select sum(con_money) from consume where con_money<0 and con_date=?and  con_stu_id = ?");
               preparedStatement.setObject(1,nowdate);
               preparedStatement.setObject(2,STid);
               ResultSet resultSet = preparedStatement.executeQuery();
               while (resultSet.next()) {
                   castSum = resultSet.getDouble(1);
               }
           } catch (Exception e) {
               throw new RuntimeException(e);
           }


       }
    }

    boolean passwd(){
        AtomicBoolean flag = new AtomicBoolean(true);  // 使用 AtomicBoolean 以便在匿名类中更新
        JDialog jDialog1 = new JDialog();
        JLabel jLabel1 = new JLabel("由于你今天消费过50请输入密码");
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel jPanel = new JPanel(new GridLayout(3,1));
        JTextField aGpasswd = new JTextField(12);
        JButton jButton1 = new JButton("确认");
        jButton1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                String textAG = aGpasswd.getText();
                if (!textAG.matches(new AdminDao().AdmsetPS(logIN.ID))) {
                    System.out.println(textAG);
                    aGpasswd.setText("密码错误");
                    flag.set(false);
                }
                else {
                    jDialog1.dispose();
                    jDialog1.setVisible(false);
                    flag.set(true);
                }

            }
        });
        jPanel.add(jLabel1);
        jPanel.add(aGpasswd);
        jPanel.add(jButton1);
        jDialog1.add(jPanel);
        jDialog1.pack();
        // 阻塞当前线程直到对话框关闭
        jDialog1.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);  // 设置模态对话框，阻止用户与其他窗口交互
        jDialog1.setVisible(true);
            return flag.get();

    }



}
