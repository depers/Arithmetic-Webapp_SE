# arithmetic_webapp_se
> 201571030107/201571030121《小学四则运算练习软件》结对项目

## 项目Github地址
[Arithmetic-Webapp_SE](https://github.com/depers/Arithmetic-Webapp_SE)

## 1、需求分析
* 由计算机从题库文件中随机选择20道加减乘除混合算式，用户输入算式答案，程序检查答案是否正确，每道题正确计5分，错误不计分，20道题测试结束后给出测试总分。
* 程序为用户提供四则运算练习功能：百以内整数算式+带括号算式。
* 程序允许用户进行多轮测试，提供用户多轮测试分数柱状图，示例如下：
* 程序记录用户答题结果，当程序退出再启动的时候，可为用户显示24小时内参与测试的成绩统计。
* 测试有计时功能，测试时动态显示用户开始答题后的消耗时间。
* 该程序为Web端程序，可供用户在线使用。

## 2、软件设计
[系统UML](https://images2018.cnblogs.com/blog/1350176/201804/1350176-20180402225301848-895755394.png)
<img src="https://images2018.cnblogs.com/blog/1350176/201804/1350176-20180402225301848-895755394.png" width = "100%" height = "100%" alt="图片名称" align=center />

## 3、核心代码
* 将算术表达式（中缀表达式）转换为后缀表达式：
>  eg. 89-(44/(56\*33)) 中缀表达式转换为后缀表达式
> 后缀表达式：89 44 56 33 * / -，元素之间用空格隔开。
> 从左到右遍历中缀表达式的每一个数字和运算符 
> 如果是数字就输出（即存入后缀表达式） 
> 如果是右括号，则弹出左括号之前的运算符 
> 如果优先级低于栈顶运算符，则弹出栈顶运算符加入后缀表达式，并将当前运算符加入后缀表达式
> 遍历结束后，将栈则剩余运算符弹出。 


```
public static String prefixToSuffix(String express) throws Exception {

        String[] expression = express.split(",");
        StringBuffer suffixStr = new StringBuffer();
        String temp = "";

        try {
            for (int i = 0; i < expression.length; i++) {
                if (SuffixToValueUtil.isNum(expression[i])) {
                    suffixStr.append(expression[i]).append(" ");
                } else if (expression[i].equals(")")) {
                    temp = stack.pop();
                    while (!temp.equals("(")) {
                        suffixStr.append(temp).append(" ");
                        temp = stack.pop();
                    }
                } else if (expression[i].equals("(") ||
                        priority.get(expression[i]) >= priority.get(getTopOperator())) {

                    stack.push(expression[i]);
                } else {
                    temp = stack.pop();
                    suffixStr.append(temp).append(" ").append(expression[i]).append(" ");
                }

            }
        } catch (Exception e) {
            throw e;
        }
        while (stack.size() > 0) {
            suffixStr.append(stack.pop());
        }

        return suffixStr.toString();
    }
```

* 根据中缀表达式计算结果
>  eg. 根据后缀表达式：89 44 56 33 * / - 计算结果。
>  从左到右遍历后缀表达式 
>  遇到数字就进栈 
>   遇到符号，就将栈顶的两个数字出栈运算，运算结果进栈，直到获得最终结果。

```
public static int compute(String expression) throws Exception {
        int numOne, numTwo;
        String temp = "";
        String[] express = expression.split(" ");
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < express.length; i++){
            if (isNum(express[i])){
                stack.push(express[i]);
            }
            else
            {
                numTwo = Integer.parseInt(stack.pop());
                numOne = Integer.parseInt(stack.pop());
                temp = CalculateUtil.compute(numOne, numTwo, express[i]).toString();

                if (Integer.parseInt(temp) < 0){
                    throw new Exception("Negative numbers in operation");
                }
                stack.push(temp);
            }
        }

        return Integer.parseInt(stack.pop());
    }
```

## 4、程序运行
<img src="https://images2018.cnblogs.com/blog/1350176/201804/1350176-20180402225335843-1084008651.png" width = "100%" height = "100%" alt="图片名称" align=center />
<img src="https://images2018.cnblogs.com/blog/1350176/201804/1350176-20180402225343555-1468631841.png" width = "100%" height = "100%" alt="图片名称" align=center />
<img src="https://images2018.cnblogs.com/blog/1350176/201804/1350176-20180402225349905-943336654.png" width = "100%" height = "100%" alt="图片名称" align=center />
<img src="https://images2018.cnblogs.com/blog/1350176/201804/1350176-20180402225355230-567473352.png" width = "100%" height = "100%" alt="图片名称" align=center />

## 5、结对编程
![](https://images2018.cnblogs.com/blog/1350176/201804/1350176-20180403120344250-1898956324.jpg)

## 6、PSP
PSP2.1|任务内容|计划完成的时间(min)|实际完成需要的时间(min)
-------- | --------- | ------------ | ----------------------
**PLanning**|**计划**|**40**|**40**
Estimate|估计这个任务需要多少时间，并规划大致工作步骤|40|40
**Developmet**|**开发**|**162**|**180**
Analysis|需求分析（包括学习新技术）|12|22
Design Spec|生成设计文档|5|3
Design Revie|设计复审（和同事审核设计文档）|3|3
Coding Standard|代码规范|2|2
Design|具体设计|45|50
Coding|具体编码|75|70
Code Review|代码复审|5|5
Test|测试（自我测试，修改代码，提交修改）|15|25
**Reporting**|**报告**|**30**|**40**
Test Report|测试报告|20|30
Size Measurement|计算工作量|3|3
Postmortem & Process Improvement Plan|事后总结，并提出过程改机计划|7|7
## 7、使用汉堡评价法给小伙伴的一些点评
在本次项目中，我的小伙伴是马思远同学，这个项目相比于上一个项目来说主要增加了对图形用户界面的要求。刚开始思远同学建议我们采用Java的图形界面库来做软件的界面。而我又觉得那个界面有点土，我认为web界面效果会比较好。所以这里我们俩儿就起了冲突。在我向他详细介绍了采用web界面的优势后，小马同意了我的建议，在两人合作编程中，两个人有意见上的冲突，通过多交流冲突就会迎刃而解。本次项目感谢思远同学和我的默契合作。
## 8、结对编程的感受
首先，本次项目的规模相比于上一次大了不少。所以结队编程的好处也就是提高了编码和效率，减轻了工作量。其次两个人不一样的编码方式，思维方式和语言沟通都不相同，通过项目的磨合，增加了我们在将来实际项目中的协作能力和工作经验。