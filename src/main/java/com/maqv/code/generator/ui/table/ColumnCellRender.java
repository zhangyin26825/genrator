package com.maqv.code.generator.ui.table;

import com.maqv.code.generator.database.Column;
import com.maqv.code.generator.ui.column.ColumnPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author zhangyin
 * @create 2019-12-17 10:40
 **/
public class ColumnCellRender implements ListCellRenderer  {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        return new ColumnPanel((Column) value);
    }
}
