package BigHomework.SQLD;

import jdbc.tables.DAO.BaseDao;

import java.util.Arrays;
import java.util.List;

public class AdminDao extends BaseDao  {
    //这是个空参数构造，为了外部调用它里面的方法的通道
    public AdminDao(){

    }
public List<AdminTable> admin( String id){
        List<AdminTable> ad;
    try {
        ad=executeQuery(AdminTable.class,"select * from Admin where stu_id=?",id);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }

    return ad;
}
public void stu(String beg,String id){
    try {
       executeUPData("update Admin set stu_beg=? where stu_id=?", beg, id);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}

    public void  Admset(String state,String id){
        try {
            executeUPData("update Admin set card_state=? where stu_id=?", state, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String AdmsetPS(String id){
        AdminTable adminTable = null;
        try {
            adminTable = onlyexecuteQuery(AdminTable.class, "select stu_ps from Admin where stu_id=?", id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return adminTable.getStu_ps();
    }
    public void setPS(String psword,String id){
        try {
            executeUPData("update Admin set Admin.stu_ps=? where stu_id=?",psword,id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
