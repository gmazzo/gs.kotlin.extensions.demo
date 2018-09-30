package org.test.koltintest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.test.koltintest.model.User;

import java.util.Objects;

public class UsersEditFragment extends Fragment {
    private static final String ARG_USER = "user";

    public static UsersEditFragment create() {
        return create(null);
    }

    public static UsersEditFragment create(@Nullable User user) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_USER, user);

        UsersEditFragment fragment = new UsersEditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    {
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_users_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState == null) {
            User user = getArguments().getParcelable(ARG_USER);

            if (user != null) {
                TextInputLayout name = view.findViewById(R.id.name);
                TextInputLayout profession = view.findViewById(R.id.profession);
                TextInputLayout age = view.findViewById(R.id.age);

                name.getEditText().setText(user.getName());
                profession.getEditText().setText(user.getProfession());
                age.getEditText().setText(Objects.toString(user.getAge(), null));
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.fragment_users_edit, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                if (validate()) {
                    // TODO perform save here

                    getFragmentManager().popBackStack();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean validate() {
        View view = getView();
        TextInputLayout name = view.findViewById(R.id.name);
        TextInputLayout profession = view.findViewById(R.id.profession);
        TextInputLayout age = view.findViewById(R.id.age);

        boolean result = validate(name, true);
        result &= validate(profession, result);
        // result &= validate(age, result);
        return result;
    }

    private boolean validate(TextInputLayout layout, boolean setFocus) {
        Editable value = layout.getEditText().getText();

        if (TextUtils.isEmpty(value)) {
            layout.setError(getText(R.string.err_field_required));

            if (setFocus) {
                layout.requestFocus();
            }
            return false;

        } else {
            layout.setErrorEnabled(false);
        }
        return true;
    }

}
