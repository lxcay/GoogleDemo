package com.mwqi.utils;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;

public class DrawableUtils {

	/**
	 * 创建一个图片
	 * @param contentColor 内部填充颜色
	 * @param strokeColor  描边颜色
	 * @param radius       圆角
	 */
	public static GradientDrawable createDrawable(int contentColor, int strokeColor, int radius) {
		GradientDrawable drawable = new GradientDrawable(); // 生成Shape
		drawable.setGradientType(GradientDrawable.RECTANGLE); // 设置矩形
		drawable.setColor(contentColor);// 内容区域的颜色
		drawable.setStroke(1, strokeColor); // 四周描边,描边后四角真正为圆角，不会出现黑色阴影。如果父窗体是可以滑动的，需要把父View设置setScrollCache(false)
		drawable.setCornerRadius(radius); // 设置四角都为圆角
		return drawable;
	}

	/**
	 * 创建一个图片选择器
	 * @param normalState  普通状态的图片
	 * @param pressedState 按压状态的图片
	 */
	public static StateListDrawable createSelector(Drawable normalState, Drawable pressedState) {
		StateListDrawable bg = new StateListDrawable();
		bg.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, pressedState);
		bg.addState(new int[]{android.R.attr.state_enabled}, normalState);
		bg.addState(new int[]{}, normalState);
		return bg;
	}

	/**
	 * TextView设置不同状态时其文字颜色
	 * 创建一个文本颜色选择器
	 * @param normal 正常的
	 * @param pressed 按下的
	 * @param focused 获得焦点的
	 * @param unable 
	 * @return
	 */
    private ColorStateList createColorState(int normal, int pressed, int focused, int unable) {  
        int[] colors = new int[] { pressed, focused, normal, focused, unable, normal };  
        int[][] states = new int[6][];  
        states[0] = new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled };  
        states[1] = new int[] { android.R.attr.state_enabled, android.R.attr.state_focused };  
        states[2] = new int[] { android.R.attr.state_enabled };  
        states[3] = new int[] { android.R.attr.state_focused };  
        states[4] = new int[] { android.R.attr.state_window_focused };  
        states[5] = new int[] {};  
        ColorStateList colorList = new ColorStateList(states, colors);  
        return colorList;  
    }  

	/** 获取图片的大小 */
	public static int getDrawableSize(Drawable drawable) {
		if (drawable == null) {
			return 0;
		}
		Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
			return bitmap.getByteCount();
		} else {
			return bitmap.getRowBytes() * bitmap.getHeight();
		}
	}
}
