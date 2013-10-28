package ru.mail.homeworkandroid1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FourthActivity extends FragmentActivity implements FragmentDialogue2.FragmentDialogListener {
	TextView left, right;
	String text;
	
	public void showDialog() {
		DialogFragment newFragment = new FragmentDialogue2();
		newFragment.show(getSupportFragmentManager(), "");
	}
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.activity_fourth);
		
		left = (TextView)findViewById(R.id.txt_view3);
		right = (TextView)findViewById(R.id.txt_view4);
		text = getIntent().getStringExtra(MainActivity.FOURTH_ACTIVITY_EXTRA);
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
		
		Button dialogButton = (Button)findViewById(R.id.dialogue_btn1);
		dialogButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDialog();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fourth, menu);
		return true;
	}

	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		
	}

}
