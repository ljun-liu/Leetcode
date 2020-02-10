// (JAVA)

// 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请
// 将最后剩余的节点保持原有顺序。

// 示例 :
// 给定这个链表：1->2->3->4->5

// 当 k = 2 时，应当返回: 2->1->4->3->5
// 当 k = 3 时，应当返回: 3->2->1->4->5

// 说明 :
// -你的算法只能使用常数的额外空间。
// -你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

// *--------------------------------------------------------------------------------------------------------------------------------*
// 步骤分解:
// -链表分区为已翻转部分+待翻转部分+未翻转部分
// -每次翻转前，要确定翻转链表的范围，这个必须通过 k 此循环来确定
// -需记录翻转链表前驱和后继，方便翻转完成后把已翻转部分和未翻转部分连接起来
// -初始需要两个变量 pre 和 end，pre 代表待翻转链表的前驱，end 代表待翻转链表的末尾
// -经过k此循环，end 到达末尾，记录待翻转链表的后继 next = end.next
// -翻转链表，然后将三部分链表连接起来，然后重置 pre 和 end 指针，然后进入下一次循环
// -特殊情况，当翻转部分长度不足 k 时，在定位 end 完成后，end==null，已经到达末尾，说明题目已完成，直接返回即可
// -时间复杂度为 O(n*K) 最好的情况为 O(n) 最差的情况未 O(n^2)
// -空间复杂度为 O(1) 除了几个必须的节点指针外，我们并没有占用其他空间

// *---------------------------------------------------------------*
public ListNode reverseKGroup(ListNode head, int k) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;

    ListNode pre = dummy;
    ListNode end = dummy;

    while (end.next != null) {
//         三个节点->移动end的位置
        for (int i = 0; i < k && end != null; i++) end = end.next;
//         k-1个节点时：直接退出
        if (end == null) break;
        ListNode start = pre.next;
//         下一组（保证end.next不丢失）
        ListNode next = end.next;
//         保证一组k个
        end.next = null;
        pre.next = reverse(start);
        start.next = next;
      
        pre = start;
        end = pre;
    }
    return dummy.next;
}

private ListNode reverse(ListNode head) {
    ListNode pre = null;
    ListNode curr = head;
    while (curr != null) {
        ListNode next = curr.next;
        curr.next = pre;
//         下一组的pre
        pre = curr;
//         下一组curr
        curr = next;
    }
//     因为curr已经为第四个“字节”null
    return pre;
}
