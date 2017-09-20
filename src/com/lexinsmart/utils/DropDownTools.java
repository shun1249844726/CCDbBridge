package com.lexinsmart.utils;

/**
 * 一些数据库下拉菜单中解析成明码
 * 
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
			} else if (messyton.equals("40285a8d57fea8b501581e8cc3445459")) {
				xzdw = 27f;
			} else if (messyton.equals("40285a8d57fea8b501581e8cc344545c")) {
				xzdw = 31f;
			} else if (messyton.equals("40285a8d57fea8b501581e8cc344545a")) {
				xzdw = 35f;
			} else if (messyton.equals("40285a8d57fea8b501581e8cc344545b")) {
				xzdw = 36f;

			} else if (messyton.equals("40285a8d57fea8b501581e8cc344545d")) {
				xzdw = 42f;
			} else if (messyton.equals("40285a8d57fea8b501581e8cc344545e")) {
				xzdw = 43f;
			} else if (messyton.equals("40285a8d57fea8b501581e8cc344545f")) {
				xzdw = 46f;
			} else if (messyton.equals("40285a8d57fea8b501581e8cc3455460")) {
				xzdw = 49f;
			} else {
				xzdw = 0f;
			}
		}
		return xzdw;
	}

	public static String stringToSex(String messysex) {
		String sex = "";
		if (messysex != null) {
			if (messysex.equals("40285a8d4d459849014d45cbfcd60193")) {
				sex = "男";
			} else if (messysex.equals("40285a8d4d459849014d45cbfcd60194")) {
				sex = "女";
			} else {
				sex = "不限";
			}
		}
		return sex;
	}

	public static String stringToType(String messytype) {
		String type = "";

		if (messytype != null) {
			if (messytype.equals("40285a8d55c3ffed0155e226f71356b0")) {
				type = "劳务";
			} else if (messytype.equals("40285a8d55c3ffed0155e226f71356b1")) {
				type = "包商";
			} else if (messytype.equals("40285a8d55c3ffed0155e226f71356b2")) {
				type = "人事";
			} else if (messytype.equals("40285a8d5610857701561fbe7d122bb2")) {
				type = "司机";
			} else if (messytype.equals("40285a8d57d51e140157dac717702f44")) {
				type = "其他";
			}
		}
		return type;
	}

	public static String stringToState(String messystate) {
		String state = "";
		if (messystate != null) {
			if (messystate.equals("402864d149df48ff0149df70dc7b0007")) {
				state = "正常";
			} else if (messystate.equals("402864d149df48ff0149df70dc7b0008")) {
				state = "黑名单";
			}
		}
		return state;
	}
}
