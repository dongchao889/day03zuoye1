package comd.example.lenovo.day03zuoye1.view;

import java.util.ArrayList;


import comd.example.lenovo.day03zuoye1.bean.DatasBean;

/**
 * Created by lenovo on 2019/9/20.
 */

public interface DataView {
    void addData(ArrayList<DatasBean> list);
    void showToast(String str);
}
