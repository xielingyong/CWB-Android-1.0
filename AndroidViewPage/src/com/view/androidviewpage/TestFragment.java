package com.view.androidviewpage;

import android.support.v4.app.Fragment;


public final class TestFragment extends Fragment {

    public static Fragment newInstance(String content) {

    	Fragment fragment=null;
    	
    	if(content.equals("��ҳ")){
    		fragment=new Fragment1();
    	}else if(content.equals("��Ʒ")){
    		fragment=new Fragment2();
    	}else if(content.equals("����")){
    		fragment=new Fragment3();
    	}else if(content.equals("Ʒ��")){
    		fragment=new Fragment4();
    	}else if(content.equals("ҽ��")){
    		fragment=new Fragment5();
    	}
    	
        return fragment;
    }

}
