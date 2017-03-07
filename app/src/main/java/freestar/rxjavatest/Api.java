package freestar.rxjavatest;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import rx.Observable;

/**
 * Created by freestar on 2017/3/7 0007.
 */

public interface Api {
    @Streaming
    @GET
    Observable<ResponseBody> downloadFile();
}
