package com.tagmycode.intellij;

import com.intellij.credentialStore.CredentialAttributes;
import com.intellij.credentialStore.Credentials;
import com.intellij.ide.passwordSafe.PasswordSafe;
import com.tagmycode.plugin.IPasswordKeyChain;
import org.jetbrains.annotations.NotNull;

public class PasswordKeyChain implements IPasswordKeyChain {

    private static final String TAGMYCODE_SERVICE_NAME = "tagmycode_plugin";

    @Override
    public void saveValue(String key, String value) {
        CredentialAttributes credentialAttributes = createCredentialAttributes(key);
        Credentials credentials = new Credentials(key, value);

        PasswordSafe.getInstance().set(credentialAttributes, credentials);
    }

    @Override
    public String loadValue(String key) {
        CredentialAttributes credentialAttributes = createCredentialAttributes(key);
        Credentials credentials = PasswordSafe.getInstance().get(credentialAttributes);

        return credentials != null ? credentials.getPasswordAsString() : "";
    }

    @Override
    public void deleteValue(String key) {
        saveValue(key, null);
    }

    @NotNull
    private CredentialAttributes createCredentialAttributes(String key) {
        return new CredentialAttributes(TAGMYCODE_SERVICE_NAME + "_" + key);
    }
}
