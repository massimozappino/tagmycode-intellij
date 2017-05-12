package com.tagmycode.intellij;


import com.intellij.openapi.vfs.VirtualFile;
import com.tagmycode.sdk.FileNameToLanguage;
import com.tagmycode.sdk.model.Language;
import com.tagmycode.sdk.model.LanguagesCollection;
import org.jetbrains.annotations.NotNull;


public class IntelliJFileToLanguage {
    private LanguagesCollection languages;

    IntelliJFileToLanguage(LanguagesCollection languages) {
        this.languages = languages;
    }

    Language find(VirtualFile virtualFile) {
        String intellijCode = virtualFile.getFileType().getName().toLowerCase();

        Language foundLanguage = languages.findByCode(extractCode(intellijCode));
        if (foundLanguage != null && foundLanguage.getCode().equals(FileNameToLanguage.TEXT)) {
            foundLanguage = languages.findByFileName(virtualFile.getName());
        }
        return foundLanguage;
    }

    @NotNull
    private String extractCode(String intellijCode) {
        String code;
        switch (intellijCode) {
            case "html":
                code = "html";
                break;
            case "idea_module":
            case "xhtml":
                code = "xml";
                break;
            case "plain_text":
                code = "text";
                break;
            case "java":
                code = "java";
                break;
            case "javascript":
            case "coffee":
                code = "javascript";
                break;
            case "css":
            case "scss":
                code = "css";
                break;
            case "bash":
                code = "bash";
                break;
            default:
                code = intellijCode;
        }
        return code;
    }
}
