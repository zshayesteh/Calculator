package HW10_calculator;

import java.util.ArrayList;

public class Stack<T> {
	private ArrayList<T> digit;
	private int topOfStack = -1;

	public int getTopOfStack() {
		return topOfStack;
	}

	public void setTopOfStack(int topOfStack) {
		this.topOfStack = topOfStack;
	}

	public Stack() {
		digit = new ArrayList<T>();
	}

	public T getElementByPosition(int position) {
		return digit.get(position);
	}

	public boolean push(T num) {
		if (topOfStack < digit.size()) {
			topOfStack++;
			digit.add(num);
			return true;
		} else
			return false;
	}

	public T pop() {
		T item;
		if (topOfStack != -1) {
			item = digit.get(topOfStack);
			digit.remove(topOfStack);
			topOfStack--;
			return item;
		}
		return null;
	}
}
