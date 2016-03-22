package com.raphets.toolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/*
 * 自定义ToolBar，继承自RelativeLayout
 **/
public class MyToolBar extends RelativeLayout {

	private Button leftBtn, rightBtn; // 左边和右边的按钮
	private TextView tvTitle; // 中间的标题

	/*
	 * 中间标题的属性
	 */
	private String title; // 标题
	private boolean showTitle; // 是否显示标题
	private float titleSize; // 标题字体大小
	private int titleColor; // 标题字体颜色

	/*
	 * 左边按钮的属性
	 */
	private String leftBtnText;// 左边按钮的文字
	private Drawable leftBtnBackground;// 左边按钮的背景
	private boolean showLeftBtn;// 是否显示左边按钮
	private int leftBtnTextColor;// 左边按钮字体颜色
	private float leftBtnTextSize;// 左边按钮字体大小

	/*
	 * 右边按钮的属性
	 */
	private String rightBtnText;// 右边按钮的文字
	private Drawable rightBtnBackground;// 右边按钮的背景
	private boolean showRightBtn;// 是否显示右边按钮
	private int rightBtnTextColor;// 右边按钮字体颜色
	private float rightBtnTextSize;// 右边按钮字体大小

	/*
	 * 三个控件的位置配置参数，右边按钮的位置
	 */
	private LayoutParams titleParams;// 标题的位置配置参数
	private LayoutParams leftParams;// 左边按钮的位置配置参数
	private LayoutParams rightParams;// 右边按钮的位置配置参数
	// 事件监听器
	private MyToolBarClickListener listener;

	/*
	 * 在构造函数里完成自定义toolbar的属性设置
	 */
	public MyToolBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyToolBar);
		/*
		 * 获取中间标题的自定义属性设置以及默认值
		 */
		title = typedArray.getString(R.styleable.MyToolBar_textTitle);
		showTitle = typedArray.getBoolean(R.styleable.MyToolBar_showTitle, true);
		titleSize = typedArray.getDimension(R.styleable.MyToolBar_titleTextSize, 0);
		titleColor = typedArray.getColor(R.styleable.MyToolBar_titleTextColor, 0);
		/*
		 * 获取左边按钮的自定义属性设置以及默认值
		 */
		leftBtnText = typedArray.getString(R.styleable.MyToolBar_leftButtonText);
		leftBtnTextColor = typedArray.getColor(R.styleable.MyToolBar_leftButtonTextColor, 0);
		leftBtnBackground = typedArray.getDrawable(R.styleable.MyToolBar_leftButtonBackground);
		leftBtnTextSize = typedArray.getDimension(R.styleable.MyToolBar_leftButtonTextSize, 0);
		/*
		 * 获取右边按钮的自定义属性设置以及默认值
		 */
		rightBtnText = typedArray.getString(R.styleable.MyToolBar_rightButtonText);
		rightBtnTextColor = typedArray.getColor(R.styleable.MyToolBar_rightButtonTextColor, 0);
		rightBtnBackground = typedArray.getDrawable(R.styleable.MyToolBar_rightButtonBackground);
		rightBtnTextSize = typedArray.getDimension(R.styleable.MyToolBar_rightButtonTextSize, 0);
		// 回收资源
		typedArray.recycle();

		/*
		 * 创建三个控件的对象
		 */
		leftBtn = new Button(context);
		rightBtn = new Button(context);
		tvTitle = new TextView(context);
		/*
		 * 设置标题的属性
		 */
		tvTitle.setText(title);
		tvTitle.setTextColor(titleColor);
		tvTitle.setTextSize(titleSize);
		tvTitle.setGravity(Gravity.CENTER);
		/*
		 * 设置左边按钮的属性
		 */
		leftBtn.setText(leftBtnText);
		leftBtn.setTextColor(leftBtnTextColor);
		leftBtn.setBackground(leftBtnBackground);
		leftBtn.setTextSize(leftBtnTextSize);
		/*
		 * 设置右边按钮的属性
		 */
		rightBtn.setText(rightBtnText);
		rightBtn.setTextColor(rightBtnTextColor);
		rightBtn.setBackground(rightBtnBackground);
		rightBtn.setTextSize(rightBtnTextSize);
		// 设置自定义toolbar的背景色
		setBackgroundColor(Color.WHITE);

		// 中间标题的位置参数 配置
		titleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		titleParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		// 向RelativeLayout中添加控件
		addView(tvTitle, titleParams);

		// 左边按钮的位置参数 配置
		leftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		// 向RelativeLayout中添加控件
		addView(leftBtn, leftParams);

		// 右边按钮的位置参数 配置
		rightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		// 向RelativeLayout中添加控件
		addView(rightBtn, rightParams);

		/*
		 * 左边按钮点击事件
		 */
		leftBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				listener.leftBtnClick();

			}
		});
		/*
		 * 右边按钮点击事件
		 */
		rightBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				listener.rightBtnClick();

			}
		});
	}

	/*
	 * 定义接口
	 */
	public interface MyToolBarClickListener {
		public void leftBtnClick();// 左边按钮点击事件

		public void rightBtnClick();// 右边按钮点击事件
	}

	/*
	 * 自定义的toolbar的事件监听
	 */
	public void setOnMyToolBarClickListener(MyToolBarClickListener listener) {
		this.listener = listener;
	}

	/*
	 * 设置自定义toolbar的左右两边按钮的是否显示，默认连个按钮都是显示的
	 */
	public void setToolBarBtnVisiable(boolean leftFalg, boolean rightFalg) {
		if (leftFalg && rightFalg) {
			leftBtn.setVisibility(View.VISIBLE);
			rightBtn.setVisibility(View.VISIBLE);
		}
		if (!leftFalg && !rightFalg) {
			leftBtn.setVisibility(View.GONE);
			rightBtn.setVisibility(View.GONE);
		}
		if (!leftFalg && rightFalg) {
			leftBtn.setVisibility(View.GONE);
			rightBtn.setVisibility(View.VISIBLE);
		}
		if (leftFalg && !rightFalg) {
			leftBtn.setVisibility(View.VISIBLE);
			rightBtn.setVisibility(View.GONE);
		}

	}

	/*
	 * 设置自定义的toolbar是否显示标题，默认是有标题的
	 */
	public void setToolBarTitleVisible(boolean flag) {
		if (flag) {
			tvTitle.setVisibility(View.VISIBLE);
		} else {
			tvTitle.setVisibility(View.GONE);
		}
	}
}
