package com.mindpin.android.pinterestlistview.samples;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import com.mindpin.android.pinterestlistview.PinterestListView;

import java.util.Arrays;
import java.util.Random;

public class ScrollbarActivity extends Activity {

	private class MySimpleAdapter extends ArrayAdapter<String> {

		public MySimpleAdapter(Context context, int layoutRes) {
			super(context, layoutRes, android.R.id.text1);
		}
	}

	private PinterestListView mAdapterView = null;
	private MySimpleAdapter mAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scrollbar);
		mAdapterView = (PinterestListView) findViewById(R.id.list);
	}

	@Override
	protected void onResume() {
		super.onResume();
		initAdapter();
		mAdapterView.setAdapter(mAdapter);
	}

	private Random mRand = new Random();
	private void initAdapter() {
		mAdapter = new MySimpleAdapter(this, R.layout.sample_item);

		for( int i = 0; i < 30; ++i){
			//generate 30 random items.

			StringBuilder builder = new StringBuilder();
			builder.append("Hello!![");
			builder.append(i);
			builder.append("] ");

			char[] chars = new char[mRand.nextInt(500)];
			Arrays.fill(chars, '1');
			builder.append(chars);
			mAdapter.add(builder.toString());
		}

	}

}//end of class
