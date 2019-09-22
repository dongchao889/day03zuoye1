package comd.example.lenovo.day03zuoye1;

import java.util.ArrayList;

import comd.example.lenovo.day03zuoye1.bean.DatasBean;

/**
 * Created by lenovo on 2019/9/20.
 */

public interface CallBack {
    void suesscc(ArrayList<DatasBean> li);
    void fuil(String str);
}
