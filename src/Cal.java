import java.util.Scanner;
import java.util.Stack;






public class Cal{
    private Stack<Long> numberStack = null;//����ջ
    private Stack<Character> symbolStack = null;//����ջ
    

    //ȥ���ո�
    private String removeStrSpace(String str) {
        return str != null ? str.replaceAll(" ", "") : "";
    }
    
    //�Ƿ�������
    public boolean isNumber(char num){
    	if(num <= '9'&&num >= '0'){
    		return true;
    	}
    	else 
    		return false;
    }
    
    
    //��ʽ�ĺϷ���
    public boolean correct(String number){
    	//���ʽ��Ϊ��
    	if(number == null || number.isEmpty())
    		return false;
    	//�����Ƿ�ƥ��
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
    		//������ѹջ
    		if(("(".equals(n + "")))
    			kuohao.push(n);
    		//������ƥ��
    		if((")".equals(n + "")))
    			if((kuohao.isEmpty())||!("(".equals(kuohao.pop() + "")))
    				return false;
    		//����Ⱥŵ�����
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
    	

    //���ȼ��ıȽ�
    public boolean first(char symbol){
    	if(symbolStack.isEmpty())
    		return true;
    	// �鿴��ջ�����Ķ���
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
    		System.out.println("������ʽ�д������������룡");
    		return 0;
    	}
    	//��ʼ��ջ
        numberStack = new Stack<Long>();
        symbolStack = new Stack<Character>();
     // ���ڻ�������
        StringBuffer restore = new StringBuffer();
        
        int i = 0;
        for(i = 0;i < number.length();i++){
        	char ch = number.charAt(i);
        	if(isNumber(ch)){
        		// ���뵽���ֻ�����
                restore.append(ch);
        	}
        	else{
        		String str = restore.toString();
        		if(!(str.isEmpty())){
        				long numb = Long.parseLong(str);
        				//����ѹջ
        				numberStack.push(numb);
        				//����
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
        	if ((ch == ')')) { // ȥ����
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
