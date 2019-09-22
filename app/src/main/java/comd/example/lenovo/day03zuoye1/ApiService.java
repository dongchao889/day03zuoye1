package comd.example.lenovo.day03zuoye1;

import comd.example.lenovo.day03zuoye1.bean.Bean;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by lenovo on 2019/9/20.
 */

public interface ApiService {
    String uri="http://static.owspace.com/";
    @GET("?c=api&a=getList&page_id=0")
    Observable<Bean> getdata();
}
