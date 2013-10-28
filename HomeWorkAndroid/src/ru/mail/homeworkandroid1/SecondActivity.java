package ru.mail.homeworkandroid1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

public class SecondActivity extends Activity{
	TextView left, right;
	String text;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		left = (TextView)findViewById(R.id.txt_view1);
		right = (TextView)findViewById(R.id.txt_view2);
		text = getIntent().getStringExtra(MainActivity.SECOND_ACTIVITY_EXTRA);
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
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.second, menu);
		return true;
	}

}
