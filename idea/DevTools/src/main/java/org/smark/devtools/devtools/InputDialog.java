package org.smark.devtools.devtools;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class InputDialog extends DialogWrapper {
    protected InputDialog(@Nullable Project project) {
        super(project);
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel panel = new JPanel();
        JTextField input = new JTextField();
        input.setEnabled(true);
        panel.add(input);
        return panel;
    }
}
