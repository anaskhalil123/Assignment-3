// Generated by view binder compiler. Do not edit!
package com.example.assignment_3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.assignment_3.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAddBookBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnAdd;

  @NonNull
  public final Button btnDelete;

  @NonNull
  public final EditText edAuthor;

  @NonNull
  public final EditText edName;

  @NonNull
  public final EditText edPrice;

  @NonNull
  public final EditText edYear;

  @NonNull
  public final RatingBar ratingBar2;

  @NonNull
  public final TextView textView;

  private ActivityAddBookBinding(@NonNull ConstraintLayout rootView, @NonNull Button btnAdd,
      @NonNull Button btnDelete, @NonNull EditText edAuthor, @NonNull EditText edName,
      @NonNull EditText edPrice, @NonNull EditText edYear, @NonNull RatingBar ratingBar2,
      @NonNull TextView textView) {
    this.rootView = rootView;
    this.btnAdd = btnAdd;
    this.btnDelete = btnDelete;
    this.edAuthor = edAuthor;
    this.edName = edName;
    this.edPrice = edPrice;
    this.edYear = edYear;
    this.ratingBar2 = ratingBar2;
    this.textView = textView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAddBookBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAddBookBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_add_book, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAddBookBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnAdd;
      Button btnAdd = ViewBindings.findChildViewById(rootView, id);
      if (btnAdd == null) {
        break missingId;
      }

      id = R.id.btnDelete;
      Button btnDelete = ViewBindings.findChildViewById(rootView, id);
      if (btnDelete == null) {
        break missingId;
      }

      id = R.id.edAuthor;
      EditText edAuthor = ViewBindings.findChildViewById(rootView, id);
      if (edAuthor == null) {
        break missingId;
      }

      id = R.id.edName;
      EditText edName = ViewBindings.findChildViewById(rootView, id);
      if (edName == null) {
        break missingId;
      }

      id = R.id.edPrice;
      EditText edPrice = ViewBindings.findChildViewById(rootView, id);
      if (edPrice == null) {
        break missingId;
      }

      id = R.id.edYear;
      EditText edYear = ViewBindings.findChildViewById(rootView, id);
      if (edYear == null) {
        break missingId;
      }

      id = R.id.ratingBar2;
      RatingBar ratingBar2 = ViewBindings.findChildViewById(rootView, id);
      if (ratingBar2 == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      return new ActivityAddBookBinding((ConstraintLayout) rootView, btnAdd, btnDelete, edAuthor,
          edName, edPrice, edYear, ratingBar2, textView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
