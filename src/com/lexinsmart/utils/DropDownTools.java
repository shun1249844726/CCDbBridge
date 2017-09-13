package com.lexinsmart.utils;

/**
 * 一些数据库下拉菜单中解析成明码
 * @author xushun
 *
 */
public class DropDownTools {

		public static Float stringToTon(String messyton) {
			Float xzdw = 0.0f;
			if (messyton != null) {
				if (messyton.equals("40285a8d57fea8b501581e8cc3445457")) {
					xzdw = 18f;
				} else if (messyton.equals("40285a8d57fea8b501581e8cc3445458")) {
					xzdw = 25f;
				} else if(messyton.equals("40285a8d57fea8b501581e8cc3445459")) {
					xzdw = 27f;
				} else if (messyton.equals("40285a8d57fea8b501581e8cc344545c")) {
					xzdw = 31f;
				}else if (messyton.equals("40285a8d57fea8b501581e8cc344545a")) {
					xzdw = 35f;
				}else if(messyton.equals("40285a8d57fea8b501581e8cc344545b")){
					xzdw = 36f;

				}else if(messyton.equals("40285a8d57fea8b501581e8cc344545d")){
					xzdw = 42f;
				}else if(messyton.equals("40285a8d57fea8b501581e8cc344545e")){
					xzdw = 43f;
				}else if(messyton.equals("40285a8d57fea8b501581e8cc344545f")){
					xzdw = 46f;
				}else if(messyton.equals("40285a8d57fea8b501581e8cc3455460")){
					xzdw = 49f;
				}else{
					xzdw = 0f;
				}
			}
			return xzdw;
		}
	}

