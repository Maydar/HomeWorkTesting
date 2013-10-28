package ru.mail.homeworkandroid1;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends FragmentActivity implements FragmentDialogue.FragmentDialogListener {
	public static final String SAVE_STATE_STRING = "edit_text";
	public static final String SECOND_ACTIVITY_EXTRA = "EXTRA_SECOND_ACTIVITY";
	public static final String FOURTH_ACTIVITY_EXTRA = "EXTRA_FOURTH_ACTIVITY";
	private EditText edit2;
		
	public void showDialog() {
		DialogFragment newFragment = new FragmentDialogue();
		newFragment.show(getSupportFragmentManager(), "");
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		if(savedInstanceState != null) {
			edit2 = (EditText)findViewById(R.id.edit_text1);
			edit2.setText(savedInstanceState.getString(SAVE_STATE_STRING));	
		}
		
		Button dialogButton = (Button)findViewById(R.id.dialogue_btn);
		dialogButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDialog();
			}
		});
		
		ImageButton homeButton =(ImageButton)findViewById(R.id.imgbtn1);
		homeButton.setOnClickListener(new View.OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
			//	Fragment fragment = new SecondFragment();
			//	FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
			//	fragmentTransaction.add(R.id.fragment1, fragment);
				//fragmentTransaction.commit();
				/*Intent i = new Intent(MainActivity.this, SecondFragment.class);
				edit2 = (EditText)findViewById(R.id.edit_text1);
				i.putExtra(SECOND_ACTIVITY_EXTRA, edit2.getText().toString());
				startActivity(i);*/	
			}
		});
		
		
		
		
		ImageButton gearButton =(ImageButton)findViewById(R.id.imgbtn2);
		gearButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, ThirdActivity.class);
				
				startActivity(i);
				
			}
		});
		
		ImageButton mailButton =(ImageButton)findViewById(R.id.imgbtn3);
		mailButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, FourthActivity.class);
				edit2 = (EditText)findViewById(R.id.edit_text1);
				i.putExtra(FOURTH_ACTIVITY_EXTRA, edit2.getText().toString());
				startActivity(i);
				
			}
		});
		
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}
	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		Dialog dialogView = dialog.getDialog();
	    EditText edit_dlg = ((EditText)dialogView.findViewById(R.id.edit_text_dlg));
;
		edit2 = (EditText)findViewById(R.id.edit_text1);		
		edit2.setText(edit_dlg.getText().toString());
	}
	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		edit2 = (EditText)findViewById(R.id.edit_text1);	
		outState.putString(SAVE_STATE_STRING, edit2.getText().toString());
		
	}

}
