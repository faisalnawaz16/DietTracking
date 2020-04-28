package com.example.dietapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class arrayListMeal extends ArrayAdapter<SetMeal> {
    private Activity context;
    private List<SetMeal> mealList;

    public arrayListMeal(Activity context,List<SetMeal> mealList){
        super(context,R.layout.activity_history,mealList);
        this.context=context;
        this.mealList=mealList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View mealListView=inflater.inflate(R.layout.activity_history,null,true);

        SetMeal meals = mealList.get(position);

        return mealListView;
    }
}
