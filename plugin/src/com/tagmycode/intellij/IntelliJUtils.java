package com.tagmycode.intellij;


import com.intellij.openapi.project.Project;

public class IntelliJUtils {
    public static TagMyCodeProject getTagMyCodeProject(Project project) {
        return project.getComponent(TagMyCodeProject.class);
    }
}
