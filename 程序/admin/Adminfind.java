package BigHomework.manage;

import BigHomework.SQLD.cosumeDao;
import BigHomework.SQLD.cosumeTable;
import BigHomework.student.logIN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static BigHomework.student.LogoutExample.openDialogs;

public class Adminfind {
    Adminfind(){
        StringBuffer stringBuffer = new StringBuffer();
        JTextArea textArea = new JTextArea(20, 40);
        JDialog jDialog1 = new JDialog();
        JLabel jLabel1 = new JLabel("欢迎来到查询");
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel jPanelM = new JPanel(new GridLayout(5,1));
        JPanel jPaneltitle = new JPanel();
        JPanel jPanelSTA = new JPanel(new FlowLayout());
        JPanel jPanelEnd = new JPanel(new FlowLayout());
        JPanel jPanelR = new JPanel(new FlowLayout());

        JPanel jPanelAN = new JPanel();
        JLabel jLabel = new JLabel("欢迎来到查询");
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

        jPaneltitle.add(jLabel);

        JLabel jLabelS = new JLabel("开始时间（xxxx-xx-xx）");
        JTextField textSa = new JTextField(12);
        jPanelSTA.add(jLabelS);
        jPanelSTA.add(textSa);

        JLabel jLabelE = new JLabel("结束时间（xxxx-xx-xx）");
        JTextField textEn = new JTextField(12);
        jPanelEnd.add(jLabelE);
        jPanelEnd.add(textEn);

        JLabel jLabelR = new JLabel("地点");
        JTextField textR = new JTextField(12);
        jPanelR.add(jLabelR);
        jPanelR.add(textR);




        JButton jButton1 = new JButton("确认");
        jButton1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                super.mousePressed(e);
                //限制输
                if(!(textSa.getText().matches("\\d{1,4}(-)(1[0-2]|0?[1-9])\\1(0?[1-9]|[1-2]\\d|30|31)")
                        &&textSa.getText().matches("\\d{1,4}(-)(1[0-2]|0?[1-9])\\1(0?[1-9]|[1-2]\\d|30|31)"))){
                    textSa.setText("格式错误");
                    textEn.setText("格式错误");
                    return;

                }


                String Stext1 = textSa.getText();
                String Etext1 = textEn.getText();
                String Ram = textR.getText();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date Stext = simpleDateFormat.parse(Stext1);
                    Date Etext= simpleDateFormat.parse( Etext1);
                    cosumeDao cosumeDao = new cosumeDao();


                    List<cosumeTable> selectdatel = cosumeDao.selectdatel(Stext, Etext,Ram);
                    System.out.println(selectdatel);
                    stringBuffer.append("学生账号"+"                 "+"\t"+"编号"+" "+"\t"+"金额"+" "+"\t"+"备注"+" "+"\t"+"日期"+"\n");
                    for (int i = 0; i < selectdatel.size(); i++) {
                        cosumeTable cosumeTable =selectdatel.get(i);
                        int conId = cosumeTable.getConId();
                        double conMoney = cosumeTable.getConMoney();
                        String conRemark = cosumeTable.getConRemark();
                        Date conDate = cosumeTable.getConDate();
                        String conStuid = cosumeTable.getConStuid();
                        stringBuffer.append(conStuid+" "+"\t"+conId+" "+"\t"+conMoney+" "+"\t"+conRemark+"\t"+" "+conDate+"\n");
                    }
                    System.out.println(stringBuffer);
                    //这是对stringBuffer的清空
                    textArea.setText(" ");
                    System.out.println("我 被执行了");
                    textArea.setText(stringBuffer.toString());
                    stringBuffer.setLength(0);

                    //设置滚动
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    textArea.setEditable(false);//固定数据
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

                    // 使用 JOptionPane 显示带有滚动条的对话框
                    JOptionPane.showMessageDialog(null, scrollPane, "消费表", JOptionPane.INFORMATION_MESSAGE);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });





        jPanelAN.add(jButton1);

        jPanelM.add(jPaneltitle);
        jPanelM.add(jPanelSTA);
        jPanelM.add(jPanelEnd);
        jPanelM.add(jPanelR);

        jPanelM.add(jPanelAN);
        jDialog1.add(jPanelM);
        jDialog1.pack();
        jDialog1.setVisible(true);
        openDialogs.add(jDialog1);
    }
}
