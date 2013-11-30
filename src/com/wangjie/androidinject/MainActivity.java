package com.wangjie.androidinject;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.wangjie.androidinject.annotation.annotations.*;
import com.wangjie.androidinject.annotation.present.AIActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AILayout(R.layout.main)
public class MainActivity extends AIActivity {

    @AIView(id = R.id.btn1, clickMethod = "onClickCallback", longClickMethod = "onLongClickCallback")
    private Button btn1;

    @AIView(id = R.id.btn2, clickMethod = "onClickCallback", longClickMethod = "onLongClickCallback")
    private Button btn2;

//    @AIView(id = R.id.btn3)
//    private Button btn3;

//    @AIView(id = R.id.listView, itemClickMethod = "onItemClickCallback")
    @AIView(id = R.id.listView)
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map;
        for(int i = 0; i < 10; i++){
            map = new HashMap<String, String>();
            map.put("title", "item_" + i);
            list.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(context, list, R.layout.list_item, new String[]{"title"}, new int[]{R.id.list_item_title_tv});
        listView.setAdapter(adapter);

    }

    @Override
    public void onClickCallback(View view){
        if(view instanceof Button){
            Toast.makeText(context, "onClickCallback: " + ((Button)view).getText(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLongClickCallback(View view){
        if(view instanceof Button){
            Toast.makeText(context, "onLongClickCallback: " + ((Button)view).getText(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClickCallback(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(context, "onItemClickCallback: " + ((Map<String, String>)adapterView.getAdapter().getItem(i)).get("title"), Toast.LENGTH_SHORT).show();
    }

    @AIClick({R.id.btn3})
    public void onClickCallbackForBtn3(View view){
        if(view instanceof Button){
            System.out.println("onClickForBtn3");
            Toast.makeText(context, "onClickForBtn3: " + ((Button)view).getText(), Toast.LENGTH_SHORT).show();
        }
    }

    @AILongClick({R.id.btn3})
    public void onLongClickCallbackForBtn3(View view){
        if(view instanceof Button){
            System.out.println("onLongClickCallbackForBtn3");
            Toast.makeText(context, "onLongClickCallbackForBtn3: " + ((Button)view).getText(), Toast.LENGTH_SHORT).show();
        }
    }

    @AIItemClick({R.id.listView})
    public void onItemClickCallbackForListView(AdapterView<?> adapterView, View view, int i, long l){
        System.out.println("onItemClickCallbackForListView");
        Toast.makeText(context, "onItemClickCallbackForListView: " + ((Map<String, String>)adapterView.getAdapter().getItem(i)).get("title"), Toast.LENGTH_SHORT).show();
    }


}
