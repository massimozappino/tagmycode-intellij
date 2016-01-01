package com.tagmycode.intellij;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.tagmycode.plugin.Framework;
import org.jetbrains.annotations.NotNull;


public class MainWindow implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        Framework framework = IntelliJUtils.getTagMyCodeProject(project).getFramework();

        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(framework.getMainWindow().getMainComponent(), "", false);
        toolWindow.getContentManager().addContent(content);
    }
}
