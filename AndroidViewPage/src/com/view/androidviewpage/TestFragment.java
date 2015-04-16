package com.view.androidviewpage;

import android.support.v4.app.Fragment;


public final class TestFragment extends Fragment {

    public static Fragment newInstance(String content) {

    	Fragment fragment=null;
    	
    	if(content.equals("首页")){
    		fragment=new Fragment1();
    	}else if(content.equals("商品")){
    		fragment=new Fragment2();
    	}else if(content.equals("健康")){
    		fragment=new Fragment3();
    	}else if(content.equals("品种")){
    		fragment=new Fragment4();
    	}else if(content.equals("医疗")){
    		fragment=new Fragment5();
    	}
    	
        return fragment;
    }

}
