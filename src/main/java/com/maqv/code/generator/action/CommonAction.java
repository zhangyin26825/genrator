package com.maqv.code.generator.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.maqv.code.generator.G;


import java.io.IOException;
import java.util.Properties;

/**
 * @author zhangyin
 * @create 2019-11-18 15:18
 **/
public abstract class CommonAction extends AnAction {

    private static String FILE_NAME="maqvConfig.properties";

    @Override
    public void update(AnActionEvent e) {
        VirtualFile virtualFile = e.getData(DataKeys.VIRTUAL_FILE);
        if(virtualFile!=null&&virtualFile.getName().equals(FILE_NAME)){
            e.getPresentation().setEnabled(true);
            e.getPresentation().setVisible(true);
        }else {
            e.getPresentation().setEnabled(false);
            e.getPresentation().setVisible(false);
        }
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        try {
            Project project = e.getProject();
            VirtualFile virtualFile = e.getData(DataKeys.VIRTUAL_FILE);
            Properties properties = new Properties();
            properties.load(virtualFile.getInputStream());
            G.init(project,properties);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        ownActionPerformed(e);
    }

    public abstract  void ownActionPerformed(AnActionEvent e);
}
