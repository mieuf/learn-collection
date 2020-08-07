package mieuf.org.base.dell.StackandQueue.stack2queue;

import java.util.HashMap;
import java.util.Stack;

/**
 * 两个栈实现一个队列
 */
public class TwoStackQueue {

    public Stack<Integer> stackPush;
    public Stack<Integer> stackPop;

    public TwoStackQueue() {
        this.stackPop = new Stack<>();
        this.stackPush = new Stack<>();
    }

    public void add(int pushInt) {
        this.stackPush.push(pushInt);
    }

    public int poll() {
        if (this.stackPop.isEmpty() && this.stackPush.isEmpty()) {
            throw new RuntimeException("queue is empty.");
        } else if (this.stackPop.isEmpty()){
            while (!this.stackPush.isEmpty()) {
                this.stackPop.push(this.stackPush.pop());
            }
        }
        return this.stackPop.pop();
    }

    public int peek() {
        if (this.stackPop.isEmpty() && this.stackPush.isEmpty()) {
            throw new RuntimeException("queue is empty");
        }else if (this.stackPop.isEmpty()) {
            while (!this.stackPush.isEmpty()) {
                this.stackPop.push(this.stackPush.pop());
            }
        }
        return stackPop.peek();
    }

}
