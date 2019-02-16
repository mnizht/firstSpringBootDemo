package com.example.demo;

/**
 * author zhuhaitao
 * date 2019/2/16 11:08
 **/
public class MoneyForm {

  public static void main(String[] args) {
    long money = 1105055L;
    System.out.println(digitUppercase(money));
  }

  //单位，补在每位数字之后，根据数字某些零后的单位要省略
  private static final String[] UNIT = {"万", "仟", "佰", "拾", "亿", "仟", "佰",
    "拾", "万", "仟", "佰", "拾", "元", "角", "分"};

  private static final String[] NUM = {"零", "壹", "贰", "叁", "肆", "伍", "陆",
    "柒", "捌", "玖"};
  //单位预设了15个字符，所以设定数字最大为15位
  private static final long MAX_VALUE = 999999999999999L;

  private static String digitUppercase(long money) {
    if (money < 0 || money > MAX_VALUE)
      return "参数非法!";
    if (money == 0)
      return "零元整";
    String strMoney = String.valueOf(money);
    int numIndex = 0; // numIndex用于选择金额数值
    int unitIndex = UNIT.length - strMoney.length(); // unitIndex用于选择金额单位
    boolean isZero = false; // 用于判断当前为是否为零
    StringBuilder result = new StringBuilder();
    for (; numIndex < strMoney.length(); numIndex++, unitIndex++) {
      char num = strMoney.charAt(numIndex);
      if (num == '0') {
        isZero = true;
        if (UNIT[unitIndex].equals("亿") || UNIT[unitIndex].equals("万")
          || UNIT[unitIndex].equals("元")) { // 如果当前位是亿、万、元，且数值为零
          result.append(UNIT[unitIndex]); //补单位亿、万、元
          isZero = false;
        }
      } else {
        if (isZero) {
          result.append("零");
          isZero = false;
        }
        result.append(NUM[Integer.parseInt(String.valueOf(num))]).append(UNIT[unitIndex]);
      }
    }
    //不是角分结尾就加"整"字
    if (!result.toString().endsWith("角") && !result.toString().endsWith("分")) {
      result.append("整");
    }
    //例如没有这行代码，数值"400000001101.2"，输出就是"肆千亿万壹千壹佰零壹元贰角"
    return result.toString().replaceAll("亿万", "亿");

  }
}
