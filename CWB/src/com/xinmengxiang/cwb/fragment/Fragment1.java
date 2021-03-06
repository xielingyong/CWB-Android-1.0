package com.xinmengxiang.cwb.fragment;

import java.util.ArrayList;
import zrc.widget.SimpleFooter;
import zrc.widget.SimpleHeader;
import zrc.widget.ZrcListView;
import zrc.widget.ZrcListView.OnItemClickListener;
import zrc.widget.ZrcListView.OnStartListener;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.xinmengxiang.cwb.activity.R;


public final class Fragment1 extends Fragment {
	
	private ZrcListView listView;
    private Handler handler;
    private ArrayList<String> msgs;
    private int pageId = -1;
    private MyAdapter adapter;

    private static final String[][] names = new String[][]{
        {"加拿大","瑞典","澳大利亚","瑞士","新西兰","挪威","丹麦","芬兰","奥地利","荷兰","德国","日本","比利时","意大利","英国"},
        {"德国","西班牙","爱尔兰","法国","葡萄牙","新加坡","希腊","巴西","美国","阿根廷","波兰","印度","秘鲁","阿联酋","泰国"},
        {"智利","波多黎各","南非","韩国","墨西哥","土耳其","埃及","委内瑞拉","玻利维亚","乌克兰"},
        {"以色列","海地","中国","沙特阿拉伯","俄罗斯","哥伦比亚","尼日利亚","巴基斯坦","伊朗","伊拉克"}
    };

	
/*	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 // 主动下拉刷新
	
		System.out.println("oncreate................");
		
}
*/

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		System.out.println("onCreateView................");
		
		View view = inflater.inflate(R.layout.fragment1, container,false);
		
		listView = (ZrcListView) view.findViewById(R.id.zListView);
	    handler = new Handler();

	     /*// 设置默认偏移量，主要用于实现透明标题栏功能。（可选）
	    float density = getResources().getDisplayMetrics().density;
	    listView.setFirstTopOffset((int) (50 * density));*/

	    // 设置下拉刷新的样式（可选，但如果没有Header则无法下拉刷新）
	    SimpleHeader header = new SimpleHeader(getActivity());
	    header.setTextColor(Color.RED);
	    header.setCircleColor(Color.RED);
	    listView.setHeadable(header);

	    // 设置加载更多的样式（可选）
	    SimpleFooter footer = new SimpleFooter(getActivity());
	    footer.setCircleColor(Color.RED);
	    listView.setFootable(footer);

	    // 设置列表项出现动画（可选）
	    listView.setItemAnimForTopIn(R.anim.topitem_in);
	    listView.setItemAnimForBottomIn(R.anim.bottomitem_in);

	    // 下拉刷新事件回调（可选）
	    listView.setOnRefreshStartListener(new OnStartListener() {
	        @Override
	        public void onStart() {
	            refresh();
	        }
	    });

	    // 加载更多事件回调（可选）
	    listView.setOnLoadMoreStartListener(new OnStartListener() {
	        @Override
	        public void onStart() {
	            loadMore();
	        }
	    });
	
	    adapter = new MyAdapter();
	    listView.setAdapter(adapter);
	    listView.refresh();
		
	    listView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(ZrcListView parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				String data = ((TextView) view).getText().toString();
				Toast.makeText(getActivity(), "当前选择的城市是"+data, 0).show();
			}
		});
		return view;
	}


private void refresh(){
    handler.postDelayed(new Runnable() {
        @Override
        public void run() {
            int rand = (int) (Math.random() * 2); // 随机数模拟成功失败。这里从有数据开始。
            if(rand == 0 || pageId == -1){
                pageId = 0;
                msgs = new ArrayList<String>();
                for(String name:names[0]){
                    msgs.add(name);
                }
                adapter.notifyDataSetChanged();
                listView.setRefreshSuccess("加载成功"); // 通知加载成功
                listView.startLoadMore(); // 开启LoadingMore功能
            }else{
                listView.setRefreshFail("加载失败");
            }
        }
    }, 2 * 1000);
}

private void loadMore(){
    handler.postDelayed(new Runnable() {
        @Override
        public void run() {
            pageId++;
            if(pageId<names.length){
                for(String name:names[pageId]){
                    msgs.add(name);
                }
                adapter.notifyDataSetChanged();
                listView.setLoadMoreSuccess();
            }else{
                listView.stopLoadMore();
            }
        }
    }, 2 * 1000);
}

private class MyAdapter extends BaseAdapter{
    @Override
    public int getCount() {
        return msgs==null ? 0 : msgs.size();
    }
    @Override
    public Object getItem(int position) {
        return msgs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if(convertView==null) {
            textView = (TextView) getActivity().getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null);
        }else{
            textView = (TextView) convertView;
        }
        textView.setText(msgs.get(position));
        return textView;
    }
}
}
