package data;

import java.awt.Color;
import java.awt.Font;

public class Data {
	public static final String CHU = "楚";
	public static final String HE = "河";
	public static final String HAN = "漢";
	public static final String JIE = "界";
	public static final String HONG_JU = "車";
	public static final String HONG_MA = "馬";
	public static final String HONG_PAO = "砲";
	public static final String HONG_XIANG = "相";
	public static final String HONG_SHI = "仕";
	public static final String HONG_SHUAI = "帥";
	public static final String HONG_BING = "兵";
	public static final String HEI_JU = "車";
	public static final String HEI_MA = "馬";
	public static final String HEI_PAO = "砲";
	public static final String HEI_XIANG = "象";
	public static final String HEI_SHI = "士";
	public static final String HEI_JIANG = "將";
	public static final String HEI_ZU = "卒";
	public static final String NEW = "新局";
	public static final String SET = "摆子";
	public static final String CLEAR = "清空";
	public static final String START = "初始";
	public static final String FINISH = "完成";
	public static final String ZOOMIN = "放大";
	public static final String ZOOMOUT = "缩小";
	public static final Color RED = new Color(0xff1f1f);
	public static final Color BLACK = new Color(0x000000);
	private static int unit;
	private static int border;
	private static Font font;
	static {
		Data.unit = 100;
		Data.border = Data.getUnit() * 3 / 20;
		Data.font = new Font("Kaiti SC", Font.PLAIN, Data.getUnit() / 2);
	}

	public static void setUnit(int scale) {
		Data.unit = Data.unit + scale * 10;
		Data.unit = Data.unit < 50 ? 50 : Data.unit;
		Data.unit = Data.unit > 100 ? 100 : Data.unit;
		Data.border = Data.unit * 3 / 20;
		Data.font = Data.getFont().deriveFont(Data.unit / 2f);
	}

	public static int getUnit() {
		return Data.unit;
	}

	public static int getBorder() {
		return Data.border;
	}

	public static Font getFont() {
		return Data.font;
	}
}