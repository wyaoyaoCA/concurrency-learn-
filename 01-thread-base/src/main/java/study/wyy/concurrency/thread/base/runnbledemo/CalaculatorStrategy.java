package study.wyy.concurrency.thread.base.runnbledemo;

/**
 * 计算税收的策略
 */
public interface CalaculatorStrategy {
    double calculate(double salary,double bonus);
}
