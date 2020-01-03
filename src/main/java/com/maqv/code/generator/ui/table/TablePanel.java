package com.maqv.code.generator.ui.table;

import com.intellij.ui.components.JBList;
import com.intellij.ui.components.JBScrollPane;
import com.maqv.code.generator.database.Column;
import com.maqv.code.generator.database.ColumnImpl;
import com.maqv.code.generator.database.Table;
import com.maqv.code.generator.database.mysql.JdbcUtil;
import com.maqv.code.generator.database.mysql.MysqlColumnInfo;
import com.maqv.code.generator.ui.column.ColumnPanel;
import org.apache.commons.collections.CollectionUtils;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Collections;
import java.util.List;

/**
 * @author zhangyin
 * @create 2019-12-17 10:31
 **/
public class TablePanel extends JPanel {

    private Table table;

    private JLabel label;

    public TablePanel(Table table) {
        this.table = table;
        this.setLayout(new BorderLayout());
        label=new JLabel(table.getTableName());

        DefaultListModel defaultListModel = new DefaultListModel();
        List<Column> columns = table.getColumns();
        for (Column column : columns) {
            defaultListModel.addElement(column);
        }


        JList jbList=new JList(defaultListModel);
        jbList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jbList.setModel(defaultListModel);
        jbList.setCellRenderer(new ColumnCellRender());
        jbList.setLayoutOrientation(JList.VERTICAL);
        jbList.setVisibleRowCount(-1);

        jbList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int firstIndex = e.getFirstIndex();
                System.out.println("点击了"+firstIndex);

            }
        });


        JScrollPane listScroller = new JScrollPane(jbList);
        listScroller.setPreferredSize(new Dimension(250, 80));
        this.add("Center",listScroller);
        this.add("North",label);
    }

    public static void main(String[] args) {

        java.util.List<MysqlColumnInfo> columns = JdbcUtil.queryColumns();
        List<Table> tables = MysqlColumnInfo.convert(columns);

        JFrame jFrame=new JFrame("测试 columnPanel");

        jFrame.setLayout(new BorderLayout());

        jFrame.add("Center",new TablePanel(tables.get(0)));
        jFrame.setEnabled(true);
        jFrame.setVisible(true);
        jFrame.setSize(600,400);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
