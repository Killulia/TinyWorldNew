package com.kingwag.tinyworld.view.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.kingwag.tinyworld.R;
import com.kingwag.tinyworld.view.view.activity.MyFragment_LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
/**
 * 购物车账号已登录Fragment
 */

public class ShoppingNoneFragment extends Fragment {

    private Button btLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View shopView = inflater.inflate(R.layout.shopping_none_login, container, false);
        btLogin = (Button) shopView.findViewById(R.id.shopping_login);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyFragment_LoginActivity.class);
                startActivity(intent);
            }
        });
        return shopView;
    }

}
