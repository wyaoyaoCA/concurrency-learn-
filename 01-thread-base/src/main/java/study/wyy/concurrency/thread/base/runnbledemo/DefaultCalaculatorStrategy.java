package study.wyy.concurrency.thread.base.runnbledemo;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-24 21:21
 */
public class DefaultCalaculatorStrategy implements CalaculatorStrategy {
    private final static double SALARY_RATE = 0.1;
    private final static double BONUS_RATE = 0.15;

    @Override
    public double calculate(double salary, double bonus) {
        return salary * SALARY_RATE + bonus * BONUS_RATE;
    }
}
