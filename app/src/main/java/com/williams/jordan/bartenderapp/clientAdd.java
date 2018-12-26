package com.williams.jordan.bartenderapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class clientAdd extends Fragment {
    private Button mButton;
    private NavigationView navigationView;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.makeclient,container,false);
        mButton = view.findViewById(R.id.addClient);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check of apploication is connected to internet before sending data to list fragment

                    if (isConnected()) {


                        new AlertDialog.Builder(getContext())
                                .setTitle("title")
                                .setMessage("Are you sure you want to add this client")
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        EditText name = view.findViewById(R.id.clientName);
                                        EditText amt = view.findViewById(R.id.tabAmt);


                                        Bundle bundle = new Bundle();
                                        bundle.putString("name", name.getText().toString());
                                        bundle.putString("amount", amt.getText().toString());

                                        ClientList fragment = new ClientList();

                                        if (bundle != null) {
                                            fragment.setArguments(bundle);
                                        }

                                        //chnage nav list selection to client add
                                        View view1 = inflater.inflate(R.layout.activity_main_action,container,false);
                                        navigationView = view1.findViewById(R.id.nav_view);
                                        navigationView.setCheckedItem(R.id.lister);

                                        FragmentManager fragmentManager = getFragmentManager();
                                        FragmentTransaction ft = fragmentManager.beginTransaction();
                                        ft.replace(R.id.fragment_container, fragment);
                                        ft.addToBackStack("");
                                        ft.commit();
                                    }})
                                .setNegativeButton(android.R.string.no, null).show();





                    }
                    else {
                        Toast.makeText(getContext(), "your not connected to the internet", Toast.LENGTH_LONG).show();
                    }
            }
        });

        return view;
    }
    public boolean isConnected()
    {
        String command = "ping -c 1 google.com";
        try {
            return (Runtime.getRuntime().exec (command).waitFor() == 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


}
