package org.smark.devtools.devtools;

import com.intellij.notification.*;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.apache.commons.collections.map.HashedMap;

import javax.swing.*;
import java.io.IOException;
import java.util.Map;

public class SoAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        /**
         * d=https://www.baidu.com/s?wd=
         * 	3=https://www.so.com/s?q=
         * 	b=http://www.bing.com/search?q=
         * 	g=https://www.google.com/#q=
         * 	s=http://stackoverflow.com/search?q=
         * 	y=https://www.baidu.com/s?wd=
         * 	m=http://mvnrepository.com/search?q=
         * 	w=http://www.wolframalpha.com/input/?i=
         */
        Map<String,String> engineMap = new HashedMap();
        engineMap.put("g","https://www.google.com/#q=");
        engineMap.put("3","https://www.so.com/s?q=");
        engineMap.put("b","http://www.bing.com/search?q=");
        engineMap.put("s","http://stackoverflow.com/search?q=");
        engineMap.put("d","https://www.baidu.com/s?wd=");
        engineMap.put("m","http://mvnrepository.com/search?q=");
        engineMap.put("w","http://www.wolframalpha.com/input/?i=");

        String v = JOptionPane.showInputDialog(null,
                "","",JOptionPane.QUESTION_MESSAGE);
        if (null==v)return;
        String engineKey = v.substring(0,1);
        String keyword = v.substring(1,v.length());
        String engine = engineMap.get(engineKey);
        if (engine==null) {
            engine = engineMap.get("g");
            keyword = v;
        }

        try {

            String cmd = "cmd /c start "+engine+keyword.trim();
            System.err.println(cmd);
            Runtime.getRuntime().exec(cmd);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
