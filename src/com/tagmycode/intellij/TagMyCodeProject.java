package com.tagmycode.intellij;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.WindowManager;
import com.tagmycode.plugin.Framework;
import com.tagmycode.plugin.FrameworkConfig;
import com.tagmycode.sdk.authentication.TagMyCodeApiProduction;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class TagMyCodeProject implements ProjectComponent {
    private Project project;
    private Framework framework;

    public TagMyCodeProject(Project project) {
        this.project = project;
    }

    @Override
    public void initComponent() {
    }

    public Framework getFramework()
    {
        if (framework == null) {
            initFramework();
        }
        return framework;
    }

    private void initFramework() {
        FrameworkConfig frameworkConfig = new FrameworkConfig(new PasswordKeyChain(project), new Storage(), new MessageManager(project), new TaskFactory(this), getMainFrame());
        framework = new Framework(new TagMyCodeApiProduction(), frameworkConfig, new Secret());
    }

    private Frame getMainFrame() {
        return WindowManager.getInstance().getFrame(project);
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "TagMyCodeProject";
    }

    public void projectOpened() {
        // called when project is opened
    }

    public void projectClosed() {
        // called when project is being closed
    }

    public Project getProject() {
        return project;
    }


}
