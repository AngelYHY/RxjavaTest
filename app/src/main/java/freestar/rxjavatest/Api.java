package freestar.rxjavatest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by freestar on 2017/3/7 0007.
 */

public interface Api {
    @GET("http://112.124.9.133:8080/parking-app-admin-1.0/android/manager/adminVersion/")
    Observable<ResponseBody> downloadFile();

    //    @GET("http://112.124.9.133:8080/parking-app-admin-1.0/android/manager/adminVersion/")
//    @GET("http://gdown.baidu.com/data/wisegame/a5d81d9f9020059a/WiFiwannengyaochi_3105.apk")
    @GET
    Call<ResponseBody> download(@Url String url);
}
