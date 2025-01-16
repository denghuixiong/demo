package BigHomework.manage;

import BigHomework.student.logIN;
import jdbc.tables.DAO.BaseDao;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Admincheck {
    Admincheck(){
        JDialog dialog = new JDialog();
        dialog.setSize(300, 200);


        // 设置弹窗的布局和组件
        JPanel panel0 = new JPanel(new GridLayout(2,1));
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();



        JLabel titleLabel = new JLabel("审核");
       titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel1.add(titleLabel);

        JButton button1 = new JButton("查询");
        button1.addActionListener(e -> {
            // 创建自定义弹窗
            JDialog dialog1 = new JDialog();
            dialog1.setSize(400, 300);


            // 传入的集合数据
            List<String> data = new ArrayList<>();
            data.add(logIN.stuNEW.get(0));

            // 创建表格的模型
            DefaultTableModel tableModel = new DefaultTableModel() {
                @Override
                public Class<?> getColumnClass(int column) {
                    // 第二列为 Boolean 类型，用于显示复选框
                    return column == 1 ? Boolean.class : String.class;
                }
            };
            tableModel.addColumn("内容");
            tableModel.addColumn("选择");

            // 向表格中添加数据
            for (String item : data) {
                tableModel.addRow(new Object[]{item, false}); // 第二列初始为未选中状态
            }

            // 创建表格
            JTable table = new JTable(tableModel);

            // 设置自定义渲染器和编辑器来显示复选框
            table.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    JCheckBox checkBox = new JCheckBox();
                    checkBox.setSelected((Boolean) value); // 设置复选框状态
                    checkBox.setHorizontalAlignment(SwingConstants.CENTER); // 居中显示
                    return checkBox;
                }
            });
            table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JCheckBox()));

            // 创建一个面板来装载表格和按钮
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.add(new JScrollPane(table), BorderLayout.CENTER);

            // 同意按钮
            JButton agreeButton = new JButton("同意");
            panel.add(agreeButton, BorderLayout.SOUTH);

            // 鼠标点击监听事件
            agreeButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // 获取所有复选框的选择状态
                    StringBuilder selectedItems = new StringBuilder("已选择:\n");
                    List<String> selectedData = new ArrayList<>(); // 用于保存选中的集合
                    for (int row = tableModel.getRowCount() - 1; row >= 0; row--) {
                        boolean isChecked = (Boolean) tableModel.getValueAt(row, 1); // 获取复选框状态
                        if (isChecked) {
                            String item = (String) tableModel.getValueAt(row, 0);
                            selectedItems.append(item).append("\n");
                            selectedData.add(item); // 保存到集合
                            tableModel.removeRow(row); // 删除已选中的行
                        }
                    }

                    // 如果有选中项，输出选中的项
                    if (!selectedData.isEmpty()) {
                        JOptionPane.showMessageDialog(dialog1, selectedItems.toString(), "选择结果", JOptionPane.INFORMATION_MESSAGE);
                        try {
                            new BaseDao().executeUPData("insert into Admin values (?,'解挂','解挂','123')",logIN.stuNEW.get(0));
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(dialog1, "没有选择任何项", "选择结果", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            // 添加面板到弹窗
            dialog1.add(panel);
            dialog1.setVisible(true);
        });



        panel1.add(titleLabel);
        panel2.add(button1);


        panel0.add(panel1);
        panel0.add(panel2);

        dialog.add(panel0);
        dialog.setVisible(true);
    }

}
