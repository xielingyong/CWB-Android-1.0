package com.view.androidviewpage;

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


public final class Fragment1 extends Fragment {
	
	private ZrcListView listView;
    private Handler handler;
    private ArrayList<String> msgs;
    private int pageId = -1;
    private MyAdapter adapter;

    private static final String[][] names = new String[][]{
        {"���ô�","���","�Ĵ�����","��ʿ","������","Ų��","����","����","�µ���","����","�¹�","�ձ�","����ʱ","�����","Ӣ��"},
        {"�¹�","������","������","����","������","�¼���","ϣ��","����","����","����͢","����","ӡ��","��³","������","̩��"},
        {"����","�������","�Ϸ�","����","ī����","������","����","ί������","����ά��","�ڿ���"},
        {"��ɫ��","����","�й�","ɳ�ذ�����","����˹","���ױ���","��������","�ͻ�˹̹","����","������"}
    };

	
/*	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 // ��������ˢ��
	
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

	     /*// ����Ĭ��ƫ��������Ҫ����ʵ��͸�����������ܡ�����ѡ��
	    float density = getResources().getDisplayMetrics().density;
	    listView.setFirstTopOffset((int) (50 * density));*/

	    // ��������ˢ�µ���ʽ����ѡ�������û��Header���޷�����ˢ�£�
	    SimpleHeader header = new SimpleHeader(getActivity());
	    header.setTextColor(Color.RED);
	    header.setCircleColor(Color.RED);
	    listView.setHeadable(header);

	    // ���ü��ظ������ʽ����ѡ��
	    SimpleFooter footer = new SimpleFooter(getActivity());
	    footer.setCircleColor(Color.RED);
	    listView.setFootable(footer);

	    // �����б�����ֶ�������ѡ��
	    listView.setItemAnimForTopIn(R.anim.topitem_in);
	    listView.setItemAnimForBottomIn(R.anim.bottomitem_in);

	    // ����ˢ���¼��ص�����ѡ��
	    listView.setOnRefreshStartListener(new OnStartListener() {
	        @Override
	        public void onStart() {
	            refresh();
	        }
	    });

	    // ���ظ����¼��ص�����ѡ��
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
				Toast.makeText(getActivity(), "��ǰѡ��ĳ�����"+data, 0).show();
			}
		});
		return view;
	}


private void refresh(){
    handler.postDelayed(new Runnable() {
        @Override
        public void run() {
            int rand = (int) (Math.random() * 2); // �����ģ��ɹ�ʧ�ܡ�����������ݿ�ʼ��
            if(rand == 0 || pageId == -1){
                pageId = 0;
                msgs = new ArrayList<String>();
                for(String name:names[0]){
                    msgs.add(name);
                }
                adapter.notifyDataSetChanged();
                listView.setRefreshSuccess("���سɹ�"); // ֪ͨ���سɹ�
                listView.startLoadMore(); // ����LoadingMore����
            }else{
                listView.setRefreshFail("����ʧ��");
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
