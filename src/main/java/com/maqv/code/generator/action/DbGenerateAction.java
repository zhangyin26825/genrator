package com.maqv.code.generator.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.popup.ComponentPopupBuilder;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.util.ui.ColumnInfo;
import com.maqv.code.generator.G;
import com.maqv.code.generator.database.Table;
import com.maqv.code.generator.database.mysql.JdbcUtil;
import com.maqv.code.generator.database.mysql.MysqlColumnInfo;
import com.maqv.code.generator.file.TableGeneratorTask;
import com.maqv.code.generator.ui.listselect.DataListSelect;
import com.maqv.code.generator.ui.listselect.ListValue;
import com.maqv.code.generator.ui.listselect.SelectedCallback;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyin
 * @create 2019-12-16 15:44
 **/
public class DbGenerateAction  extends CommonAction{
    @Override
    public void ownActionPerformed(AnActionEvent e) {
        List<MysqlColumnInfo> columns = JdbcUtil.queryColumns();
        List<Table> tables = MysqlColumnInfo.convert(columns);

        SelectedCallback selectedCallback=new SelectedCallback() {
            @Override
            public void handleSelectValue(ListValue listValue) {
                if(listValue instanceof  Table){
                    Table table = (Table) listValue;
                    TableGeneratorTask tableGeneratorTask = new TableGeneratorTask(table, table.getColumns());
                    tableGeneratorTask.createTableRelationClasses();

                }
            }
        };
        List<ListValue> listValues=new ArrayList(tables);
        DataListSelect dataListSelect=new DataListSelect(listValues,selectedCallback);
        ComponentPopupBuilder componentPopupBuilder = JBPopupFactory.getInstance().createComponentPopupBuilder(dataListSelect, null);
        componentPopupBuilder.setProject(G.getProject());
        componentPopupBuilder.setMinSize(new Dimension(200,400));
        JBPopup jbPopup = componentPopupBuilder.createPopup();
        jbPopup.showInBestPositionFor(e.getDataContext());
        dataListSelect.setJbPopup(jbPopup);
    }
}
