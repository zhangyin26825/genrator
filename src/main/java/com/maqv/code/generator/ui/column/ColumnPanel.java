package com.maqv.code.generator.ui.column;

import com.maqv.code.generator.database.Column;
import com.maqv.code.generator.database.ColumnImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author zhangyin
 * @create 2019-12-17 10:16
 **/
public class ColumnPanel extends JPanel {

    private Column column;

    private JLabel jLabel;

    private JRadioButton input;

    private JRadioButton output;


    public ColumnPanel(Column column) {
        super(new GridLayout(1,3));
        this.column = column;
        jLabel=new JLabel(column.getColumnName());
        input=new JRadioButton("输入");
        input.setEnabled(true);
        input.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
               if(input.isSelected()){
                   input.setSelected(false);
               }else{
                   input.setSelected(true);
               }
            }
        });

        output=new JRadioButton("输出");
        output.setEnabled(true);
        add(jLabel);
        add(input);
        add(output);
    }




    public static void main(String[] args) {

        JFrame jFrame=new JFrame("测试 columnPanel");
        Column column=new ColumnImpl("Ads","fd",false,true,false,"",null,false,false);
        jFrame.setLayout(new BorderLayout());

        jFrame.add("Center",new ColumnPanel(column));
        jFrame.setEnabled(true);
        jFrame.setVisible(true);
        jFrame.setSize(600,400);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }


}
