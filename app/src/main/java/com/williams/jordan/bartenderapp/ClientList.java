package com.williams.jordan.bartenderapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ClientList extends Fragment {
    private ArrayList<String> mList = new ArrayList();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listofclients,container,false);

        ListView listView = view.findViewById(R.id.list);
        if (getArguments() != null) {
            String text = getArguments().getString("name");
            Storage.add(text);

        }
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,Storage.getmArrayList());
        listView.setAdapter(arrayAdapter);
        return view;
    }
}
