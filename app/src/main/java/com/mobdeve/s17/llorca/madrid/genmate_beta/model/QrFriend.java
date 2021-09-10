package com.mobdeve.s17.llorca.madrid.genmate_beta.model;

public class QrFriend {
    private int id = -1;
    private String ign = "";
    private String uid = "";

    public int getId() {return this.id;}
    public void setId(int id) {this.id = id;}

    public String getIgn() { return this.ign; }
    public void setIgn(String ign) { this.ign = ign; }

    public String getUid() { return this.uid; }
    public void setUid(String uid) { this.uid = uid; }

    public QrFriend(int id, String ign, String uid){
        this.id = id;
        this.ign = ign;
        this.uid = uid;
    }

    public QrFriend(){}
}
