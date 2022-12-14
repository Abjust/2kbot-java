// 2kbit Java Edition，2kbit的Java分支版本
// Copyright(C) 2022 Abjust 版权所有。

// 本程序是自由软件：你可以根据自由软件基金会发布的GNU Affero通用公共许可证的条款，即许可证的第3版或（您选择的）任何后来的版本重新发布它和/或修改它。。

// 本程序的发布是希望它能起到作用。但没有任何保证；甚至没有隐含的保证。本程序的分发是希望它是有用的，但没有任何保证，甚至没有隐含的适销对路或适合某一特定目的的保证。 参见 GNU Affero通用公共许可证了解更多细节。

// 您应该已经收到了一份GNU Affero通用公共许可证的副本。 如果没有，请参见<https://www.gnu.org/licenses/>。

// 致所有构建及修改2kbit代码片段的用户：作者（Abjust）并不承担构建2kbit代码片段（包括修改过的版本）所产生的一切风险，但是用户有权在2kbit的GitHub项目页提出issue，并有权在代码片段修复这些问题后获取这些更新，但是，作者不会对修改过的代码版本做质量保证，也没有义务修正在修改过的代码片段中存在的任何缺陷。

package com.java_2kbit;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.message.data.MessageChain;

import java.math.BigDecimal;
import java.util.Objects;

public class Mathematics {
    public static void Execute(long group, MessageChain messageChain) {
        if (messageChain.contentToString().equals("!calc")) {
            try {
                Objects.requireNonNull(Bot.getInstance(Global.bot_qq).getGroup(group)).sendMessage("""
                        2kbot数学计算器
                        指令前缀为：/calc
                        在前缀后跟上空格可输入算式，例如：
                        基本运算
                        加法：<数字>+<数字>
                        减法：<数字>-<数字>
                        乘法：<数字>*<数字>
                        除法：<数字>/<数字>
                        乘方：<数字>^<数字>
                        三角函数运算
                        正弦：sin<数字>
                        余弦：cos<数字>
                        正切：tan<数字>
                        余切：cot<数字>
                        """);
            } catch (Exception ex) {
                System.out.println("群消息发送失败");
            }
        } else {
            String formula = messageChain.contentToString().split(" ")[1];
            boolean is_int = true;
            boolean negative = false;
            double double_ans = 0;
            BigDecimal decimal_ans = BigDecimal.valueOf(0);
            long int_ans = 0;
            double operand;
            String[] operands;
            // 基本运算
            if (formula.contains("+")) {
                operands = formula.split("\\+");
                try {
                    for (String number : operands) {
                        if (Double.parseDouble(number.replace("n", "")) % 1 != 0) {
                            is_int = false;
                            break;
                        }
                    }
                    for (int i = 0; i < operands.length; i++) {
                        if (is_int) {
                            if (i == 0) {
                                if (operands[i].contains("n")) {
                                    int_ans = -Integer.parseInt(operands[i]);
                                } else {
                                    int_ans = Integer.parseInt(operands[i]);
                                }
                            } else {
                                if (operands[i].contains("n")) {
                                    int_ans += -Integer.parseInt(operands[i]);
                                } else {
                                    int_ans += Integer.parseInt(operands[i]);
                                }
                            }
                        } else {
                            if (i == 0) {
                                if (operands[i].contains("n")) {
                                    decimal_ans = BigDecimal.valueOf(-Double.parseDouble(operands[i]));
                                } else {
                                    decimal_ans = BigDecimal.valueOf(Double.parseDouble(operands[i]));
                                }
                            } else {
                                if (operands[i].contains("n")) {
                                    decimal_ans = decimal_ans.add(BigDecimal.valueOf(-Double.parseDouble(operands[i])));
                                } else {
                                    decimal_ans = decimal_ans.add(BigDecimal.valueOf(Double.parseDouble(operands[i])));
                                }
                            }
                        }
                    }
                    if (is_int) {
                        try {
                            Objects.requireNonNull(Bot.getInstance(Global.bot_qq).getGroup(group)).sendMessage(String.format("这个算式的答案是：%s", int_ans));
                        } catch (Exception ex1) {
                            System.out.println("群消息发送失败");
                        }
                    } else {
                        try {
                            Objects.requireNonNull(Bot.getInstance(Global.bot_qq).getGroup(group)).sendMessage(String.format("这个算式的答案是：%s", decimal_ans));
                        } catch (Exception ex1) {
                            System.out.println("群消息发送失败");
                        }
                    }
                } catch (Exception ex) {
                    try {
                        Objects.requireNonNull(Bot.getInstance(Global.bot_qq).getGroup(group)).sendMessage("计算失败");
                    } catch (Exception ex1) {
                        System.out.println("群消息发送失败");
                    }
                }
            } else if (formula.contains("-")) {
                operands = formula.split("-");
                try {
                    for (String number : operands) {
                        if (Double.parseDouble(number) % 1 != 0) {
                            is_int = false;
                            break;
                        }
                    }
                    for (int i = 0; i < operands.length; i++) {
                        if (is_int) {
                            if (i == 0) {
                                if (operands[i].contains("n")) {
                                    int_ans = -Integer.parseInt(operands[i]);
                                } else {
                                    int_ans = Integer.parseInt(operands[i]);
                                }
                            } else {
                                if (operands[i].contains("n")) {
                                    int_ans -= -Integer.parseInt(operands[i]);
                                } else {
                                    int_ans -= Integer.parseInt(operands[i]);
                                }
                            }
                        } else {
                            if (i == 0) {
                                if (operands[i].contains("n")) {
                                    decimal_ans = BigDecimal.valueOf(-Double.parseDouble(operands[i]));
                                } else {
                                    decimal_ans = BigDecimal.valueOf(Double.parseDouble(operands[i]));
                                }
                            } else {
                                if (operands[i].contains("n")) {
                                    decimal_ans = decimal_ans.subtract(BigDecimal.valueOf(-Double.parseDouble(operands[i])));
                                } else {
                                    decimal_ans = decimal_ans.subtract(BigDecimal.valueOf(Double.parseDouble(operands[i])));
                                }
                            }
                        }
                    }
                    if (is_int) {
                        try {
                            Objects.requireNonNull(Bot.getInstance(Global.bot_qq).getGroup(group)).sendMessage(String.format("这个算式的答案是：%s", int_ans));
                        } catch (Exception ex1) {
                            System.out.println("群消息发送失败");
                        }
                    } else {
                        try {
                            Objects.requireNonNull(Bot.getInstance(Global.bot_qq).getGroup(group)).sendMessage(String.format("这个算式的答案是：%s", decimal_ans));
                        } catch (Exception ex1) {
                            System.out.println("群消息发送失败");
                        }
                    }
                } catch (Exception ex) {
                    try {
                        Objects.requireNonNull(Bot.getInstance(Global.bot_qq).getGroup(group)).sendMessage("计算失败");
                    } catch (Exception ex1) {
                        System.out.println("群消息发送失败");
                    }
                }
            } else if (formula.contains("*")) {
                operands = formula.split("\\*");
                try {
                    for (String number : operands) {
                        if (Double.parseDouble(number) % 1 != 0) {
                            is_int = false;
                            break;
                        }
                    }
                    for (int i = 0; i < operands.length; i++) {
                        if (is_int) {
                            if (i == 0) {
                                if (operands[i].contains("n")) {
                                    int_ans = -Integer.parseInt(operands[i]);
                                } else {
                                    int_ans = Integer.parseInt(operands[i]);
                                }
                            } else {
                                if (operands[i].contains("n")) {
                                    int_ans *= -Integer.parseInt(operands[i]);
                                } else {
                                    int_ans *= Integer.parseInt(operands[i]);
                                }
                            }
                        } else {
                            if (i == 0) {
                                if (operands[i].contains("n")) {
                                    decimal_ans = BigDecimal.valueOf(-Double.parseDouble(operands[i]));
                                } else {
                                    decimal_ans = BigDecimal.valueOf(Double.parseDouble(operands[i]));
                                }
                            } else {
                                if (operands[i].contains("n")) {
                                    decimal_ans = decimal_ans.multiply(BigDecimal.valueOf(-Double.parseDouble(operands[i])));
                                } else {
                                    decimal_ans = decimal_ans.multiply(BigDecimal.valueOf(Double.parseDouble(operands[i])));
                                }
                            }
                        }
                    }
                    if (is_int) {
                        try {
                            Objects.requireNonNull(Bot.getInstance(Global.bot_qq).getGroup(group)).sendMessage(String.format("这个算式的答案是：%s", int_ans));
                        } catch (Exception ex1) {
                            System.out.println("群消息发送失败");
                        }
                    } else {
                        try {
                            Objects.requireNonNull(Bot.getInstance(Global.bot_qq).getGroup(group)).sendMessage(String.format("这个算式的答案是：%s", decimal_ans));
                        } catch (Exception ex1) {
                            System.out.println("群消息发送失败");
                        }
                    }
                } catch (Exception ex) {
                    try {
                        Objects.requireNonNull(Bot.getInstance(Global.bot_qq).getGroup(group)).sendMessage("计算失败");
                    } catch (Exception ex1) {
                        System.out.println("群消息发送失败");
                    }
                }
            } else if (formula.contains("/")) {
                operands = formula.split("/");
                try {
                    for (String number : operands) {
                        if (Double.parseDouble(number) % 1 != 0) {
                            is_int = false;
                            break;
                        }
                    }
                    for (int i = 0; i < operands.length; i++) {
                        if (is_int) {
                            if (i == 0) {
                                if (operands[i].contains("n")) {
                                    int_ans = -Integer.parseInt(operands[i]);
                                } else {
                                    int_ans = Integer.parseInt(operands[i]);
                                }
                            } else {
                                if (operands[i].contains("n")) {
                                    int_ans /= -Integer.parseInt(operands[i]);
                                } else {
                                    int_ans /= Integer.parseInt(operands[i]);
                                }
                            }
                        } else {
                            if (i == 0) {
                                if (operands[i].contains("n")) {
                                    decimal_ans = BigDecimal.valueOf(-Double.parseDouble(operands[i]));
                                } else {
                                    decimal_ans = BigDecimal.valueOf(Double.parseDouble(operands[i]));
                                }
                            } else {
                                if (operands[i].contains("n")) {
                                    decimal_ans = decimal_ans.divide(BigDecimal.valueOf(-Double.parseDouble(operands[i])));
                                } else {
                                    decimal_ans = decimal_ans.divide(BigDecimal.valueOf(Double.parseDouble(operands[i])));
                                }
                            }
                        }
                    }
                    if (is_int) {
                        try {
                            Objects.requireNonNull(Bot.getInstance(Global.bot_qq).getGroup(group)).sendMessage(String.format("这个算式的答案是：%s", int_ans));
                        } catch (Exception ex1) {
                            System.out.println("群消息发送失败");
                        }
                    } else {
                        try {
                            Objects.requireNonNull(Bot.getInstance(Global.bot_qq).getGroup(group)).sendMessage(String.format("这个算式的答案是：%s", decimal_ans));
                        } catch (Exception ex1) {
                            System.out.println("群消息发送失败");
                        }
                    }
                } catch (Exception ex) {
                    try {
                        Objects.requireNonNull(Bot.getInstance(Global.bot_qq).getGroup(group)).sendMessage("计算失败");
                    } catch (Exception ex1) {
                        System.out.println("群消息发送失败");
                    }
                }
            } else if (formula.contains("^")) {
                operands = formula.split("\\^");
                try {
                    for (String number : operands) {
                        if (!number.contains("n") && Double.parseDouble(number) % 1 != 0) {
                            is_int = false;
                            break;
                        } else if (operands[0].contains("n")) {
                            negative = true;
                        } else if (operands[1].contains("n")) {
                            negative = true;
                            is_int = false;
                            break;
                        }
                    }
                    for (int i = 0; i < operands.length; i++) {
                        if (is_int) {
                            if (i == 0) {
                                if (!negative) {
                                    int_ans = Integer.parseInt(operands[i]);
                                } else {
                                    int_ans = -Integer.parseInt(operands[i]);
                                }
                            } else {
                                int_ans = (long) Math.pow(int_ans, Integer.parseInt(operands[i]));
                            }
                        } else if (negative) {
                            if (i == 0) {
                                double_ans = Double.parseDouble(operands[i].replace("n", ""));
                            } else {
                                double_ans = Math.pow(double_ans, -Integer.parseInt(operands[i].replace("n", "")));
                            }
                        } else {
                            if (i == 0) {
                                decimal_ans = BigDecimal.valueOf(Double.parseDouble(operands[i]));
                            } else {
                                decimal_ans = BigDecimal.valueOf(Math.pow(decimal_ans.doubleValue(), Double.parseDouble(operands[i])));
                            }
                        }
                    }
                    if (is_int) {
                        try {
                            Objects.requireNonNull(Bot.getInstance(Global.bot_qq).getGroup(group)).sendMessage(String.format("这个算式的答案是：%s", int_ans));
                        } catch (Exception ex1) {
                            System.out.println("群消息发送失败");
                        }
                    } else {
                        try {
                            Objects.requireNonNull(Bot.getInstance(Global.bot_qq).getGroup(group)).sendMessage(String.format("这个算式的答案是：%s", decimal_ans));
                        } catch (Exception ex1) {
                            System.out.println("群消息发送失败");
                        }
                    }
                } catch (Exception ex) {
                    try {
                        Objects.requireNonNull(Bot.getInstance(Global.bot_qq).getGroup(group)).sendMessage("计算失败");
                    } catch (Exception ex1) {
                        System.out.println("群消息发送失败");
                    }
                }
            }
            // 三角函数运算
            else if (formula.startsWith("sin")) {
                try {
                    operand = Double.parseDouble(formula.split("sin")[0]);
                    decimal_ans = BigDecimal.valueOf(Math.sin(operand * Math.PI / 180));
                    try {
                        Objects.requireNonNull(Bot.getInstance(Global.bot_qq).getGroup(group)).sendMessage(String.format("这个算式的答案是：%s", decimal_ans));
                    } catch (Exception ex1) {
                        System.out.println("群消息发送失败");
                    }
                } catch (Exception ex) {
                    try {
                        Objects.requireNonNull(Bot.getInstance(Global.bot_qq).getGroup(group)).sendMessage("计算失败");
                    } catch (Exception ex1) {
                        System.out.println("群消息发送失败");
                    }
                }
            } else if (formula.startsWith("cos")) {
                try {
                    operand = Double.parseDouble(formula.split("cos")[1]);
                    decimal_ans = BigDecimal.valueOf(Math.cos(operand * Math.PI / 180));
                    try {
                        Objects.requireNonNull(Bot.getInstance(Global.bot_qq).getGroup(group)).sendMessage(String.format("这个算式的答案是：%s", decimal_ans));
                    } catch (Exception ex1) {
                        System.out.println("群消息发送失败");
                    }
                } catch (Exception ex) {
                    try {
                        Objects.requireNonNull(Bot.getInstance(Global.bot_qq).getGroup(group)).sendMessage("计算失败");
                    } catch (Exception ex1) {
                        System.out.println("群消息发送失败");
                    }
                }
            } else if (formula.startsWith("tan")) {
                try {
                    operand = Double.parseDouble(formula.split("tan")[1]);
                    decimal_ans = BigDecimal.valueOf(Math.tan(operand * Math.PI / 180));
                    try {
                        Objects.requireNonNull(Bot.getInstance(Global.bot_qq).getGroup(group)).sendMessage(String.format("这个算式的答案是：%s", decimal_ans));
                    } catch (Exception ex1) {
                        System.out.println("群消息发送失败");
                    }
                } catch (Exception ex) {
                    try {
                        Objects.requireNonNull(Bot.getInstance(Global.bot_qq).getGroup(group)).sendMessage("计算失败");
                    } catch (Exception ex1) {
                        System.out.println("群消息发送失败");
                    }
                }
            } else if (formula.startsWith("cot")) {
                try {
                    operand = Double.parseDouble(formula.split("cot")[1]);
                    decimal_ans = BigDecimal.valueOf(1 / Math.tan(operand * Math.PI / 180));
                    try {
                        Objects.requireNonNull(Bot.getInstance(Global.bot_qq).getGroup(group)).sendMessage(String.format("这个算式的答案是：%s", decimal_ans));
                    } catch (Exception ex1) {
                        System.out.println("群消息发送失败");
                    }
                } catch (Exception ex) {
                    try {
                        Objects.requireNonNull(Bot.getInstance(Global.bot_qq).getGroup(group)).sendMessage("计算失败");
                    } catch (Exception ex1) {
                        System.out.println("群消息发送失败");
                    }
                }
            }
            // 如果不是算式
            else {
                try {
                    Objects.requireNonNull(Bot.getInstance(Global.bot_qq).getGroup(group)).sendMessage("参数错误");
                } catch (Exception ex1) {
                    System.out.println("群消息发送失败");
                }
            }
        }
    }
}
