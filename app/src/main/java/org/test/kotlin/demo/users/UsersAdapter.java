package org.test.kotlin.demo.users;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.test.kotlin.demo.R;
import org.test.kotlin.demo.model.User;

import java.util.function.Consumer;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static org.test.Utils.asDate;

public class UsersAdapter extends RecyclerView.Adapter<UsersViewHolder> {
    private final User users[] = {
            new User(1, "Guillermo Mazzola", "Software Developer", asDate("19/12/1985")),
            new User(2, "German Bravo Rojas", "Android Developer", null),
            new User(3, "Konstantin Portnov", "iOS Developer", null)
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
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_users_item, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        User user = users[position];

        holder.bindUser(user);
        holder.itemView.setOnClickListener($ -> selectionListener.accept(user));
    }

    @Override
    public void onViewRecycled(@NonNull UsersViewHolder holder) {
        super.onViewRecycled(holder);

        holder.itemView.setOnClickListener(null);
    }

}
