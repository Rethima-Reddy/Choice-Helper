package com.example.choicehelper;

import java.util.ArrayList;

// Step-06: Create a Model with array list in it and implement the singleton
public class ChoiceModel {

    public static class MyInfo {
        public String item;

        public MyInfo(String item) {
            this.item = item;
        }
    }


    // singleton implementation.
    private static ChoiceModel singleton = null;

    public static ChoiceModel getSingleton() {
        if(singleton == null) {
            singleton = new ChoiceModel();
        }
        return singleton;
    }

    // create an array.
    public ArrayList<MyInfo> choiceList;

    // constructor of this class(ChoiceModel)
    public ChoiceModel() {
        this.choiceList = new ArrayList<MyInfo>();
        loadModel();
    }

    private void loadModel() {
        // Add items into the array list.
        choiceList.add(new MyInfo("Go to beach"));
        choiceList.add(new MyInfo("Read a book"));
        choiceList.add(new MyInfo("Eat a snack"));
        choiceList.add(new MyInfo("Complete the assignments"));
        choiceList.add(new MyInfo("Get read for tests"));
        choiceList.add(new MyInfo("Go to gym"));
        choiceList.add(new MyInfo("Shop new clothes"));
        choiceList.add(new MyInfo("Buy gifts to siblings"));
        choiceList.add(new MyInfo("Buy a new car"));
    }

    public void reset() {
        choiceList.clear();
        loadModel();
    }

    public void clear(){
        choiceList.clear();
    }
}
