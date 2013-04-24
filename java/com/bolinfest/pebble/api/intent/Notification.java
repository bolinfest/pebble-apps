package com.bolinfest.pebble.api.intent;

import android.content.Intent;
import com.google.common.base.Preconditions;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.annotation.Nullable;

/**
 *
 */
public class Notification {

  public static final String ACTION = "com.getpebble.action.SEND_NOTIFICATION";

  private final String messageType;
  private final String sender;
  private final String notificationData;

  private Notification(String messageType, String sender, String notificationData) {
    this.messageType = messageType;
    this.sender = sender;
    this.notificationData = notificationData;
  }

  public Intent createIntent() {
    Intent intent = new Intent(ACTION);
    intent.putExtra("messageType", messageType);
    intent.putExtra("sender", sender);
    intent.putExtra("notificationData", notificationData);
    return intent;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private String messageType = "PEBBLE_ALERT";

    @Nullable
    private String sender;

    @Nullable
    private String notificationData = "{}";

    private Builder() {}

    public Notification build() {
      Preconditions.checkNotNull(sender, "sender must be set");
      return new Notification(messageType, sender, notificationData);
    }

    /**
     * @param messageType The only acceptable value documented on
     *     http://developer.getpebble.com/android/intents is {@code "PEBBLE_ALERT"}
     */
    public Builder setMessageType(String messageType) {
      this.messageType = Preconditions.checkNotNull(messageType);
      return this;
    }

    public Builder setSender(String sender) {
      this.sender = Preconditions.checkNotNull(sender);
      return this;
    }

    public Builder setNotificationData(String title, String body) {
      Preconditions.checkNotNull(title);
      Preconditions.checkNotNull(body);

      JsonObject json = new JsonObject();
      json.addProperty("title", title);
      json.addProperty("body", body);

      JsonArray array = new JsonArray();
      array.add(json);

      notificationData = array.toString();
      return this;
    }
  }
}
