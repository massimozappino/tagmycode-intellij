package com.tagmycode.intellij;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.tagmycode.sdk.model.Snippet;

public class CreateSnippetAction extends AnAction {

    @Override
    public void actionPerformed(final AnActionEvent e) {
        Project project = e.getProject();
        TagMyCodeProject tagMyCodeProject = IntelliJUtils.getTagMyCodeProject(project);
        if (!tagMyCodeProject.getFramework().canOperate()) {
            return;
        }
        final Editor editor = e.getData(CommonDataKeys.EDITOR);

        Snippet snippet = new Snippet();
        assert editor != null;
        snippet.setTitle(getFile(editor.getDocument()).getName());
        snippet.setCode(getCode(editor));
        String mimeType = "text/plain";
        tagMyCodeProject.getFramework().showSnippetDialog(snippet, mimeType);
    }

    private VirtualFile getFile(Document document) {
        return FileDocumentManager.getInstance().getFile(document);
    }

    private String getCode(Editor editor) {
        String code;
        final Document document = editor.getDocument();
        final SelectionModel selectionModel = editor.getSelectionModel();

        if (!selectionModel.hasSelection()) {
            code = document.getText();
        } else {
            code = selectionModel.getSelectedText();
        }
        return code;
    }

}
