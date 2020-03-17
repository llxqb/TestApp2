package com.llxqb.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout mContainerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContainerLayout = findViewById(R.id.container_layout);
        TextView tx1 = findViewById(R.id.tx1);
        tx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取剪贴版
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                //创建ClipData对象
                //第一个参数只是一个标记，随便传入。
                //第二个参数是要复制到剪贴版的内容
                ClipData clip = ClipData.newPlainText("simple text", "https://fb.me/2pQYoXe68dnry4Y");
                //传入clipdata对象.
                clipboard.setPrimaryClip(clip);
            }
        });

        TextView tx2 = findViewById(R.id.tx2);
        tx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addView(view);
            }
        });
    }

    /**
     * 按钮点击事件，向容器中添加TextView
     *
     * @param view
     */
    public void addView(View view) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view1 = inflater.inflate(R.layout.item_text, null);
        TextView textView = view1.findViewById(R.id.text_tv);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(16);
        textView.setText("hello");
        layoutParams.setMargins(200, 200, 0, 0);
        textView.setLayoutParams(layoutParams);
        mContainerLayout.addView(view1);  // 调用一个参数的addView方法
    }
}
