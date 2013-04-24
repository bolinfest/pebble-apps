package com.bolinfest.pebble.demo.notification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.bolinfest.pebble.R;
import com.bolinfest.pebble.api.intent.Notification;

/**
 * Activity that has a text box and a send button to send a notification to your Pebble.
 */
public class SenderActivity extends Activity {

  private EditText titleField;
  private EditText bodyField;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.simplesender);
    titleField = (EditText) findViewById(R.id.title);
    bodyField = (EditText) findViewById(R.id.body);
  }

  @SuppressWarnings("unused")
  public void onSendPressed(View view) {
    Notification.Builder builder = Notification.builder();
    builder.setSender("TEST APP");

    builder.setNotificationData(
        titleField.getText().toString().trim(),
        bodyField.getText().toString().trim());

    Intent intent = builder.build().createIntent();
    sendBroadcast(intent);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    titleField = null;
    bodyField = null;
  }
}
