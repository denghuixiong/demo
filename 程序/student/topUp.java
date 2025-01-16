package BigHomework.student;

import BigHomework.SQLD.cosumeDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

import static BigHomework.student.LogoutExample.openDialogs;

public class topUp {
    cosumeDao cosumedao;

   public static JTextField money;
    topUp(){
        JDialog jDialog = new JDialog();
        jDialog.setBounds(30, 30, 600, 590);
        jDialog.setTitle("登录");

        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel(new FlowLayout());
        JPanel jPanel3 = new JPanel();
        JPanel jPanelC = new JPanel(new GridLayout(3,1));

        JLabel jLabel1 = new JLabel("欢迎来到充值");
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel1.add(jLabel1);


        JLabel jLabel = new JLabel("金额：");
        money = new JTextField(30);
        jPanel2.add(jLabel);
        jPanel2.add(money);

        JButton jButton = new JButton("确定");
        jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                //组合
                cosumedao = new cosumeDao();
                new TF(cosumedao);


            }
        });





        jPanel3.add(jButton);


        jPanelC.add(jPanel1);
        jPanelC.add(jPanel2);
        jPanelC.add(jPanel3);

        jDialog.add(jPanelC);
        jDialog.pack();
        jDialog.setVisible(true);
        openDialogs.add(jDialog);

    }
    class TF {
        TF( cosumeDao cosumedao){
            double tempM =Double.parseDouble(money.getText());
            if(tempM<=0||tempM>500){
                money.setText("金额不对，金额应该大于0小于500");
            }else {
                //加入金额与时间
                String id = logIN.ID;
                LocalDate now = LocalDate.now();
                String date = now.toString();
                cosumedao.insertIN(tempM,date,id);
                money.setText("充值成功");
            }
        }

    }
}
