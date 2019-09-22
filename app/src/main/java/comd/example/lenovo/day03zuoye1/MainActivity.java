package comd.example.lenovo.day03zuoye1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import comd.example.lenovo.day03zuoye1.adapter.MyRecAdapter;
import comd.example.lenovo.day03zuoye1.bean.DatasBean;
import comd.example.lenovo.day03zuoye1.persenter.DataPer;
import comd.example.lenovo.day03zuoye1.view.DataView;

public class MainActivity extends AppCompatActivity implements DataView{
    private String TAG="MainActivity";
    private Toolbar mTb;
    private RecyclerView mRe;
    private ArrayList<DatasBean> li;
    private DataPer dataPer;
    private MyRecAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataPer = new DataPer(this);
        initView();
    }

    private void initView() {
        mTb = (Toolbar) findViewById(R.id.tb);
        mRe = (RecyclerView) findViewById(R.id.re);
        mTb.setTitle("");
        setSupportActionBar(mTb);
        mRe.setLayoutManager(new LinearLayoutManager(this));
        mRe.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        li = new ArrayList<>();

        adapter = new MyRecAdapter(li, this);
        mRe.setAdapter(adapter);
        dataPer.getData();
        adapter.setOnclick(new MyRecAdapter.Onclick() {
            @Override
            public void onclick(View view, final int position) {
                final Button bt = view.findViewById(R.id.bt);

                        String s = bt.getText().toString();
                        if (s.equals("关注")){
                            dataPer.insert(li.get(position));
                            bt.setText("取消");
                        }else {
                            dataPer.delete(li.get(position));
                            bt.setText("关注");


                        }
            }
        });
    }

    @Override
    public void addData(ArrayList<DatasBean> list) {
            li.addAll(list);
            adapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String str) {
//        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "showToast: "+str);
    }
}
