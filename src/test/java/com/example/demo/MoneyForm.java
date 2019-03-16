package com.example.demo;

/**
  * @author zhuhaitao
  * @date 2019/2/16 11:22
  **/
public class MoneyForm {

  /**
   * 单位，补在每位数字之后，根据数字某些零后的单位要省略
   */
  private static final String[] UNIT = {"万", "仟", "佰", "拾", "亿", "仟", "佰",
    "拾", "万", "仟", "佰", "拾", "元", "角", "分"};

  private static final String[] NUM = {"零", "壹", "贰", "叁", "肆", "伍", "陆",
    "柒", "捌", "玖"};
  /**
   * 单位预设了15个字符，所以设定数字最大为15位
   */
  private static final long MAX_VALUE = 999999999999999L;

  public static String digitUppercase(long money) {
    if (money < 0 || money > MAX_VALUE) {
      return "参数非法!";
    }
    if (money == 0) {
      return "零元整";
    }
    String strMoney = String.valueOf(money);
    // numIndex用于选择金额数值
    int numIndex = 0;
    // unitIndex用于选择金额单位
    int unitIndex = UNIT.length - strMoney.length();
    // 用于判断当前为是否为零
    boolean isZero = false;
    StringBuilder result = new StringBuilder();
    for (; numIndex < strMoney.length(); numIndex++, unitIndex++) {
      char num = strMoney.charAt(numIndex);
      if (num == '0') {
        isZero = true;
        // 如果当前位是亿、万、元，且数值为零
        if (UNIT[4].equals(UNIT[unitIndex]) || UNIT[0].equals(UNIT[unitIndex])
          || UNIT[12].equals(UNIT[unitIndex])) {
          //补单位亿、万、元
          result.append(UNIT[unitIndex]);
          isZero = false;
        }
      } else {
        if (isZero) {
          result.append("零");
          isZero = false;
        }
        int temp = Integer.parseInt(String.valueOf(num));
        if (temp >= 0 && temp <= 9 && unitIndex >= 0 && unitIndex <= 14) {
          result.append(NUM[temp]).append(UNIT[unitIndex]);
        } else {
          throw new NumberFormatException();
        }
      }
    }
    //不是角分结尾就加"整"字
    String jiao = "角";
    String fen = "分";
    if (!result.toString().endsWith(jiao) && !result.toString().endsWith(fen)) {
      result.append("整");
    }
    //例如没有这行代码，数值"400000001101.2"，输出就是"肆千亿万壹千壹佰零壹元贰角"
    return result.toString().replaceAll("亿万", "亿");
  }
}
