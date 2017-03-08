package free.pdfdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener {

    private PDFView mPdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPdfView = (PDFView) findViewById(R.id.pdf);
        goOne();
//        goTwo();

    }

    private void goTwo() {
        provideRetrofitService().download()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseBody response) {
                        mPdfView.fromStream(response.byteStream())
                                .defaultPage(0)
                                .onPageChange(MainActivity.this)
                                .enableAnnotationRendering(true)
                                .onLoad(MainActivity.this)
                                .swipeHorizontal(true)
                                .scrollHandle(new DefaultScrollHandle(MainActivity.this))
                                .load();
                    }
                });
    }

    private void goOne() {
        Call<ResponseBody> call = provideRetrofitService().downloadTest();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                mPdfView.fromStream(response.body().byteStream())
                        .defaultPage(0)
                        .onPageChange(MainActivity.this)
                        .enableAnnotationRendering(true)
                        .onLoad(MainActivity.this)
                        .swipeHorizontal(true)
                        .scrollHandle(new DefaultScrollHandle(MainActivity.this))
                        .load();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    DownLoad provideRetrofitService() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(2, TimeUnit.SECONDS);

        //String baseUrl = "https://api.github.com/";
        String baseUrl = "http://www.yunmath.com:8012/api/";
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .baseUrl(baseUrl)
                .build();
        return retrofit.create(DownLoad.class);
    }

    @Override
    public void onPageChanged(int page, int pageCount) {

    }

    @Override
    public void loadComplete(int nbPages) {

    }
}
