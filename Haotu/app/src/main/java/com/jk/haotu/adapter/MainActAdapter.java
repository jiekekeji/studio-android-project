package com.jk.haotu.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
public class MainActAdapter extends BaseAdapter {

    private int TYPE_COUNT = 3;//item类型的总数
    private static final int TYPE_FISRT = 0;//第一种类型的item
    private static final int TYPE_SECOND = 1;//第二种类型的item
    private static final int TYPE_THREE = 2;//第二种类型的item
    private int currentType;//当前的item类型


    private Context mContext;
    private List<Tngou> mList;
    private LayoutInflater mInflater;

    public MainActAdapter(Context context, List<Tngou> list) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mList = list;
    }

    /**
     * 返回item的总数
     *
     * @return
     */
    @Override
    public int getCount() {
        if (mList == null) {
            //当mList为空,不显示mList的布局
            return TYPE_COUNT - 1;
        } else {
            //总数为mList的布局加上固定的布局
            return (mList.size() + TYPE_COUNT - 1);
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return this.TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        int type = 0;
        switch (position) {
            case 0://第一个item布局应该显示的布局类型
                type = TYPE_FISRT;
                break;
            case 1://第二个item布局应该显示的布局类型
                type = TYPE_THREE;
                break;
            default://其他item布局应该显示的布局类型
                type = TYPE_SECOND;
                break;
        }
        return type;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        LogUtils.i("item次数:" + i);
        currentType = getItemViewType(i);
        switch (currentType) {
            case TYPE_FISRT:
                convertView = showtypeFisrtItem(i, convertView);
                break;
            case TYPE_THREE:
                convertView = showtypeThreeItem(i, convertView);
                break;
            case TYPE_SECOND:
                convertView = showtypeSecondItem(i, convertView);
                break;
        }
        return convertView;
    }

    /**
     * 显示第一种类型的item
     *
     * @param i
     * @param convertView
     */

    private View showtypeFisrtItem(int i, View convertView) {
        ViewHolderFisrt viewHolder = null;
        if (null == convertView) {
            viewHolder = new ViewHolderFisrt();
            convertView = mInflater.inflate(R.layout.adapter_main_fisrt, null);

//            viewHolder.nameTv = (TextView) convertView.findViewById(R.id.name);
//            viewHolder.viewPager= (ViewPager) convertView.findViewById(R.id.viewpager);
//            viewHolder.viewPager.setAdapter(new IndexHeaderAdapter(mContext, mList));

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderFisrt) convertView.getTag();
        }

        //设置数据
//        viewHolder.nameTv.setText("没想到我是第一个");
        return convertView;
    }

    /**
     * 显示第二种类型的item
     *
     * @param i
     * @param convertView
     */
    private View showtypeSecondItem(int i, View convertView) {
        ViewHolderSecond viewHolder = null;
        if (null == convertView) {
            viewHolder = new ViewHolderSecond();
            convertView = mInflater.inflate(R.layout.adapter_main_second, null);

//            viewHolder.titleTv = (TextView) convertView.findViewById(R.id.name);
            viewHolder.imgview = (ImageView) convertView.findViewById(R.id.imgview);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderSecond) convertView.getTag();
        }

        //设置数据
        // set item values to the viewHolder:
        Tngou tngou = mList.get(i - TYPE_COUNT + 1);
//        viewHolder.titleTv.setText(tngou.getTitle());
        Glide.with(mContext).load(URLapi.IMG_PRE + tngou.getImg()).diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.test).into(viewHolder.imgview);
        LogUtils.i("列表图片url=" + URLapi.IMG_PRE + tngou.getImg());
        return convertView;
    }

    /**
     * 显示第三种视图布局
     *
     * @param i
     * @param convertView
     * @return
     */
    private View showtypeThreeItem(int i, View convertView) {
        ViewHolderFisrt viewHolder = null;
        if (null == convertView) {
            viewHolder = new ViewHolderFisrt();
            convertView = mInflater.inflate(R.layout.adapter_main_three, null);

//            viewHolder.nameTv = (TextView) convertView.findViewById(R.id.name);
//            viewHolder.viewPager= (ViewPager) convertView.findViewById(R.id.viewpager);
//            viewHolder.viewPager.setAdapter(new IndexHeaderAdapter(mContext, mList));

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderFisrt) convertView.getTag();
        }

        //设置数据
//        viewHolder.nameTv.setText("没想到我是第一个");
        return convertView;


    }

    static class ViewHolderFisrt {
        TextView nameTv;
        ViewPager viewPager;
    }

    static class ViewHolderSecond {
        TextView titleTv;
        ImageView imgview;
    }
}
