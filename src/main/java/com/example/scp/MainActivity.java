package com.example.scp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    AsyncNetworkTask task;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn_onClick(View v) {
        final EditText editId = findViewById(R.id.id);
        final EditText editPass = findViewById(R.id.pass);
        TextView alert = findViewById(R.id.alertView);

        String id = editId.getText().toString();
        String pass = editPass.getText().toString();

        // 入力のnullチェック
        if(id.length() != 0 && pass.length() != 0) {

            // 非同期処理を実行
            task = new AsyncNetworkTask(context);

            task.setOnCallBack(new AsyncNetworkTask.CallBackTask() {

                @Override
                public void CallBack(String result) {
                    super.CallBack(result);
                    // ここからAsyncTask処理後の処理を記述します。
                    Intent intent = new Intent(getApplication(), SubActivity.class);
                    intent.putExtra("names", result);
                    startActivity(intent);
                }
            });

            task.execute("http://10.0.2.2/mobilejson", id, pass);
        }else if(id.length() == 0 && pass.length() != 0) {
            alert.setText("idが入力されていません");
        }else if(id.length() != 0 && pass.length() == 0) {
            alert.setText("パスワードが入力されていません");
        }else {
            alert.setText("idとパスワードが入力されていません");
        }
    }

}
