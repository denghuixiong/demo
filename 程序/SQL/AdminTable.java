package BigHomework.SQLD;

public class AdminTable {
    String stu_id;
    String card_state;
    String stu_beg;

    String stu_ps;

    public AdminTable() {
    }

    public AdminTable(String stu_id, String card_state, String stu_beg ,String stu_ps) {
        this.stu_id = stu_id;
        this.card_state = card_state;
        this.stu_beg = stu_beg;
        this.stu_ps=stu_ps;
    }

    /**
     * 获取
     * @return stu_id
     */
    public String getStu_id() {
        return stu_id;
    }

    /**
     * 设置
     * @param stu_id
     */
    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    /**
     * 获取
     * @return card_state
     */
    public String getCard_state() {
        return card_state;
    }

    /**
     * 设置
     * @param card_state
     */
    public void setCard_state(String card_state) {
        this.card_state = card_state;
    }

    /**
     * 获取
     * @return stu_beg
     */
    public String getStu_beg() {
        return stu_beg;
    }

    /**
     * 设置
     * @param stu_beg
     */
    public void setStu_beg(String stu_beg) {
        this.stu_beg = stu_beg;
    }

    public String getStu_ps() {
        return stu_ps;
    }

    public void setStu_ps(String stu_ps) {
        this.stu_ps = stu_ps;
    }

    public String toString() {
        return "AdminTable{stu_id = " + stu_id + ", card_state = " + card_state + ", stu_beg = " + stu_beg + "}";
    }
}
