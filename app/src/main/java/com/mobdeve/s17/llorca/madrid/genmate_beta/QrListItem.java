package com.mobdeve.s17.llorca.madrid.genmate_beta;

public class QrListItem {
    private String ign = "";
    private String uid = "";

    public String getIgn() { return this.ign; }
    public void setIgn(String ign) { this.ign = ign; }

    public String getUid() { return this.uid; }
    public void setUid(String uid) { this.uid = uid; }

    public QrListItem(String ign, String uid){
        this.ign = ign;
        this.uid = uid;
    }
}
