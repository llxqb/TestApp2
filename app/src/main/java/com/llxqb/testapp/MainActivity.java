package com.llxqb.testapp;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.llxqb.testapp.ireader.model.bean.CollBookBean;
import com.llxqb.testapp.ireader.ui.activity.ReadActivity;
import com.llxqb.testapp.ireader.utils.Constant;
import com.llxqb.testapp.ireader.utils.MD5Utils;
import com.llxqb.testapp.ireader.utils.StringUtils;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout mContainerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        String FilePath = Environment.getExternalStorageDirectory() + "/all.txt";
        File file = new File(FilePath);
        CollBookBean collBookBean = convertCollBook(file);

        TextView tx3 = findViewById(R.id.tx3);
        tx3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReadActivity.startActivity(MainActivity.this,
                        collBookBean, true);
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



    /**
     * 将文件转换成CollBook
     *
     * @param file:需要加载的文件列表
     * @return
     */
    private CollBookBean convertCollBook(File file) {
//        CollBookBean collBook = new CollBookBean;
        //判断文件是否存在
        if (!file.exists()) return null;

        CollBookBean collBook = new CollBookBean();
        collBook.set_id(MD5Utils.strToMd5By16(file.getAbsolutePath()));
        collBook.setTitle(file.getName().replace(".txt", ""));
        collBook.setAuthor("");
        collBook.setShortIntro("无");
        collBook.setCover(file.getAbsolutePath());
        collBook.setLocal(true);
        collBook.setLastChapter("开始阅读");
        collBook.setUpdated(StringUtils.dateConvert(file.lastModified(), Constant.FORMAT_BOOK_DATE));
        collBook.setLastRead(StringUtils.
                dateConvert(System.currentTimeMillis(), Constant.FORMAT_BOOK_DATE));
        return collBook;
    }
}
