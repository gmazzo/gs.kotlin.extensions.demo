package org.test.koltintest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ShapeFragment extends Fragment {
    private final int layouts[] = {
            R.layout.fragment_first,
            R.layout.fragment_second,
            R.layout.fragment_third
    };

    private static final String ARG_INDEX = "index";

    public static ShapeFragment create() {
        return create(0);
    }

    public static ShapeFragment create(int index) {
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);

        ShapeFragment fragment = new ShapeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int index = getArguments().getInt(ARG_INDEX); // TODO código duplicado
        return inflater.inflate(layouts[index] /* */, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.setOnClickListener($ -> {
            int index = getArguments().getInt(ARG_INDEX); // TODO código duplicado
            int nextIndex = (index + 1) % layouts.length;
            String tag = "shape" + index;

            getFragmentManager().popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            getFragmentManager().beginTransaction() // TODO podría hacerse genérico
                    .replace(getId(), ShapeFragment.create(nextIndex))
                    .addToBackStack(tag)
                    .commit();
        });
    }

}
