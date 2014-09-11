package com.tagmycode.intellij;

import com.intellij.ide.passwordSafe.PasswordSafe;
import com.intellij.ide.passwordSafe.PasswordSafeException;
import com.intellij.openapi.project.Project;
import com.tagmycode.plugin.IPasswordKeyChain;
import com.tagmycode.plugin.exception.TagMyCodeGuiException;

public class PasswordKeyChain implements IPasswordKeyChain {

    private final PasswordSafe passwordSafe;
    private final Project project;
    private Class currentClass = PasswordKeyChain.class;

    public PasswordKeyChain(Project project) {
        this.project = project;
        passwordSafe = PasswordSafe.getInstance();
    }

    @Override
    public void saveValue(String key, String value) throws TagMyCodeGuiException {
        try {
            passwordSafe.storePassword(project, currentClass, key, value);
        } catch (Throwable e) {
            throwExceptionRelatedToPasswordManager(e);
        }
    }

    @Override
    public String loadValue(String key) throws TagMyCodeGuiException {
        String value = "";
        try {
            value = passwordSafe.getPassword(project, currentClass, key);
        } catch (Throwable e) {
            throwExceptionRelatedToPasswordManager(e);
        }
        return value;
    }

    @Override
    public void deleteValue(String key) throws TagMyCodeGuiException {
        try {
            passwordSafe.removePassword(project, currentClass, key);
        } catch (Throwable e) {
            throwExceptionRelatedToPasswordManager(e);
        }
    }

    private void throwExceptionRelatedToPasswordManager(Throwable e) throws TagMyCodeGuiException {
        String message = "Unable to access to the password manager";
        if (e instanceof PasswordSafeException) {
            message = e.getMessage();
        }
        throw new TagMyCodeGuiException(message);
    }
}
