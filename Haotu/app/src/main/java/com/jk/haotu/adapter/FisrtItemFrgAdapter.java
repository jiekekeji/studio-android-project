package com.jk.haotu.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jk.haotu.R;
import com.jk.haotu.bean.Tngou;
import com.jk.haotu.net.URLapi;

import java.util.List;

/**
 * Created by jack on 16/5/21.
 */
public class FisrtItemFrgAdapter extends PagerAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Tngou> mTngous;

    public FisrtItemFrgAdapter(Context context, List<Tngou> tngous) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mTngous = tngous;
    }


    @Override
    public int getCount() {
        return mTngous.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View itemView = mInflater.inflate(R.layout.adapter_fr_fisrt, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imgview);
//        TextView titleTv= (TextView) itemView.findViewById(R.id.title);

        Tngou tngou = mTngous.get(position);
        Glide.with(mContext).load(URLapi.IMG_PRE+tngou.getImg()).diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.test).into(imageView);
        LogUtils.i("列表图片url="+URLapi.IMG_PRE+tngou.getImg());
//        titleTv.setText(tngou.getTitle());
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    //start  PagerAdapter的notifyDataSetChanged无效解决方法
    private int mChildCount = 0;

    @Override
    public void notifyDataSetChanged() {
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object)   {
        if ( mChildCount > 0) {
            mChildCount --;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }
    //end
}
