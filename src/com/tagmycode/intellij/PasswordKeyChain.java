package com.tagmycode.intellij;

import com.intellij.credentialStore.CredentialAttributes;
import com.intellij.credentialStore.Credentials;
import com.intellij.ide.passwordSafe.PasswordSafe;
import com.intellij.ide.passwordSafe.PasswordSafeException;
import com.intellij.openapi.project.Project;
import com.tagmycode.plugin.IPasswordKeyChain;
import com.tagmycode.plugin.exception.TagMyCodeGuiException;
import org.jetbrains.annotations.NotNull;

public class PasswordKeyChain implements IPasswordKeyChain {

    private static final String TAGMYCODE_SERVICE_NAME = "tagmycode_plugin";
    private final Project project;
    private final PasswordSafe passwordSafe;

    private final Class currentClass = PasswordKeyChain.class;

    public PasswordKeyChain(Project project) {
        this.project = project;
        passwordSafe = PasswordSafe.getInstance();
    }

    @Override
    public void saveValue(String key, String value) throws TagMyCodeGuiException {
        try {
            CredentialAttributes credentialAttributes = createCredentialAttributes(key);
            Credentials credentials = new Credentials(key, value);

            PasswordSafe.getInstance().set(credentialAttributes, credentials);
        } catch (NoClassDefFoundError e) {
            saveValueOldWay(key, value);
        }
    }

    @Override
    public String loadValue(String key) throws TagMyCodeGuiException {
        try {
            CredentialAttributes credentialAttributes = createCredentialAttributes(key);
            Credentials credentials = PasswordSafe.getInstance().get(credentialAttributes);

            return credentials != null ? credentials.getPasswordAsString() : "";
        } catch (NoClassDefFoundError e) {
            return loadValueOldWay(key);
        }
    }

    @Override
    public void deleteValue(String key) throws TagMyCodeGuiException {
        saveValue(key, null);
    }

    @NotNull
    private CredentialAttributes createCredentialAttributes(String key) {
        return new CredentialAttributes(TAGMYCODE_SERVICE_NAME + "_" + key);
    }


    public void saveValueOldWay(String key, String value) throws TagMyCodeGuiException {
        try {
            passwordSafe.storePassword(project, currentClass, key, value);
        } catch (Throwable e) {
            throwExceptionRelatedToPasswordManager(e);
        }
    }

    public String loadValueOldWay(String key) throws TagMyCodeGuiException {
        String value = "";
        try {
            value = passwordSafe.getPassword(project, currentClass, key);
        } catch (Throwable e) {
            throwExceptionRelatedToPasswordManager(e);
        }
        return value;
    }

    private void throwExceptionRelatedToPasswordManager(Throwable e) throws TagMyCodeGuiException {
        String message = "Unable to access to the password manager";
        if (e instanceof PasswordSafeException) {
            message = e.getMessage();
        }
        throw new TagMyCodeGuiException(message);
    }

}
