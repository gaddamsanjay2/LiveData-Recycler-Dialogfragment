package com.example.livedata;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MyFragmentDialog extends DialogFragment {

    MyViewModel viewModel;

    ArrayList<MyModel> datamodelList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.custom_dialog, container, false);

        viewModel = ViewModelProviders.of((FragmentActivity) getActivity()).get(MyViewModel.class);


        EditText ename = root.findViewById(R.id.dialog_edit1);
        EditText email = root.findViewById(R.id.dialog_edit2);

        Button load = root.findViewById(R.id.load);
        Button save = root.findViewById(R.id.save);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enameStr = ename.getText().toString();
                String emailStr = email.getText().toString();

                if (TextUtils.isEmpty(enameStr)) {
                    ename.setError("enter details");
                }
                if (TextUtils.isEmpty(emailStr)) {
                    email.setError("enter details");
                } else {
                    loadData();
                    datamodelList.add(new MyModel(enameStr,emailStr));
//                    datamodelList.add(emailStr);

                    viewModel.setMutableLiveData(datamodelList);

//                    getDialog().dismiss();



                    Toast.makeText(getActivity(), "Data saved", Toast.LENGTH_SHORT).show();


                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveData();

            }
        });

        return root;

    }

    @SuppressLint("NewApi")
    private void saveData() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);

        // creating a variable for editor to
//        // store data in shared preferences.
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // creating a new variable for gson.
        Gson gson = new Gson();
//        // getting data from gson and storing it in a string.
        String json = gson.toJson(datamodelList);
//        courseModalArrayList.add(new GridModel(courseNameEdt));
//        // below line is to save data in shared
//        // prefs in the form of string.
        editor.putString("courses", json);
//        // below line is to apply changes
//        // and save data in shared prefs.
        editor.apply();
//        // after saving data we are displaying a toast message.
        Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();
    }

    private void loadData() {

        // method to load arraylist from shared prefs
//        // initializing our shared prefs with name as
//        // shared preferences.
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
//
//        // creating a variable for gson.
        Gson gson = new Gson();
//
//        // below line is to get to string present from our
//        // shared prefs if not present setting it as null.
        String json = sharedPreferences.getString("courses", null);
//
//        // below line is to get the type of our array list.
        Type type = new TypeToken<ArrayList<MyModel>>() {
        }.getType();
//
//        // in below line we are getting data from gson
//        // and saving it to our array list
        datamodelList = gson.fromJson(json, type);
//        // checking below if the array list is empty or not
        if (datamodelList == null) {
//            // if the array list is empty
//            // creating a new array list.
            datamodelList = new ArrayList<>();

        }
    }
}


