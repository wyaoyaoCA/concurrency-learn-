package study.wyy.concurrency.thread.api.join_test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-28 11:17
 * @description：
 * @modified By：
 * @version: $
 */
// 定义采集的任务
@Slf4j
public class CaptureDataTask implements Runnable, CaptureData {
    // 机器的名字
    private final String machineName;
    // 这次任务花费的时间，每个机器采集的时间可能不同，
    private Long spendTime;
    // 采集的结果
    private Data result;

    // 这里模拟每个采集的时间不同，在构造中也加入spendTime
    public CaptureDataTask(String machineName, Long spendTime) {
        this.machineName = machineName;
        this.spendTime = spendTime;
    }

    @Override
    public void run() {
        try {
            // 模拟采集数据的过程
            log.info("[{}]{}开始采集数据", System.currentTimeMillis(), machineName);
            result = captureData();
            Thread.sleep(spendTime);
            log.info("[{}]{}采集数据结束", System.currentTimeMillis(), machineName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 返回采集的结果
    public Data getResult() {
        return result;
    }

    @Override
    public Data captureData() {
        // 模拟
        return new Data(machineName + "的数据");
    }
}
