package study.wyy.concurrency.thread.base.runnbledemo;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-24 21:03
 * @description：
 * @modified By：
 * @version: $
 */
public class TaxCalaculator {
    // 薪水
    private final double salary;
    // 奖金
    private final double bonus;

    private CalaculatorStrategy calaculatorStrategy;

    public TaxCalaculator(double salary, double bonus) {
        this.salary = salary;
        this.bonus = bonus;
    }

    //
    public double calcTax(){
        if(calaculatorStrategy != null){
            return calaculatorStrategy.calculate(salary,bonus);
        }
        return 0.0d;
    }

    // 类比于Thread中的start方法
    public double calculate(){
       return this.calcTax();
    }

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setCalaculatorStrategy(CalaculatorStrategy calaculatorStrategy) {
        this.calaculatorStrategy = calaculatorStrategy;
    }
}
