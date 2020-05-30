/**
 * 创建线程的基本方式
 *
 *  1 继承thread:
 *      @see study.wyy.concurrency.thread.base.CreateThreadDemo1
 *  2 实现Runnable
 *      @see study.wyy.concurrency.thread.base.CreateThreadDemo2
 * 两种方式，有了方式1 为何出现方式2
 *   实现runnable接口，实现了单一职责设计原则，实现启动线程和业务逻辑隔离
 *      runnable接口，只是Thread的中的一个逻辑单元，内部实现了线程要执行的功能逻辑
 *   并且解决了java单继承的问题。
 *
 *
 */
package study.wyy.concurrency.thread.base;