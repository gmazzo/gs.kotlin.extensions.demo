package org.test.koltintest;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import org.test.koltintest.model.User;

import java.util.Objects;

public class UserViewHolder extends RecyclerView.ViewHolder {
    private final TextView name;
    private final TextView nameLabel;
    private final TextView profession;
    private final TextView professionLabel;
    private final TextView age;
    private final TextView ageLabel;

    public UserViewHolder(@NonNull View view) {
        super(view);

        this.name = itemView.findViewById(R.id.name);
        this.nameLabel = itemView.findViewById(R.id.nameLabel);
        this.profession = itemView.findViewById(R.id.profession);
        this.professionLabel = itemView.findViewById(R.id.professionLabel);
        this.age = itemView.findViewById(R.id.age);
        this.ageLabel = itemView.findViewById(R.id.ageLabel);
    }

    void bindUser(User user) {
        setText(name, nameLabel, user.getName());
        setText(profession, professionLabel, user.getProfession());
        setText(age, ageLabel, Objects.toString(user.getAge(), null));
    }

    private void setText(@NonNull TextView value, @NonNull TextView label, @Nullable CharSequence text) {
        value.setText(text);
        value.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE); // TODO this could be generic?
        label.setVisibility(value.getVisibility());
    }

}
