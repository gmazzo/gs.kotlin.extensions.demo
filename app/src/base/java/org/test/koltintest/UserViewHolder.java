package org.test.koltintest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import org.test.koltintest.model.User;

import java.util.Objects;

public class UserViewHolder extends RecyclerView.ViewHolder {

    public UserViewHolder(@NonNull View view) {
        super(view);
    }

    void bindUser(User user) {
        setText(itemView.findViewById(R.id.name), itemView.findViewById(R.id.nameLabel), user.getName());
        setText(itemView.findViewById(R.id.profession), itemView.findViewById(R.id.professionLabel), user.getProfession());
        setText(itemView.findViewById(R.id.age), itemView.findViewById(R.id.ageLabel), Objects.toString(user.getAge(), null));
    }

    private void setText(TextView value, TextView label, CharSequence text) {
        value.setText(text);
        value.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE); // TODO this could be generic?
        label.setVisibility(value.getVisibility());
    }

}
