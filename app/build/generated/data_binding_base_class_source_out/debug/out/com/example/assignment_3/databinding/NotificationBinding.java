// Generated by view binder compiler. Do not edit!
package com.example.assignment_3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.assignment_3.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class NotificationBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final ImageView appLogo;

  @NonNull
  public final TextView message;

  @NonNull
  public final TextView title;

  private NotificationBinding(@NonNull RelativeLayout rootView, @NonNull ImageView appLogo,
      @NonNull TextView message, @NonNull TextView title) {
    this.rootView = rootView;
    this.appLogo = appLogo;
    this.message = message;
    this.title = title;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static NotificationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static NotificationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.notification, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static NotificationBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.app_logo;
      ImageView appLogo = ViewBindings.findChildViewById(rootView, id);
      if (appLogo == null) {
        break missingId;
      }

      id = R.id.message;
      TextView message = ViewBindings.findChildViewById(rootView, id);
      if (message == null) {
        break missingId;
      }

      id = R.id.title;
      TextView title = ViewBindings.findChildViewById(rootView, id);
      if (title == null) {
        break missingId;
      }

      return new NotificationBinding((RelativeLayout) rootView, appLogo, message, title);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
