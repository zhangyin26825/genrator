package com.maqv.code.generator.file;

import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiPackage;
import com.maqv.code.generator.G;
import com.maqv.code.generator.PropertiesKey;
import com.maqv.code.generator.file.config.PackageConfig;

/**
 * @author zhangyin
 * @create 2019-12-23 16:43
 **/
public class CheckPackageExistUtils {


    public  static void check(){
        for (PackageConfig packageConfig : PackageConfig.values()) {
            if(packageConfig.equals(PackageConfig.Controller)){
                return;
            }

            PropertiesKey propertiesKey = packageConfig.getPropertiesKey();
            String properties = G.getProperties(propertiesKey);
            PsiPackage aPackage = G.findPackage(properties);
            if (aPackage == null) {
                String message = properties + "所对应的包并不存在，请创建包再生成代码";
                Messages.showMessageDialog(G.getProject(), message, "错误的包配置", Messages.getErrorIcon());
                throw new RuntimeException(message);
            }
        }
    }
}
