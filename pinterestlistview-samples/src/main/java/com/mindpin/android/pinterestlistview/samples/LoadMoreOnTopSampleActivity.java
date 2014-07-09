package com.mindpin.android.pinterestlistview.samples;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.mindpin.android.pinterestlistview.PinterestListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LoadMoreOnTopSampleActivity extends Activity {

    private static final String TAG = "PullToRefreshSampleActivity";

    private class MySimpleAdapter extends ArrayAdapter<String> {

		public MySimpleAdapter(Context context, int layoutRes) {
			super(context, layoutRes, android.R.id.text1);
		}
	}

	private PinterestListView mAdapterView = null;
	private MySimpleAdapter mAdapter = null;

    int defaultTo = 30;
    int defaultFrom = 21;
    int from = defaultFrom;
    private Random mRand = new Random();

    List<String> strings;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loadmore_on_top);
        strings = new ArrayList<String>();
		mAdapterView = (PinterestListView) findViewById(R.id.list);
        mAdapterView.set_on_refresh_listener(new PinterestListView.OnRefreshListener() {
            @Override
            public void on_refresh() {
                new LoadMoreOnTopTask().execute();
            }
        });
        init();
	}

	@Override
	protected void onResume() {
		super.onResume();
        mAdapterView.setAdapter(mAdapter);
		//mAdapterView.setAdapter(mAdapter);
	}

    private void init() {
        mAdapterView.set_text_pull_to_refresh("下拉读取最新");
        mAdapterView.set_text_refreshing("读取中");
        mAdapterView.set_text_release_to_refresh("放手读取最新");
        initAdapter();
        initDatas(defaultFrom, defaultTo);
        datasToAdapter();
    }

    private void add_to_top() {
        initAdapter();
        addDatasToTop(from - 1);
        datasToAdapter();
    }

    private void datasToAdapter() {
        for(String str : strings){
            mAdapter.add(str);
        }
        mAdapterView.setAdapter(mAdapter);
    }

    private void initDatas(int from, int to) {
        int i = from;
		for( ; i < to; ++i){
			//generate 30 random items.

			StringBuilder builder = new StringBuilder();
			builder.append("Hello!![");
			builder.append(i);
			builder.append("] ");

			char[] chars = new char[mRand.nextInt(500)];
			Arrays.fill(chars, '1');
			builder.append(chars);
            strings.add(builder.toString());
		}
	}

    private void addDatasToTop(int from) {
        if(from > 0) {
            int i = from;
            for (; i >= 0; --i) {
                //generate 30 random items.

                StringBuilder builder = new StringBuilder();
                builder.append("Hello!![");
                builder.append(i);
                builder.append("] ");

                char[] chars = new char[mRand.nextInt(500)];
                Arrays.fill(chars, '1');
                builder.append(chars);
                strings.add(0, builder.toString());
            }
            this.from = 0;
        }
        else{
            Toast.makeText(this , "没有更新的内容", Toast.LENGTH_LONG).show();
        }
    }

    private void initAdapter() {
        mAdapter = new MySimpleAdapter(this, R.layout.sample_item);
    }

    private class LoadMoreOnTopTask extends AsyncTask<Void, Long, Void> {
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
            add_to_top();
            mAdapterView.on_refresh_complete();
        }
    }

}//end of class
