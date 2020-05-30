package study.wyy.concurrency.thread.base.runnbledemo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-24 21:05
 * @description：测试
 * @modified By：
 * @version: $
 */
@Slf4j
public class Test {

    @org.junit.Test
    public void test1() {
        TaxCalaculator taxCalaculator = new TaxCalaculator(10000d, 2000d) {
            // 重写calcTax，实现个人所得税的计算方法
            @Override
            public double calcTax() {
                // 工资要交10%的税，奖金要交百分15的税
                return getSalary() * 0.1 + getBonus() * 0.15;
            }
        };
        double calculate = taxCalaculator.calculate();
        log.info("计算出个人所得税为：{}", calculate);

    }
    // 如果此时要改变计算税收的方式，就要改变整个程序，如果计算方式很复杂呢
    // 所以可以学习Runnbale的设计方式，将计算税收的方式抽离出来，单独实现，而TaxCalaculator只是
    // 持有计算税收的逻辑单元即可



    @org.junit.Test
    public void test2() {
        TaxCalaculator taxCalaculator = new TaxCalaculator(10000d, 2000d);
        CalaculatorStrategy calaculatorStrategy = new DefaultCalaculatorStrategy();
        taxCalaculator.setCalaculatorStrategy(calaculatorStrategy);
        double calculate = taxCalaculator.calculate();
        log.info("计算出个人所得税为：{}", calculate);

    }
}
