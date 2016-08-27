package com.jk.srv.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jk.srv.R;

import java.util.List;

/**
 * Created by Administrator on 2016-07-04.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    private static final String TAG = MyAdapter.class.getSimpleName();
    public List<String> datas = null;

    public MyAdapter(List<String> datas) {
        this.datas = datas;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        vh.mTextView.setOnClickListener(this);
        vh.mTextView.setOnLongClickListener(this);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.mTextView.setText(datas.get(position));
        viewHolder.mTextView.setTag(datas.get(position));
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        private ImageView mImageView;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text);
            mImageView = (ImageView) view.findViewById(R.id.imageview);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text:
                Log.i(TAG,"点击了:"+v.getTag());
                break;
            case R.id.imageview:

                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        Log.i(TAG,"长按了:"+v.getTag());
        datas.remove(v.getTag());
        notifyDataSetChanged();
        Log.i(TAG,"已删除:"+v.getTag());
        return false;
    }
}
