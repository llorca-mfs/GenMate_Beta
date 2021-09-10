package com.mobdeve.s17.llorca.madrid.genmate_beta.dao;

import com.mobdeve.s17.llorca.madrid.genmate_beta.model.QrFriend;

import java.util.ArrayList;

public interface QrListDAO {

    long addQrFriend(QrFriend qrFriend);
    ArrayList<QrFriend> getQrFriends();
    QrFriend getQrFriend(int qrFriendId);
}
