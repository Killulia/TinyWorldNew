package com.kingwag.tinyworld.view.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kingwag.tinyworld.R;

/**
 * Created by kingwag on 2016/12/20.
 */

public class ShoppingLoginedFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopping_has_login, container, false);
        return view;
    }
}
