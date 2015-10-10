package com.anony.okhttp.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.anony.okhttp.AsyncOkHttp;
import com.anony.okhttp.BaseResponseHandler;
import com.anony.okhttp.RequestParams;

public class MainActivity extends AppCompatActivity {

	private final String TAG = "AsyncOkHttp";
	private TextView mResponse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mResponse = (TextView) findViewById(R.id.response);
	}

	public void noParamsGet(View view) {
		String url = "http://op.juhe.cn/onebox/news/query?key=b572d3f948f1a2c9e8f04bd138ac0bd4&q=成都";
		AsyncOkHttp.getInstance().get(url, new BaseResponseHandler() {

			@Override
			public void onStart() {
				super.onStart();
				Toast.makeText(MainActivity.this, "onStart", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFinish() {
				super.onFinish();
				Toast.makeText(MainActivity.this, "onFinish", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFailure(int code, String responseString) {
				super.onFailure(code, responseString);
				Log.e(TAG, "=====onFailure：  " + responseString);
				Log.e(TAG, "=====onFailureCode：  " + code);
				mResponse.setText("Get Failure Code：\n" + code + "\nGet Failure response：\n" + responseString);
			}

			@Override
			public void onSuccess(int code, String responseString) {
				Log.e(TAG, "=====onSuccess：  " + responseString);
				mResponse.setText("Get response：\n" + responseString);
			}
		});
	}

	public void paramsGet(View view) {
		String url = "http://op.juhe.cn/onebox/news/query";
		RequestParams params = new RequestParams();
		params.put("key", "b572d3f948f1a2c9e8f04bd138ac0bd4");
		params.put("q", "北京");
		AsyncOkHttp.getInstance().get(url, params, new BaseResponseHandler() {

			@Override
			public void onFailure(int code, String responseString) {
				super.onFailure(code, responseString);
				Log.e(TAG, "=====onFailure：  " + responseString);
				Log.e(TAG, "=====onFailureCode：  " + code);
				mResponse.setText("Get params Failure Code：\n" + code + "\nGet params Failure response：\n" + responseString);
			}

			@Override
			public void onSuccess(int code, String responseString) {
				Log.e(TAG, "=====onSuccess：  " + responseString);
				mResponse.setText("Get params response：\n" + responseString);
			}
		});
	}

	public void paramsPost(View view) {
		String url = "http://op.juhe.cn/onebox/news/query";
		RequestParams params = new RequestParams();
		params.put("key", "b572d3f948f1a2c9e8f04bd138ac0bd4");
		params.put("q", "北京");
		AsyncOkHttp.getInstance().post(url, params, new BaseResponseHandler() {

			@Override
			public void onFailure(int code, String responseString) {
				super.onFailure(code, responseString);
				Log.e(TAG, "=====onFailure：  " + responseString);
				Log.e(TAG, "=====onFailureCode：  " + code);
				mResponse.setText("Post params Failure Code：\n" + code + "\nPost params Failure response：\n" + responseString);
			}

			@Override
			public void onSuccess(int code, String responseString) {
				Log.e(TAG, "=====onSuccess：  " + responseString);
				mResponse.setText("Post params response：\n" + responseString);
			}
		});
	}

	public void paramsPut(View view) {
		String url = "http://op.juhe.cn/onebox/news/query";
		RequestParams params = new RequestParams();
		params.put("key", "b572d3f948f1a2c9e8f04bd138ac0bd4");
		params.put("q", "北京");
		AsyncOkHttp.getInstance().put(url, params, new BaseResponseHandler() {

			@Override
			public void onFailure(int code, String responseString) {
				super.onFailure(code, responseString);
				Log.e(TAG, "=====onFailure：  " + responseString);
				Log.e(TAG, "=====onFailureCode：  " + code);
				mResponse.setText("Put params Failure Code：\n" + code + "\nPut params Failure response：\n" + responseString);
			}

			@Override
			public void onSuccess(int code, String responseString) {
				Log.e(TAG, "=====onSuccess：  " + responseString);
				mResponse.setText("Put params response：\n" + responseString);
			}
		});
	}

}
