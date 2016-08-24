package one.stk.com.indextextview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import indexview.Util;

/**
 * Created by admin on 2016/8/23.
 */
public class MyAdapter extends BaseAdapter {
    ArrayList<TextModel> list;
    Context context;

    public MyAdapter(ArrayList<TextModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public MyAdapter() {
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        helper h = null;
        if (h == null) {
            h = new helper();
            view = LayoutInflater.from(context).inflate(R.layout.index, null);
            h.index = (TextView) view.findViewById(R.id.index);
            h.name = (TextView) view.findViewById(R.id.name);
            view.setTag(h);
        } else {
            h = (helper) view.getTag();
        }
        h.name.setText(list.get(i).getName());
        char index = Util.getIndex(list.get(i).getName());
        if (i == 0 || index != Util.getIndex(list.get(i - 1).getName())) {
            h.index.setVisibility(View.VISIBLE);
            h.index.setText(String.valueOf(index));
        } else {
            h.index.setVisibility(View.GONE);
        }
        //点击
        h.name.setOnClickListener(new myOnClick(i, context));
        return view;
    }

    //点击
    public class myOnClick implements View.OnClickListener {
        int i;
        Context context;

        public myOnClick(int i, Context context) {
            this.i = i;
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            Log.e("ttt", i + "");
            Toast.makeText(context, "点击了" + i, Toast.LENGTH_LONG).show();
        }
    }

    public class helper {
        TextView index, name;
    }
}
