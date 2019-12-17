package com.test.memoryModel;

/**
 * 总线
 * ：是计算机各种功能部件之间传送信息的公共通信干线
 *
 * 在计算机中数据通过总线在处理器和内存之间传递，每次处理器和内存之间的数据传递都是
 * 通过一系列步骤来完成的。这一系列步骤被称之为总线事务
 *
 * 总线事务包含读事务和写事务
 * 读事务：把数据从内存中传递到处理器中
 * 写事务：从处理器传递数据到内存中
 *
 * 每个事务会读/写内存中一个或者多个物理上连续的字节
 *
 *
 * 关键： 总线会同步试图并发使用总线的事务（意思：在一个处理器执行总线事务期间，
 * 总线会禁止其它所有的处理器和 I/O 设备执行内存的读/ 写）
 *
 *
 * 总线的这些工作机制可以把所有处理器对内存的访问以串行化的方式来执行;在任意时间点，
 * 最多只能有一个处理器能访问一个特定的连续物理内存。这个特性确保了单个总线事务之中的内存读/写操作具有原子性。
 *
 * 比如：一个32位的处理器：一次只能保证对一个32位的内存读的访问，这样对这个32位的连续物理内存的读写操作是具有原子性的
 * 如果32的处理器对一个64位的long和double类型的数据进行处理，如果要保证写操作的原子性就要做一些其他的操作，并且会增加比较
 * 大的开销，为了照顾处理器java语言规范并没有强制要求jvm对64为long或者double类型的写就有原子性。所以jvm会把对64位long或者double
 * 类型的数据的写拆分成对两个32位的写操作。这个两个写操作可能会被分配给不同的总线事务中执行。此时对这个64位数据类型的操作将不具有
 * 原子性，当处理器对单个数据类型的操作不具有原子性的时候将会出现意想不到的结果
 *
 *
 *
 */
public class TestMain7 {
}