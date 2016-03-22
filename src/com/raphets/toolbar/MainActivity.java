package com.raphets.toolbar;

import com.raphets.toolbar.MyToolBar.MyToolBarClickListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {
	private MyToolBar myToolBar;// 自定义toolbar

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化视图
		initView();
		// 初始化数据
		initData();
		// 事件监听
		initListener();

	}

	/*
	 * 初始化视图
	 */
	private void initView() {
		setContentView(R.layout.activity_main);
		myToolBar = (MyToolBar) findViewById(R.id.myToolBar);
	}

	/*
	 * 初始化数据
	 */
	private void initData() {
		// 设置左边右边的按钮是否显示
		myToolBar.setToolBarBtnVisiable(false, true);
		// 设置是否显示中间标题，默认的是显示
		myToolBar.setToolBarTitleVisible(true);
	}

	/*
	 * 事件监听
	 */
	private void initListener() {
		/*
		 * toolbar的点击事件处理
		 */
		myToolBar.setOnMyToolBarClickListener(new MyToolBarClickListener() {

			@Override
			public void rightBtnClick() {// 右边按钮点击事件
				Toast.makeText(MainActivity.this, "菜单", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void leftBtnClick() {// 左边按钮点击事件
				Toast.makeText(MainActivity.this, "返回", Toast.LENGTH_SHORT).show();

			}
		});
	}

}
