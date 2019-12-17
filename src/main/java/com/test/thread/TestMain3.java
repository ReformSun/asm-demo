package com.test.thread;

/**
 * 在一个屋子里面有很多的人，想统计所有人员的年龄总和
 * 通过什么样的手段获取
 * 1. 共享可变性设计
 * 在教师中的黑板上写一个数值0
 * 让所有人排号队，书写自己的年龄加上黑板上所有的值
 * 理想情况下所有人排好队不会出现有人捣乱把值改为无限大
 * 如何能保证这种情况不会发生那，只能让几个人盯着黑板。防止有人捣乱
 * 这种设计方式有几个问题
 *   1. 必须保证所有人排号队写
 *   2. 没有人出现捣乱的情况。因为如果出现一个人捣乱这个总数就无效。前面做的工作白费
 *   3. 这样也会造成大量人员处于等待状态，统计总数的时间会非常的长
 *
 */
public class TestMain3 {
    private static int total = 0;
    public static void main(String[] args) {

    }

}