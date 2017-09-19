package com.qs.webside.activity.service.impl;

import com.qs.common.constant.AppConstants;
import com.qs.common.constant.CommonContants;
import com.qs.common.constant.Constants;
import com.qs.mainku.game.service.IBaseParamService;
import com.qs.webside.activity.mapper.ActiAwardProMapper;
import com.qs.webside.activity.model.*;
import com.qs.webside.activity.service.*;
import com.qs.webside.util.RamUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zun.wei on 2017/9/18 11:23.
 * Description:奖品概率对象
 */
@Service
public class ActiAwardProService implements IActiAwardProService {

    @Resource
    private ActiAwardProMapper actiAwardProMapper;

    @Resource
    private IActiCenterService actiCenterService;

    @Resource
    private IActiAwardService actiAwardService;

    @Resource
    private IActiAwardRecordService actiAwardRecordService;

    @Resource
    private IActiRedPacketService actiRedPacketService;

    @Resource
    private IActiSendGoldService actiSendGoldService;

    @Resource
    private IBaseParamService baseParamService;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return actiAwardProMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ActiAwardPro record) {
        return actiAwardProMapper.insert(record);
    }

    @Override
    public int insertSelective(ActiAwardPro record) {
        return actiAwardProMapper.insertSelective(record);
    }

    @Override
    public ActiAwardPro selectByPrimaryKey(Integer id) {
        return actiAwardProMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ActiAwardPro record) {
        return actiAwardProMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ActiAwardPro record) {
        return actiAwardProMapper.updateByPrimaryKey(record);
    }

    @Override
    public ActiAwardPro selectByAwarId(Integer awarId) {
        return actiAwardProMapper.selectByAwarId(awarId);
    }

    @Override
    public int updateByAwardIdAndType(ActiAwardPro record) {
        return actiAwardProMapper.updateByAwardIdAndType(record);
    }

    @Override
    public List<Map<String, Object>> queryListByActiType(Map<String, Object> parameters) {
        return actiAwardProMapper.queryListByActiType(parameters);
    }

    @Override
    public Map<String, Object> executeLuckDraw(int mid,String sesskey) throws ParseException {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> actiCenter = actiCenterService.queryListActivityByStatusAndType
                (AppConstants.ActivityType.ELEVEN_BIG_TURNTABLE_ACTIVITY_TYPE);//验证活动是否存在并在有效期内
        if (actiCenter == null) {
            result.put(CommonContants.RESULT, Boolean.FALSE);
            result.put(CommonContants.ERROR, -1);
            result.put(CommonContants.MESSAGE, "活动类型不存在!");
            return result;
        }
        int startTime = 0;
        int endTime = 0;
        if (actiCenter.get("startTime") != null) startTime = Integer.parseInt(actiCenter.get("startTime") + "");
        if (actiCenter.get("endTime") != null) endTime = Integer.parseInt(actiCenter.get("endTime") + "");
        int nowTime = new Long(((new Date().getTime()) / 1000)).intValue();
        if (startTime <= nowTime && nowTime <= endTime) {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("type", AppConstants.ActivityType.ELEVEN_BIG_TURNTABLE_ACTIVITY_TYPE);
            List<Map<String, Object>> actiAwardPros = queryListByActiType(parameters);
            if (actiAwardPros == null || actiAwardPros.isEmpty()) {
                result.put(CommonContants.RESULT, Boolean.FALSE);
                result.put(CommonContants.ERROR, -3);
                result.put(CommonContants.MESSAGE, "未配置奖品抽奖概率或产出时间未到或者奖品库存不足！");
                return result;
            }
            List<AwardPro> awardPros = new ArrayList<>();
            for (Map<String, Object> actiAwardPro : actiAwardPros) {
                String name = actiAwardPro.get("name") + "";
                int awardId = Integer.parseInt(actiAwardPro.get("awardId") + "");
                int stock = Integer.parseInt(actiAwardPro.get("stock") + "");
                double proStock = Double.parseDouble(actiAwardPro.get("awardProStock") + "");
                //double award_Pro = Double.parseDouble(actiAwardPro.get("awardPro") + "");
                int type = Integer.parseInt(actiAwardPro.get("type") + "");
                if (Integer.parseInt(actiAwardPro.get("status") + "") == 0) {//0启用高级设置,1未开启
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date now = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
                    Date addProDate = simpleDateFormat.parse(actiAwardPro.get("addProDate") + "");
                    int d = differentDaysByMillisecond(now, addProDate);
                    if (d >= 0) {
                        double awardProStock =  proStock + Double.parseDouble(actiAwardPro.get("daliAddPro") + "") * d;
                        AwardPro awardPro = new AwardPro(awardId, name, stock, awardProStock,type);
                        awardPros.add(awardPro);
                    } else {
                        AwardPro awardPro = new AwardPro(awardId, name, stock, proStock,type);
                        awardPros.add(awardPro);
                    }
                } else {
                    AwardPro awardPro = new AwardPro(awardId, name, stock, proStock,type);
                    awardPros.add(awardPro);
                }
            }
            AwardPro ap = RamUtil.lottery(awardPros);

            Map<String, Object> para = new HashMap<>();
            para.put("id", ap.getId());
            para.put("reduce", 1);
            int updateStockResult = actiAwardService.updateStockByReduceAndId(para);
            if (updateStockResult > 0) {//更新库存成功
                ActiAward actiAward = actiAwardService.selectByPrimaryKey(ap.getId());
                String remark = actiAward.getRemark();

                //插入兑换记录表
                ActiAwardRecord actiAwardRecord = new ActiAwardRecord();
                actiAwardRecord.setMid(mid);
                actiAwardRecord.setAwardId(ap.getId());
                actiAwardRecord.setName(ap.getName());
                actiAwardRecord.setIntegral(0L);
                actiAwardRecord.setAwardNum(1L);
                actiAwardRecord.setType(ap.getType());
                actiAwardRecord.setCreateTime(new Date());

                if (StringUtils.isNotBlank(remark)) {
                    Integer awardType = Integer.parseInt(remark);

                    if (awardType == 2) {//如果为红包类型
                        List<ActiRedPacketCfg> arpcs = actiRedPacketService.queryListByActiType
                                (AppConstants.ActivityType.ELEVEN_BIG_TURNTABLE_ACTIVITY_TYPE);
                        if (arpcs == null || arpcs.isEmpty()) {
                            result.put(CommonContants.RESULT, Boolean.FALSE);
                            result.put(CommonContants.ERROR, -7);
                            result.put(CommonContants.MESSAGE, "红包概率配置表未配置！");
                            return result;
                        }
                        List<AwardPro> awardPros1 = new ArrayList<>();
                        for (ActiRedPacketCfg arpc : arpcs) {
                            int actiType = arpc.getActiType();
                            double gailv = Double.parseDouble(arpc.getGailv());
                            int id = arpc.getId();
                            int stock = arpc.getPackStore();
                            String name = arpc.getName();
                            AwardPro awardPro = new AwardPro(id, name, stock, gailv, actiType);
                            awardPros1.add(awardPro);
                        }
                        AwardPro ap1 = RamUtil.lottery(awardPros1);
                        Map<String, Object> para1 = new HashMap<>();
                        para1.put("id", ap1.getId());
                        para1.put("reduce", 1);

                        int updateStockResult1 = actiRedPacketService.updateStockByReduceAndId(para1);
                        if (updateStockResult1 > 0) {//更新库存成功
                            ActiRedPacketCfg actiRedPacketCfg = actiRedPacketService.selectByPrimaryKey(ap1.getId());
                            String gold = actiRedPacketCfg.getGold();
                            actiAwardRecord.setIsReview(0);
                            actiAwardRecord.setDescr(gold + "元");
                        } else {
                            result.put(CommonContants.RESULT, Boolean.FALSE);
                            result.put(CommonContants.ERROR, -55);
                            result.put(CommonContants.MESSAGE, "红包库存不足！");
                            return result;
                        }
                    } else if (awardType == 1) {//房卡类型
                        actiAwardRecord.setIsReview(2);
                        actiAwardRecord.setSendTime(new Date());
                        String cardNumStr = actiAward.getDescr();
                        cardNumStr = cardNumStr == null ? "1" : cardNumStr;
                        Integer cardNum = Integer.parseInt(cardNumStr);
                        String sendGoldUrl = baseParamService.getBaseParamValueByCode(AppConstants.BaseParam.APP_SEND_GOLD_INTERFACE_URL);
                        String httpsResponse = actiSendGoldService.sendGold(sesskey,
                                cardNum,
                                AppConstants.GoldLogSourceType.BIG_TURNTABLE_LOTTERY_ADD_GOLD,
                                new Date(System.currentTimeMillis()).getTime() / 1000,
                                sendGoldUrl);
                    } else if (awardType == 3) {//忽略类
                        actiAwardRecord.setIsReview(2);
                        actiAwardRecord.setSendTime(null);
                    } else {//非房卡，非红包
                        actiAwardRecord.setIsReview(0);
                        actiAwardRecord.setSendTime(null);
                    }
                } else {
                    result.put(CommonContants.RESULT, Boolean.FALSE);
                    result.put(CommonContants.ERROR, -6);
                    result.put(CommonContants.MESSAGE, "奖品类型异常！");
                    return result;
                }

                if (actiAward.getStock() <= 0) {//库存为零之后，把概率为清零。把原分摊过来的概率还回去
                    ActiAwardPro actiAwardPro = actiAwardProMapper.selectByAwarId(ap.getId());
                    if (actiAwardPro.getStatus() == 0) {
                        int proSources = actiAwardPro.getProSources();
                        double aap = Double.parseDouble(actiAwardPro.getAwardProStock());
                        double daliAddPro = Double.parseDouble(actiAwardPro.getDaliAddPro());
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date now = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
                        Date addProDate = simpleDateFormat.parse(simpleDateFormat.format(actiAwardPro.getAddProDate()));
                        int d = differentDaysByMillisecond(now, addProDate);
                        actiAwardPro.setAwardProStock("0");
                        actiAwardProMapper.updateByPrimaryKeySelective(actiAwardPro);//把库存为0的奖品概率调零

                        ActiAwardPro aproSources = actiAwardProMapper.selectByAwarId(proSources);
                        double aps = Double.parseDouble(aproSources.getAwardProStock()) + aap ;
                        if (d >= 0) aps += daliAddPro * d;
                        aproSources.setAwardProStock(aps + "");
                        actiAwardProMapper.updateByPrimaryKeySelective(aproSources);//把概率还给原分摊的奖品
                    }
                }

                int r = actiAwardRecordService.insertSelective(actiAwardRecord); //插入兑换记录表
                if (r > 0) {
                    Map<String, Object> retrunM = new HashMap<>();
                    retrunM.put("name", actiAwardRecord.getName());
                    String gold = actiAwardRecord.getDescr();
                    retrunM.put("gold", gold);//如果是红包，则这个不为空
                    int type = StringUtils.isNotBlank(gold) ? 0 : 1;//0 为红包类型，1为非红包
                    retrunM.put("type",type );
                    result.put(CommonContants.RESULT, Boolean.TRUE);
                    result.put(CommonContants.ERROR, 0);
                    result.put(CommonContants.DATA, actiAwardRecord);
                    result.put(CommonContants.MESSAGE, "抽奖成功!");
                    return result;
                } else {
                    result.put(CommonContants.RESULT, Boolean.FALSE);
                    result.put(CommonContants.ERROR, -4);
                    result.put(CommonContants.MESSAGE, "插入记录失败！");
                    return result;
                }
            } else {//更新库存失败，说明没有库存了。
                result.put(CommonContants.RESULT, Boolean.FALSE);
                result.put(CommonContants.ERROR, -5);
                result.put(CommonContants.MESSAGE, "库存不足！");
                return result;
            }
        } else {
            result.put(CommonContants.RESULT, Boolean.FALSE);
            result.put(CommonContants.ERROR, -2);
            result.put(CommonContants.MESSAGE, "活动时间未开始或者已结束！");
            return result;
        }
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    private int differentDaysByMillisecond(Date date1,Date date2){
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }

}
