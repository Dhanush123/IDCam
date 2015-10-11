package com.x10host.dhanushpatel.idcam;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by chapatel on 10/10/15.
 */
public class ImageData {
    @SerializedName("status") String requestStatus;
    String url;
//    String transactionCharged;
    ArrayList<Face> imageFaces;
//    public ImageData(){
//
//    }
}
