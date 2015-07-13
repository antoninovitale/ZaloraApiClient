package com.example.zalora;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zalora.R;

public class FirstFragment extends Fragment {

	public final static String TAG = FirstFragment.class.getSimpleName();

	public static FirstFragment init(Bundle args) {
		FirstFragment tmp = new FirstFragment();
		tmp.setArguments(args);
		return tmp;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView");

		View layoutView = inflater.inflate(R.layout.fragment_first,
				container, false);

		return layoutView;
	}
}
