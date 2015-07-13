package com.example.zalora.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.zalora.ApplicationStatus;
import com.example.zalora.FirstFragment;
import com.example.zalora.LeftDrawerListItem;
import com.example.zalora.R;
import com.example.zalora.SecondFragment;

import java.util.ArrayList;
import java.util.List;

public class Utils {
	
	 public static void tryToDismissDialog(Dialog dialog) {
			try {
				dialog.dismiss();
			} catch (Exception e) {
			}
		}
	
	public static void openNextFragment(Context context, int type, Bundle args) {
		Fragment fragment = null;
		String tag;
		switch (type) {
		case Constants.SCREEN_FIRST:
			fragment = FirstFragment.init(args);
			tag = FirstFragment.TAG;
			break;
		case Constants.SCREEN_SECOND:			
			fragment = SecondFragment.init(args);
			tag = SecondFragment.TAG;
			break;
		default:
			return;
		}
		FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager()
				.beginTransaction();
//		transaction.setCustomAnimations(R.anim.slide_in_right,
//				android.R.anim.slide_out_right, R.anim.slide_in_right,
//				android.R.anim.slide_out_right);
		transaction.replace(R.id.content_frame, fragment, tag);
		transaction.commit();
	}

	public static void retrieveLeftDrawerList(Context context) {
		String[] leftDrawerList = context.getResources().getStringArray(R.array.left_drawer);
		List<LeftDrawerListItem> leftDrawerListItems = new ArrayList<LeftDrawerListItem>();
		LeftDrawerListItem leftDrawerListItem;
		for(String name : leftDrawerList) {
			leftDrawerListItem = new LeftDrawerListItem();
			leftDrawerListItem.setName(name);
			leftDrawerListItems.add(leftDrawerListItem);
		}
		ApplicationStatus.getInstance().setLeftDrawerListItems(leftDrawerListItems);
	}
}