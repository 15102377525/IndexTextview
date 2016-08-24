package one.stk.com.indextextview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

import indexview.Binder;
import indexview.IndexView;
import indexview.PinyinComparator;
import indexview.Util;

public class MainActivity extends Activity implements TextWatcher {
    ArrayList<TextModel> list;
    ListView listview;
    IndexView IndexView;
    AutoCompleteTextView autotext;
    String[] arr = {"张三", "李四", "王二", "娜扎", "mime人", "乐通葡", "染网", "QAQ", "走le", "爱人", "巴塔", "周星驰", "刘德华", "陈奕迅", "李白", "杜甫"};
    String[] phone = {"1510236548", "123518489", "123123", "124124", "234234", "124124", "124124", "124124", "124124", "124124", "124124", "124124", "124124", "124124", "1124124", "124124"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.listview);
        autotext = (AutoCompleteTextView) findViewById(R.id.autotext);
        IndexView = (indexview.IndexView) findViewById(R.id.indexView);
        list = new ArrayList<>();
        getList();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        autotext.setAdapter(arrayAdapter);
        /**
         * 将集合按照字母排序
         * */
        Collections.sort(list, new PinyinComparator<TextModel>() {
            @Override
            public int compare(TextModel s1, TextModel s2) {
                return compare(s1.getName(), s2.getName());
            }
        });

        listview.setAdapter(new MyAdapter(list, this));
        Binder Binder = new Binder(listview, IndexView) {
            @Override
            public String getListItemKey(int position) {
                return ((TextModel) (listview.getAdapter().getItem(position))).getName();
            }
        };
        Binder.bind();
        autotext.addTextChangedListener(this);

        /**
         * 调用系统摄像机打电话
         * */
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + list.get(i).getPhone()));
                startActivity(intent);
                return true;
            }
        });
    }

    public void getList() {
        for (int i = 0; i < arr.length; i++) {
            list.add(new TextModel(arr[i], phone[i]));
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }


    /**
     * 输入结束之后 操作右边字母显示的位置
     */
    @Override
    public void afterTextChanged(Editable editable) {
        if (!autotext.getText().equals("") && autotext.getText() != null && autotext.getText().length() > 0) {
            Log.e("ttt", autotext.getText() + "");
            char index = Util.getIndex(autotext.getText() + "//////////////////////////////////////////////////////");
            int inde = index;
            IndexView.SetIndex((inde - 65), true);
        } else {
            int inde = 'A';
            IndexView.SetIndex((inde - 65), true);
        }
    }
}
