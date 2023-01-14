package com.example.myhome;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.myhome.m_DataObject.Categorie;
import com.example.myhome.m_DataObject.Sale;

import java.util.ArrayList;

public class CustomAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<Sale> sales;

    public CustomAdapter(Context context, ArrayList<Sale> deptList) {
        this.context = context;
        this.sales = deptList;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Categorie> productList = sales.get(groupPosition).getList();
        return productList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        Categorie detailInfo = (Categorie) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.list_item, null);
        }
        TextView childItem = (TextView) view.findViewById(R.id.nomCategorie);
        childItem.setText(detailInfo.getNomCat().trim());

        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        ArrayList<Categorie> productList = sales.get(groupPosition).getList();
        return productList.size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return sales.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return sales.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view,
                             ViewGroup parent) {

        Sale headerInfo = (Sale) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.list_group, null);
        }

        TextView heading = (TextView) view.findViewById(R.id.listTitle);
        heading.setText(headerInfo.getNomSale().trim());

        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
