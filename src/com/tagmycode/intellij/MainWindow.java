package com.tagmycode.intellij;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.tagmycode.plugin.Framework;

import javax.swing.*;

public class MainWindow implements ToolWindowFactory {
    private JPanel mainPanel;

    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        Framework framework = IntelliJUtils.getTagMyCodeProject(project).getFramework();

        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(framework.getMainWindow().getMainPanel(), "", false);
        toolWindow.getContentManager().addContent(content);
    }
}
