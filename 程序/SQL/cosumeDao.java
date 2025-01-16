package BigHomework.SQLD;

import jdbc.tables.DAO.BaseDao;
import jdbc.tables.employeeTable;

import javax.swing.*;
import java.util.Date;
import java.util.List;

public class cosumeDao extends BaseDao implements FionshD {
    public cosumeDao(){

    }
    @Override
    public  List<cosumeTable> selectdate(Date dataStart, Date dataEnd ,String ID) {
        String SQL = "select *from consume where con_stu_id=? and  con_date BETWEEN ? and ?";
        List<cosumeTable> cosumeTableslist;
        try {
            cosumeTableslist = executeQuery(cosumeTable.class, SQL,ID,dataStart, dataEnd);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
       return cosumeTableslist;
    }

    public  List<cosumeTable> selectdatel(Date dataStart, Date dataEnd , String Rammke) {
        String SQL = "select *from consume where con_remark=?  and  con_date BETWEEN ? and ?";
        List<cosumeTable> cosumeTableslist;
        try {
            cosumeTableslist = executeQuery(cosumeTable.class, SQL,Rammke,dataStart, dataEnd);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cosumeTableslist;
    }

    @Override
    public void insertIN(double money ,String date ,String id) {
        String sql="insert into consume (con_stu_id,con_money,con_date,con_remark) values (?,?,?,'充值')";
        try {
            executeUPData(sql,id,money,date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void insertOUt(double money, String date, String remark,String id) {
            String sql="insert into consume (con_stu_id,con_money,con_date,con_remark) values (?,?,?,?)";
        double money1 = -1 * money;
        try {
            executeUPData(sql,id,money1,date,remark);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public double seleatAllMoney(String id) {
       String sql="select con_money from consume where con_stu_id=?";
        List<cosumeTable> sum;
        try {
            sum = executeQuery(cosumeTable.class, sql, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        double suMmoney=0;
        for (cosumeTable T:sum){
            double conMoney = T.getConMoney();
            suMmoney+=conMoney;
        }
        return suMmoney;
    }

}
