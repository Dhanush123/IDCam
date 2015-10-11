package com.x10host.dhanushpatel.idcam;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by chapatel on 10/10/15.
 */
public interface AlchemyService {
   // @GET("/calls/image/ImageGetRankedImageFaceTags?apikey=fe54b0353062f121819ab25e129ca70369605e81&image={image}&imagePostMode=not-raw&outputMode=json&knowledgeGraph=1")
   @GET("/calls/url/URLGetRankedImageFaceTags?url=https://www.facebook.com/photo.php?fbid=834381609951080&set=a.115429005179681.19154.100001377455784&type=3&theater&apikey="+Constants.KEY+"&outputMode=json")
    //public Call<JSONResponse> getData();
   public Call<ImageData> getData();
    //Call<Response> listRepos(@Path("user") String user);
}