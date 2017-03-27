package com.tagmycode.intellij;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.tagmycode.plugin.IMessageManager;

public class MessageManager implements IMessageManager {
    @Override
    public void error(String message) {
        Notifications.Bus.notify(new Notification("TagMyCode", "TagMyCode", message, NotificationType.ERROR));
    }
}
