package mieuf.org.base.dell.leetcode;

/**
 * Created by Administrator on 2018/5/4.
 * TODO -----
 */
public class ListNode {

    int val;
    ListNode next;
    ListNode(int x){
        this.val = x;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode resultList = head;
        ListNode q=l1;
        ListNode p=l2;
        int flag = 0;
        int result = 0;
        while(q != null && p != null){
            if((result=q.val+p.val+flag) >= 10){
                flag = result/10;
            }else{
                flag = 0;
            }
            head.next = new ListNode(result%10);
            head = head.next;
            q=q.next;
            p=p.next;
        }
        if(!((q == null) &&(p == null))){
            if (q != null){
                head.next = q;
            }
            if (p != null){
                head.next = p;
            }
        }
        if(flag > 0){
            while (flag > 0){
                if(head.next == null){
                    head.next = new ListNode(flag);
                    flag = 0;
                } else {
                    head = head.next;
                  head.val = head.val + flag;
                  if(head.val >= 10){
                      head.val = head.val%10;
                      flag = 1;
                  }else {
                      flag = 0;
                  }
                }
            }
        }
        return resultList.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);
        ListNode temp = new ListNode(0);
        ListNode listNode = temp.addTwoNumbers(l1, l2);

    }
}
