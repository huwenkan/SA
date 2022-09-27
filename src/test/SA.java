package test;

import java.util.Random;

/**
 * 以计算x^0.5为例
 */
public class SA {
    public static void main(String[] args) {
        SA sa = new SA();
        sa.SA(3.0);
    }

    //初始温度
    Double T = 20.0;
    //温度下降的速率，每次下降0.01%
    Double dt = 0.9999;
    //温度下降的最低值
    Double eps = 1E-14;
    //马尔可夫链的长度，每次降温需要迭代的次数
    Long L = 200L;

    public void SA(Double n) {
        //随机选择的初始值
        Double x = 0.0;
        //每次迭代的函数
        Double f = Math.abs(x * x - n);

        Random random = new Random();

        while (T > eps) {
            //每次随机偏移的量，产生一个-1.0到1.0随机数加上原来的x
            Double dx = x + random.nextDouble() * 2 - 1.0;
            //偏移以后产生的新解
            Double abs = Math.abs(dx * dx - n);
            //约束目标值，确保最终得到的结果不会偏离需要的要求
            while (dx < 0) {
                dx = x + random.nextDouble() * 2 - 1.0;
            }
            //迭代
            for (int i = 0; i < L; i++) {
                //产生的新解如果符合要求就直接接受
                if (abs < f) {
                    f = abs;
                    x = dx;
                } //不符合要求，以一定的概率接受
                else if (Math.exp((f - abs) / T) > random.nextDouble()) {
                    f = abs;
                    x = dx;
                }
                //降温
                T *= dt;
            }
        }
        System.out.println(x);
    }
}
