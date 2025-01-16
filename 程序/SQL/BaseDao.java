package BigHomework.SQLD;

import jdbc.tool.ThreadLocalTool;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {
    public int executeUPData(String sql, Object... values) throws Exception {
        /*1:注册驱动
         * 2：获取连接
         * 3：编写sql
         * 4:为占位符赋值，执行sql，接收返回结果
         * 5：处理结果
         * 6：释放资源*/
        Connection getconnection = ThreadLocalTool.getconnection();
        PreparedStatement preparedStatement = getconnection.prepareStatement(sql);

        if (values != null && values.length > 0) {
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setObject(i + 1, values[i]);
            }
        }
        //赋完所有的值后executeUpdate() 返回修改了多少行，常用于执行数据更新、插入或删除操作
        int i=preparedStatement.executeUpdate();
        preparedStatement.close();
        //事务的开启如果是手动的就是false，就不能直接关闭，在那个finally里面关闭
        if(getconnection.getAutoCommit()){
            ThreadLocalTool.recycal();
        }
        return i;
    }




    public <T> List<T> executeQuery(Class<T> clazz, String sql, Object... values) throws Exception {//用来表示一个未知或特定类型的类。
        /*1:注册驱动
         * 2：获取连接
         * 3：编写sql
         * 4:为占位符赋值，执行sql，接收返回结果
         * 5：处理结果
         * 6：释放资源*/

        /*
         * 通用查询：多行多列，单行单列，单行多列（看advance.java）
         * 多行多列：Arraylist<T>
         * 单行单列：一个封装的结果，String
         * 单行多列：对象
         * 封装过程：
         *1：返回类型：泛型，类型不确定，用泛型方法，调用者知道
         * 2:返回结果：list
         * 3:结果的封装：反射
         * */

        ArrayList<T> list;
        try (Connection connection = ThreadLocalTool.getconnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                if (values != null && values.length > 0) {
                    for (int i = 0; i < values.length; i++) {
                        preparedStatement.setObject(i + 1, values[i]);
                    }

                }
                ResultSet resultSet = preparedStatement.executeQuery();
                //获取到表内容每一行内容，与这个对象每个属性的类型，然后一一封装
                //利用反射获取结果集中的元数据对象------>包含列的数量，每一列的名称
                ResultSetMetaData metaData = resultSet.getMetaData();

                list = new ArrayList<>();
                int columnCount = metaData.getColumnCount();

                while (resultSet.next()) {
                    T t = clazz.newInstance();//它等价于 new T(),当它查询一行时就建立一个对象
                    for (int i = 1; i <= columnCount; i++) {//resultSet是从1开始的
                        Object value = resultSet.getObject(i);//是从表里面获取值，value将会赋给t对象的一个属性

                        String FileName = metaData.getColumnLabel(i);//从数据库里面获取对象的属性名，就是获取列名
                        // metaData.getColumnName(i)
                        Field field = clazz.getDeclaredField(FileName);//给JavaBean里的属性名创建对象，属性名要与表中列名保持一致,表中的列名用别名法让它一致
                        field.setAccessible(true);
                        field.set(t, value);//给JavaBean里的属性名，动态设置对象字段的值


                    }
                    list.add(t);
                }
                resultSet.close();
                preparedStatement.close();
            }

            //事务的开启如果是手动的就是false，就不能直接关闭
            if (connection.getAutoCommit()) {
                ThreadLocalTool.recycal();
            }
        }
        return list;
    }


    public <T> T onlyexecuteQuery(Class<T> clazz, String sql, Object... values) throws Exception{
        List<T> list  = this.executeQuery(clazz, sql, values);
        if(list==null||list.size()==0){
            return null;
        }
        return list.get(0);
    }

}
