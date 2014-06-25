package com.mindpin.android.pinterestlistview.samples;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import com.mindpin.android.pinterestlistview.PinterestListView;

import java.util.Arrays;
import java.util.Random;

public class PullToRefreshSampleActivity extends Activity {

    private static final String TAG = "PullToRefreshSampleActivity";

    private class MySimpleAdapter extends ArrayAdapter<String> {

		public MySimpleAdapter(Context context, int layoutRes) {
			super(context, layoutRes, android.R.id.text1);
		}
	}

	private PinterestListView mAdapterView = null;
	private MySimpleAdapter mAdapter = null;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.waterfall);
		//mAdapterView = (PLA_AdapterView<Adapter>) findViewById(R.id.list);
		mAdapterView = (PinterestListView) findViewById(R.id.list);
        mAdapterView.setOnRefreshListener(new PinterestListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new RefreshTask().execute();
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, 1001, 0, "Load More Contents");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch(item.getItemId()){
		case 1001:
		{
			int startCount = mAdapter.getCount();
			for( int i = 0; i < 100; ++i){
				//generate 100 random items.

				StringBuilder builder = new StringBuilder();
				builder.append("Hello!![");
				builder.append(startCount + i);
				builder.append("] ");

				char[] chars = new char[mRand.nextInt(100)];
				Arrays.fill(chars, '1');
				builder.append(chars);
				mAdapter.add(builder.toString());
			}
		}
		break;
		case 1002:
		{
			Intent intent = new Intent(this, PullToRefreshSampleActivity.class);
			startActivity(intent);
		}
		break;
		}
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
        initAdapterIfNull();
        init();
        mAdapterView.setAdapter(mAdapter);
		//mAdapterView.setAdapter(mAdapter);
	}

    private void init() {
        mAdapterView.setTextPullToRefresh("下拉刷新");
        mAdapterView.setTextRefreshing("刷新中");
        mAdapterView.setTextReleaseToRefresh("松开刷新");
        initDatas();
    }

    int i = 0;
	private Random mRand = new Random();
	private void initDatas() {
        int j = i+30;
		for( ; i < j; ++i){
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

    private void initAdapterIfNull() {
        if(mAdapter == null)
            initAdapter();
    }

    private void initAdapter() {
        mAdapter = new MySimpleAdapter(this, R.layout.sample_item);
    }

    private class RefreshTask extends AsyncTask<Void, Long, Void> {
        @Override
        protected Void doInBackground(Void... requestParams) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            initAdapter();
            init();
            mAdapterView.setAdapter(mAdapter);
            mAdapterView.onRefreshComplete();
        }
    }

}//end of class
