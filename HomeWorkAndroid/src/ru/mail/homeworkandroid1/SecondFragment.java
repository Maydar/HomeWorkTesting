package ru.mail.homeworkandroid1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SecondFragment extends Fragment {
	
	TextView left, right;
	String text;
	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getActivity().setContentView(R.layout.second_fragment);
		left = (TextView)getActivity().findViewById(R.id.txt_view1);
		right = (TextView)getActivity().findViewById(R.id.txt_view2);
		text = getActivity().getIntent().getStringExtra(MainActivity.SECOND_ACTIVITY_EXTRA);
		if(text.isEmpty()) {
			left.setText("Нет текста");
			right.setText("Нет текста");
		}
		else {
			Integer midOfString = text.length()/2;
			String leftText = text.substring(0, midOfString);
			String rightText = text.substring(midOfString, text.length());
			left.setText(leftText);
			right.setText(rightText);
		}
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
	}

}
