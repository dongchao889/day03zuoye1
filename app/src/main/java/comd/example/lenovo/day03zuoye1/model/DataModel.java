package comd.example.lenovo.day03zuoye1.model;

import java.util.ArrayList;
import java.util.List;

import comd.example.lenovo.day03zuoye1.ApiService;
import comd.example.lenovo.day03zuoye1.BaseApp;
import comd.example.lenovo.day03zuoye1.CallBack;
import comd.example.lenovo.day03zuoye1.bean.Bean;
import comd.example.lenovo.day03zuoye1.bean.DatasBean;
import comd.example.lenovo.day03zuoye1.db.DatasBeanDao;
import comd.example.lenovo.day03zuoye1.persenter.DataPer;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 2019/9/20.
 */

public class DataModel {
    public void getData(final CallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.uri)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Observable<Bean> observable = retrofit.create(ApiService.class).getdata();
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        ArrayList<DatasBean> datas = (ArrayList<DatasBean>) bean.getDatas();
                        callBack.suesscc(datas);
                        callBack.fuil(bean.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.fuil(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void insert(CallBack callBack, DatasBean datasBean) {
        DatasBeanDao dao = BaseApp.getInstance().getDaoSession().getDatasBeanDao();
        dao.insert(datasBean);
        callBack.fuil("插入成功");
    }

    public void delete(CallBack callBack, DatasBean datasBean) {
        DatasBeanDao dao = BaseApp.getInstance().getDaoSession().getDatasBeanDao();
        dao.delete(datasBean);
        callBack.fuil("取消成功");
    }
}
