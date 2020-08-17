package com.example.scp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AsyncNetworkTask extends AsyncTask<String, Void, String> {
    private WeakReference<TextView> txtResult;
    private CallBackTask callbacktask;
    public JsonParse jsonParse;

    AsyncNetworkTask(Context context) {
        super();
        MainActivity activity = (MainActivity) context;
        txtResult = new WeakReference<>((TextView)activity.findViewById(R.id.alertView));
    }


    // 非同期でHTTP　GETリクエストを送信（指定のページを取得）
    @Override
    protected String doInBackground(String... params) {
        String result = null;
        String url = params[0];
        String id = params[1];
        String pass = params[2];

        org.json.JSONObject json = new org.json.JSONObject();
        // json作成
        try {
            json.put("id", id);
            json.put("pass", pass);
        }
        catch (org.json.JSONException e) {
        }

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"), json.toString());


        // リクエストオブジェクトを作って
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        // クライアントオブジェクトを作って
        OkHttpClient client = new OkHttpClient();

        // リクエストして結果を受け取って
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 返す
        return result;
    }

    // 非同期を終了後
    @Override
    protected void onPostExecute(String result) {
        String status = "ng";
        Log.d("result", result);
        try {
            //jsonパース
            JSONObject json = new JSONObject(result);
            status = json.getString("status");
        }catch(Exception e) {
            Log.e("status", e.getMessage());
        }

        if(status.equals("ok")) {
            callbacktask.CallBack(result);
        }else {
            txtResult.get().setText("idかパスワードが間違っています");
        }
    }

    public void setOnCallBack(CallBackTask _cbj) {
        callbacktask = _cbj;
    }

    public static class CallBackTask {
        public void CallBack(String result) {
        }
    }
}
