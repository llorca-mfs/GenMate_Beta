package com.mobdeve.s17.llorca.madrid.genmate_beta.ui.farm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Farmable {

    private String itemname = "";

    private int imageDrawableId;
    private ArrayList<String> daysAvailable = new ArrayList<String>();
    public Farmable(String itemname, int imageDrawableId, ArrayList<String> daysAvailable) {
        this.itemname = itemname;
        this.imageDrawableId = imageDrawableId;
        this.daysAvailable = daysAvailable;
    }

    public ArrayList<String> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(ArrayList<String> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }


    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public int getImageDrawableId() {
        return imageDrawableId;
    }

    public void setImageDrawableId(int imageDrawableId) {
        this.imageDrawableId = imageDrawableId;
    }

}
