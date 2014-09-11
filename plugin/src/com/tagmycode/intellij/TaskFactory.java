package com.tagmycode.intellij;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task;
import com.tagmycode.plugin.AbstractTaskFactory;
import org.jetbrains.annotations.NotNull;


public class TaskFactory extends AbstractTaskFactory {
    private TagMyCodeProject tagMyCodeProject;

    public TaskFactory(TagMyCodeProject project) {
        this.tagMyCodeProject = project;
    }

    @Override
    public void create(final Runnable runnable, final String title) {

        ApplicationManager.getApplication().invokeLater(new Runnable() {
            public void run() {
                new Task.Backgroundable(tagMyCodeProject.getProject(), title, true) {
                    public void run(@NotNull final ProgressIndicator indicator) {
                        runnable.run();
                    }
                }.queue();
            }
        });
    }

}
