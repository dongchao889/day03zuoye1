package comd.example.lenovo.day03zuoye1.persenter;

import java.util.ArrayList;

import comd.example.lenovo.day03zuoye1.CallBack;
import comd.example.lenovo.day03zuoye1.bean.DatasBean;
import comd.example.lenovo.day03zuoye1.model.DataModel;
import comd.example.lenovo.day03zuoye1.view.DataView;

/**
 * Created by lenovo on 2019/9/20.
 */

public class DataPer implements CallBack{
    private DataView dataView;
    private DataModel dataModel;

    public DataPer(DataView dataView) {
        this.dataView = dataView;
        this.dataModel=new DataModel();
    }

    @Override
    public void suesscc(ArrayList<DatasBean> li) {
        dataView.addData(li);
    }

    @Override
    public void fuil(String str) {
        dataView.showToast(str);
    }

    public void getData() {
        dataModel.getData(this);
    }

    public void insert(DatasBean datasBean) {
        dataModel.insert(this,datasBean);
    }

    public void delete(DatasBean datasBean) {
        dataModel.delete(this,datasBean);
    }
}
