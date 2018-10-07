import java.util.Scanner;
import java.util.Stack;






public class Cal{
    private Stack<Long> numberStack = null;//数字栈
    private Stack<Character> symbolStack = null;//符号栈
    

    //去除空格
    private String removeStrSpace(String str) {
        return str != null ? str.replaceAll(" ", "") : "";
    }
    
    //是否是数字
    public boolean isNumber(char num){
    	if(num <= '9'&&num >= '0'){
    		return true;
    	}
    	else 
    		return false;
    }
    
    
    //算式的合法性
    public boolean correct(String number){
    	//表达式不为空
    	if(number == null || number.isEmpty())
    		return false;
    	//括号是否匹配
        Stack<Character> kuohao = new Stack<Character>(); 
    	int i = 0;
    	int j = 0;
    	for(i = 0;i<number.length();i++){
    		char n = number.charAt(i);
    		if(!((isNumber(n))
    				||("(".equals(n + ""))
    				||(")".equals(n + ""))
    				||("+".equals(n + ""))
    				||("-".equals(n + ""))
    				||("*".equals(n + ""))
    				||("/".equals(n + ""))
    				||("=".equals(n + ""))))
    			return false;
    		//左括号压栈
    		if(("(".equals(n + "")))
    			kuohao.push(n);
    		//右括号匹配
    		if((")".equals(n + "")))
    			if((kuohao.isEmpty())||!("(".equals(kuohao.pop() + "")))
    				return false;
    		//检验等号的数量
    		if(("=".equals(n + ""))){
       			j++;
    			if(j > 1){
    				return false;
    			}
    			return true;
    		}
    		
    	}
    	if(!(kuohao.isEmpty()))
    		return false;
    	if(!("=".equals(number.charAt(number.length()-1)+"")))
    		return false;
    	
    	return true;
    }
    	

    //优先级的比较
    public boolean first(char symbol){
    	if(symbolStack.isEmpty())
    		return true;
    	// 查看堆栈顶部的对象
        char top = symbolStack.peek();
    	if((top == '('))
    		return true;
    	switch(symbol){
    	case '(':
    		return true;
    	case '*':{
    		if((top == '+')||(top == '-'))
    			return true;
    		else 
    			return false;
    	}
    	case '/':{
    		if((top == '+')||(top == '-'))
    			return true;
    		else 
    			return false;
    	}
    	case '+':
    		return false;
    	case '-':
    		return false;
    	case ')':
    		return false;
    	case'=':
    		return false;
        default:
            break;
    		
    	}
    	return true;
    }

    
    public long NumDeal(String number){
    	number = removeStrSpace(number);

    	if(((number.length())> 1) && !("=".equals(number.charAt(number.length() - 1) + ""))){
    		number = number + "=";
    	}
    	if(!(correct(number))){
    		System.out.println("输入表达式有错误，请重新输入！");
    		return 0;
    	}
    	//初始化栈
        numberStack = new Stack<Long>();
        symbolStack = new Stack<Character>();
     // 用于缓存数字
        StringBuffer restore = new StringBuffer();
        
        int i = 0;
        for(i = 0;i < number.length();i++){
        	char ch = number.charAt(i);
        	if(isNumber(ch)){
        		// 加入到数字缓存中
                restore.append(ch);
        	}
        	else{
        		String str = restore.toString();
        		if(!(str.isEmpty())){
        				long numb = Long.parseLong(str);
        				//数字压栈
        				numberStack.push(numb);
        				//重置
        		        restore = new StringBuffer();
        	}
        while (!first(ch) && !symbolStack.empty()){
        	long b = numberStack.pop();
        	long a = numberStack.pop();
        	switch(symbolStack.pop()){
        	case '+':
        		long x1 = a + b;
                numberStack.push(x1);
                break;
            case '-':
        		long x2 = a - b;
                numberStack.push(x2);
                break;
            case '*':
        		long x3 = a * b;
                numberStack.push(x3);
                break;
            case '/':
        		long x4 = a / b;
                numberStack.push(x4);
                break;
            default:
                break;
        	}
        }
        if((ch != '=')){
        	symbolStack.push(ch);
        	if ((ch == ')')) { // 去括号
                symbolStack.pop();
                symbolStack.pop();
        }
        }
        }
        }
    return numberStack.pop();

}
    public static void main(String[]args){
    	Scanner input = new Scanner(System.in);
    	String str = input.nextLine();
    	Cal a = new Cal();
    	double result = a.NumDeal(str);
    	System.out.println(result);
    }
}
