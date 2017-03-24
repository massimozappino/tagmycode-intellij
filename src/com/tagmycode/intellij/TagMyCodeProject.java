package com.tagmycode.intellij;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.util.ui.UIUtil;
import com.tagmycode.plugin.Framework;
import com.tagmycode.plugin.FrameworkConfig;
import com.tagmycode.plugin.gui.SyntaxSnippetEditor;
import com.tagmycode.sdk.DbService;
import com.tagmycode.sdk.SaveFilePath;
import com.tagmycode.sdk.authentication.TagMyCodeApiProduction;
import com.tagmycode.sdk.exception.TagMyCodeException;
import org.jetbrains.annotations.NotNull;

import java.awt.Frame;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.SQLException;

public class TagMyCodeProject implements ProjectComponent {
    private Project project;
    private Framework framework;

    public TagMyCodeProject(Project project) {
        this.project = project;
    }

    @Override
    public void initComponent() {
    }

    Framework getFramework() {
        if (framework == null) {
            try {
                initFramework();
            } catch (SQLException | IOException e) {
                framework.logError(e);
            }
        }
        return framework;
    }

    private void initFramework() throws SQLException, IOException {
        DbService dbService = new DbService(new SaveFilePath(getOrCreateNamespace()));
        FrameworkConfig frameworkConfig = new FrameworkConfig(new PasswordKeyChain(project), dbService, new MessageManager(project), new TaskFactory(this), getMainFrame());
        framework = new Framework(new TagMyCodeApiProduction(), frameworkConfig, new Secret());
        try {
            framework.start();
        } catch (TagMyCodeException e) {
            throw new RuntimeException(e);
        }

        configureTheme();
    }

    private void configureTheme() {
        if (UIUtil.isUnderDarcula()) {
            SyntaxSnippetEditor.setThemeDark();
        }
    }

    @NotNull
    private String getOrCreateNamespace() {
        IntelliJProperties intelliJProperties = new IntelliJProperties();
        String profile = intelliJProperties.read("profile");
        if (profile.length() == 0) {
            SecureRandom random = new SecureRandom();
            profile = new BigInteger(130, random).toString(32);
            intelliJProperties.write("profile", profile);
            Framework.LOGGER.info("profile: " + profile);
            intelliJProperties.write("profile", profile);
        }
        return "intellij-" + profile;
    }

    private Frame getMainFrame() {
        return WindowManager.getInstance().getFrame(project);
    }

    public void disposeComponent() {
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
