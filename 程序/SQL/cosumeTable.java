package BigHomework.SQLD;

import java.util.Date;

public class cosumeTable {
    private int con_id;
    private  double con_money;
    private Date con_date;
    private String con_remark;

    private String con_stu_id;

    public cosumeTable() {
    }

    public cosumeTable(int conId, double conMoney, Date conDate, String conRemark, String conStuid) {
        this.con_id = conId;
        this.con_money = conMoney;
        this.con_date = conDate;
        this.con_remark = conRemark;
        this.con_stu_id =conStuid;
    }

    /**
     * 获取
     * @return conId
     */
    public int getConId() {
        return con_id;
    }

    /**
     * 设置
     * @param conId
     */
    public void setConId(int conId) {
        this.con_id = conId;
    }

    /**
     * 获取
     * @return conMoney
     */
    public double getConMoney() {
        return con_money;
    }

    /**
     * 设置
     * @param conMoney
     */
    public void setConMoney(double conMoney) {
        this.con_money = conMoney;
    }

    /**
     * 获取
     * @return conDate
     */
    public Date getConDate() {
        return con_date;
    }

    /**
     * 设置
     * @param conDate
     */
    public void setConDate(Date conDate) {
        this.con_date = conDate;
    }

    /**
     * 获取
     * @return conRemark
     */
    public String getConRemark() {
        return con_remark;
    }

    /**
     * 设置
     * @param conRemark
     */
    public void setConRemark(String conRemark) {
        this.con_remark = conRemark;
    }


    public String getConStuid(){return con_stu_id;}

    public void setConStuid(String conStuid){this.con_stu_id=conStuid;}


}
