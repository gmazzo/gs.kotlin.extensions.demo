package org.test.koltintest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.test.koltintest.model.User;

import java.util.function.Consumer;

import static org.test.Utils.asDate;

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private final User users[] = {
            new User(1, "Guillermo Mazzola", "Software Developer", asDate("19/12/1985")),
            new User(2, "Pablo Araya", "Android Developer", null),
            new User(3, "Armando Picón", "Android Developer", null)
    };
    private final Consumer<User> selectionListener;

    public UsersAdapter(Consumer<User> selectionListener) {
        this.selectionListener = selectionListener;
    }

    @Override
    public int getItemCount() {
        return users.length;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_users_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users[position];

        holder.bindUser(user);
        holder.itemView.setOnClickListener($ -> selectionListener.accept(user));
    }

    @Override
    public void onViewRecycled(@NonNull UserViewHolder holder) {
        super.onViewRecycled(holder);

        holder.itemView.setOnClickListener(null);
    }

}
