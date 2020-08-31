package com.stratone.bmotion.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.stratone.bmotion.R;
import com.stratone.bmotion.model.Info;
import com.stratone.bmotion.model.PurchaseHistory;

import java.util.List;

public class InfoAdapter extends ArrayAdapter<Info>
{
    private List<Info> list;
    private Context context;
    private int layout;

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();

        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View v = convertView;
        final InfoHolder holder;

        if (v == null) {
            LayoutInflater vi = ((AppCompatActivity) context).getLayoutInflater();
            v = vi.inflate(layout, parent, false);

            holder = new InfoHolder();
            holder.tvNo = v.findViewById(R.id.tvNo);
            holder.tvCreatedDate = v.findViewById(R.id.tvCreatedDate);
            holder.tvMsg = v.findViewById(R.id.tvMsg);
            holder.tvTitle = v.findViewById(R.id.tvTitle);
            holder.lnDown = v.findViewById(R.id.lnDown);
            holder.lnTop = v.findViewById(R.id.lnTop);
            holder.lnInfoHeader = v.findViewById(R.id.lnInfoHeader);
            holder.lnInfoDetail = v.findViewById(R.id.lnInfoDetail);

            holder.lnDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Open(holder.lnDown,holder.lnTop,holder.lnInfoDetail);
                }
            });

            holder.lnTop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Closed(holder.lnDown,holder.lnTop,holder.lnInfoDetail);
                }
            });

            holder.lnInfoHeader.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(holder.lnInfoDetail.getVisibility() == View.GONE)
                    {
                        Open(holder.lnDown,holder.lnTop,holder.lnInfoDetail);
                    }
                    else
                    {
                        Closed(holder.lnDown,holder.lnTop,holder.lnInfoDetail);
                    }
                }
            });

            v.setTag(holder);
        } else {
            holder = (InfoHolder) v.getTag();
        }

        final Info infoList = list.get(position);
        holder.tvNo.setText(infoList.getNO().toString());
        holder.tvCreatedDate.setText(infoList.getCreateDate());
        holder.tvMsg.setText(infoList.getMessage());
        holder.tvTitle.setText(infoList.getTitle());

        return v;
    }

    public InfoAdapter(Context context, int layout, List<Info> infoList) {
        super(context, layout, infoList);
        this.context = context;
        this.layout = layout;
        this.list = infoList;
    }

    static class InfoHolder {
        TextView tvNo, tvCreatedDate, tvMsg, tvTitle;
        LinearLayout lnDown, lnTop, lnInfoHeader, lnInfoDetail;
    }

    private void Open(LinearLayout down, LinearLayout top, LinearLayout detail)
    {
        down.setVisibility(View.GONE);
        top.setVisibility(View.VISIBLE);
        detail.setVisibility(View.VISIBLE);
    }

    private void Closed(LinearLayout down, LinearLayout top, LinearLayout detail)
    {
        down.setVisibility(View.VISIBLE);
        top.setVisibility(View.GONE);
        detail.setVisibility(View.GONE);
    }
}
