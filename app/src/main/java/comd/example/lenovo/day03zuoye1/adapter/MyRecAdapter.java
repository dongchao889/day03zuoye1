package comd.example.lenovo.day03zuoye1.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import comd.example.lenovo.day03zuoye1.BaseApp;
import comd.example.lenovo.day03zuoye1.R;
import comd.example.lenovo.day03zuoye1.bean.DatasBean;
import comd.example.lenovo.day03zuoye1.db.DatasBeanDao;

/**
 * Created by lenovo on 2019/9/20.
 */

public class MyRecAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<DatasBean> li;
    private Context context;

    public MyRecAdapter(ArrayList<DatasBean> li, Context context) {
        this.li = li;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item1, parent, false);

        return new MyHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyHolder1 holder1= (MyHolder1) holder;
        holder1.tv1.setText(li.get(position).getTitle());
        holder1.tv2.setText(li.get(position).getAuthor());
        holder1.tv2.getPaint().setFlags(Paint.FAKE_BOLD_TEXT_FLAG);
        RequestOptions crop = new RequestOptions().circleCrop();
        Glide.with(context).load(li.get(position).getAvatar()).apply(crop).into(holder1.iv);
        DatasBeanDao dao = BaseApp.getInstance().getDaoSession().getDatasBeanDao();
        DatasBean bean = dao.queryBuilder().where(DatasBeanDao.Properties.Id.eq(li.get(position).getId())).unique();
        if (bean!=null){
            holder1.bt.setText("取消");
        }
        holder1.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onclick!=null){
                    onclick.onclick(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return li.size();
    }
    class MyHolder1 extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tv1;
        private TextView tv2;
        private Button bt;
        public MyHolder1(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.item1_iv);
            tv1=itemView.findViewById(R.id.item1_tv1);
            tv2=itemView.findViewById(R.id.item1_tv2);
            bt=itemView.findViewById(R.id.bt);

        }
    }
    public interface Onclick{
        void onclick(View view,int position);
    }
    private Onclick onclick;

    public void setOnclick(Onclick onclick) {
        this.onclick = onclick;
    }
}
