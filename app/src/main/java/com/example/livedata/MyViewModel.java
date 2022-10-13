package com.example.livedata;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MyViewModel extends ViewModel {


    ArrayList<MyModel> datamodelList=new ArrayList<>();

    MutableLiveData<ArrayList<MyModel>>mutableLiveData;

    public MyViewModel(){

        mutableLiveData=new MutableLiveData<>();

    }

    public MutableLiveData<ArrayList<MyModel>> getMutableLiveData(){

        return mutableLiveData;


    }

    public void setMutableLiveData(ArrayList<MyModel> datamodelList)
    {
            mutableLiveData.setValue(datamodelList);


        }

    }








