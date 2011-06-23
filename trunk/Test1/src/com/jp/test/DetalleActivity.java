package com.jp.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DetalleActivity extends Activity {

	private EditText mTitleText;
    private EditText mBodyText;
    private String mRowId;

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        //setContentView(R.layout.note_edit);
        //setTitle(R.string.edit_note);
		String title= null, body=null;
        //mTitleText = (EditText) findViewById(R.id.title);
        //mBodyText = (EditText) findViewById(R.id.body);

        //Button confirmButton = (Button) findViewById(R.id.confirm);

        mRowId = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //String title = extras.getString(NotesDbAdapter.KEY_TITLE);
            //String body = extras.getString(NotesDbAdapter.KEY_BODY);
            mRowId = extras.getString(ListadoActivity.KEY_ROWID);

            if (title != null) {
                mTitleText.setText(title);
            }
            if (body != null) {
                mBodyText.setText(body);
            }
        }
        Toast.makeText(getApplicationContext(), "rowid: "+mRowId,
  	          Toast.LENGTH_SHORT).show();
/*
        confirmButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Bundle bundle = new Bundle();

                bundle.putString(NotesDbAdapter.KEY_TITLE, mTitleText.getText().toString());
                bundle.putString(NotesDbAdapter.KEY_BODY, mBodyText.getText().toString());
                if (mRowId != null) {
                    bundle.putLong(NotesDbAdapter.KEY_ROWID, mRowId);
                }

                Intent mIntent = new Intent();
                mIntent.putExtras(bundle);
                setResult(RESULT_OK, mIntent);
                finish();
            }

        });*/
	}
}
