package free.pdfdemo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by freestar on 2017/3/3.
 */

public interface DownLoad {
    //    @GET("http://www.yunmath.com/Files/160549df-48f7-487e-aa81-75b3b00de65a.pdf")
    @GET("http://www.tyyq.cn/xhsapp/download/a03790b7f27243eeada01537a2ce2f77.pdf")
    Call<ResponseBody> downloadTest();

    @GET("http://www.yunmath.com/Files/160549df-48f7-487e-aa81-75b3b00de65a.pdf")
    Observable<ResponseBody> download();

}
