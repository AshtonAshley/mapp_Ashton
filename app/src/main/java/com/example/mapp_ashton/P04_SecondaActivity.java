package com.example.mapp_ashton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.EditText;
import android.widget.TextView;

public class P04_SecondaActivity extends AppCompatActivity {
    public	static	final	String	EXTRA_REPLY	=
            "edu.sp.p0123456.helloworld.extra.REPLY";
    private	EditText	mReply;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p04__seconda);

        Intent	intent	=	getIntent();
        String	message	=	intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView	=	findViewById(R.id.text_message);
        textView.setText(message);
    }
    public	void	returnReply(View	view)	{
        Log.d("SecondActivity",	"2nd	button	clicked!");
        String	reply	=	mReply.getText().toString();
        Intent	replyIntent	=	new	Intent();
        replyIntent.putExtra(EXTRA_REPLY,	reply);
        setResult(RESULT_OK,replyIntent);
        finish();
    }

}
