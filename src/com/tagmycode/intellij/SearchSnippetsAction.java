package com.tagmycode.intellij;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.tagmycode.plugin.gui.IDocumentInsertText;

public class SearchSnippetsAction extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        final Project project = e.getProject();
        TagMyCodeProject tagMyCodeProject = IntelliJUtils.getTagMyCodeProject(project);
        if (!tagMyCodeProject.getFramework().canOperate()) {
            return;
        }

        IDocumentInsertText documentUpdate = null;
        final Editor editor = e.getData(CommonDataKeys.EDITOR);
        if (editor != null) {
            documentUpdate = createDocumentUpdate(project, editor);
        }
       // tagMyCodeProject.getFramework().showSearchDialog(documentUpdate);
    }

    private IDocumentInsertText createDocumentUpdate(final Project project, final Editor editor) {
        return new IDocumentInsertText() {
            @Override
            public void insertText(final String text) {
                insertSnippetCommand(text, project, editor);
            }
        };
    }

    private void insertSnippetCommand(final String text, final Project project, final Editor editor) {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {
                CommandProcessor commandProcessor = CommandProcessor.getInstance();
                commandProcessor.executeCommand(
                        project, new Runnable() {
                            public void run() {
                                ApplicationManager.getApplication().runWriteAction(new Runnable() {
                                    @Override
                                    public void run() {
                                        replaceSelectedText(text, editor);
                                    }
                                });
                            }
                        },
                        "Insert snippet", null
                );
            }
        });
    }

    private void replaceSelectedText(String text, Editor editor) {
        int[] selection = getSelectionStartEnd(editor);
        Document document = editor.getDocument();
        document.replaceString(selection[0], selection[1], cleanText(text));
    }

    private String cleanText(String text) {
        return StringUtil.replace(text, "\r", "");
    }

    private int[] getSelectionStartEnd(Editor editor) {
        final SelectionModel selectionModel = editor.getSelectionModel();
        if (!selectionModel.hasSelection()) {
            int offset = editor.getCaretModel().getOffset();
            return new int[]{offset, offset};
        } else {
            return new int[]{selectionModel.getSelectionStart(), selectionModel.getSelectionEnd()};
        }
    }
}
