package com.qs.webside.util;

import com.qs.webside.activity.model.AwardPro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RamUtil {

    /**
     * 抽奖
     * <p>
     * 原始的概率列表，保证顺序和实际物品对应
     *
     * @return 物品的索引
     */
    public static AwardPro lottery(List<AwardPro> gifts) {
        //List<Double> orignalRates
        List<Double> orignalRates = new ArrayList<Double>(gifts.size());
        for (AwardPro gift : gifts) {
            double probability = gift.getGailv();
            if (probability < 0) {
                probability = 0;
            }
            orignalRates.add(probability);
        }
        if (orignalRates == null || orignalRates.isEmpty()) {
            return null;
        }

        int size = orignalRates.size();

        // 计算总概率，这样可以保证不一定总概率是1
        double sumRate = 0d;
        for (double rate : orignalRates) {
            sumRate += rate;
        }

        // 计算每个物品在总概率的基础下的概率情况
        List<Double> sortOrignalRates = new ArrayList<Double>(size);
        Double tempSumRate = 0d;
        for (double rate : orignalRates) {
            tempSumRate += rate;
            sortOrignalRates.add(tempSumRate / sumRate);
        }

        // 根据区块值来获取抽取到的物品索引
        double nextDouble = Math.random();
        sortOrignalRates.add(nextDouble);
        Collections.sort(sortOrignalRates);
        int awardIndex = sortOrignalRates.indexOf(nextDouble);
        return gifts.get(awardIndex);
    }


    public static int getJD(List<Double> orignalRates) {
        if (orignalRates == null || orignalRates.isEmpty()) {
            return -1;
        }

        int size = orignalRates.size();

        // 计算总概率，这样可以保证不一定总概率是1
        double sumRate = 0d;
        for (double rate : orignalRates) {
            sumRate += rate;
        }

        // 计算每个物品在总概率的基础下的概率情况
        List<Double> sortOrignalRates = new ArrayList<Double>(size);
        Double tempSumRate = 0d;
        for (double rate : orignalRates) {
            tempSumRate += rate;
            sortOrignalRates.add(tempSumRate / sumRate);
        }

        // 根据区块值来获取抽取到的物品索引
        double nextDouble = Math.random();
        sortOrignalRates.add(nextDouble);
        Collections.sort(sortOrignalRates);

        return sortOrignalRates.indexOf(nextDouble);
    }

}
