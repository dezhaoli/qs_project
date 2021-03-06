package com.qs.webside.activity.service.impl;

import com.qs.common.constant.AppConstants;
import com.qs.common.constant.CacheConstan;
import com.qs.common.constant.CommonContants;
import com.qs.common.constant.Constants;
import com.qs.common.util.DateUtil;
import com.qs.mainku.game.service.IBaseParamService;
import com.qs.mainku.game.service.IMemberFidesService;
import com.qs.webside.activity.mapper.ActiAwardProMapper;
import com.qs.webside.activity.model.*;
import com.qs.webside.activity.service.*;
import com.qs.webside.member.model.Memberfides;
import com.qs.webside.util.RamUtil;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeoutException;

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

    @Resource
    private MemcachedClient memcachedClient;

    @Resource
    private IMemberFidesService memberFidesService;


    private static Log log = LogFactory.getLog(ActiAwardProService.class);

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
    public Map<String, Object> executeLuckDraw(int mid,String sesskey,int gameType)
            throws ParseException, InterruptedException, MemcachedException, TimeoutException {
        Map<String, Object> result = new HashMap<>();

        //判断人员信息是否存在
        Memberfides memberfides = memberFidesService.findMemberfidesById(mid);
        if (memberfides == null) {
            log.debug("--------------------::game member is not exist!");
            result.put(CommonContants.RESULT, Boolean.FALSE);
            result.put(CommonContants.ERROR, -12);
            result.put(CommonContants.MESSAGE, "game member is not exist!");
            return result;
        }

        //更新memcached 缓存
        String memcKey = CacheConstan.NATIONAL_ACTIVITIES_FINISHROOMCOUNT + mid;
        Object o = memcachedClient.get(memcKey);
        String oStr = (o + "");
        int nowCount = "null".equals(oStr) ? 0 : Integer.parseInt(oStr);
        nowCount = nowCount > 0 ? nowCount : 0;
        log.debug("--------------------::user "+mid+" current card count is " + nowCount+ "!");
        int playCount = 0;
        int updateCount = 0;

        switch (gameType) {
            //Constants.GameType.JX_MAJIANG
            case 20://江西换算值积分换算剩余次数
                playCount = nowCount / Constants.ActiveCenter.JX_ELEVEN_ACTIVITES_COUNT;
                updateCount = nowCount - Constants.ActiveCenter.JX_ELEVEN_ACTIVITES_COUNT;
                break;
            case 17://开心换算值积分换算剩余次数
                playCount = nowCount / Constants.ActiveCenter.KX_ELEVEN_ACTIVITES_COUNT;
                updateCount = nowCount - Constants.ActiveCenter.KX_ELEVEN_ACTIVITES_COUNT;
                break;
            default:
                playCount = nowCount / Constants.ActiveCenter.KX_ELEVEN_ACTIVITES_COUNT;
                updateCount = nowCount - Constants.ActiveCenter.KX_ELEVEN_ACTIVITES_COUNT;
                break;
        }
        /*if (!(playCount > 0)) {
            log.debug("--------------------::The number of card games did not meet the lottery conditions!");
            result.put(CommonContants.RESULT, Boolean.FALSE);
            result.put(CommonContants.ERROR, -111);
            result.put(CommonContants.MESSAGE, "玩牌局数未满足抽奖条件!");
            return result;
        }*/

        //查询数据库是否抽奖达到上限
        Map<String, Object> p = new HashMap<>();
        p.put("type", AppConstants.ActivityType.ELEVEN_BIG_TURNTABLE_ACTIVITY_TYPE);
        p.put("date", DateUtil.getNewDate());
        p.put("mid", mid);
        Integer count = actiAwardRecordService.queryRecordCount(p);
        /*if (count >= 5) {
            log.debug("--------------------::The number of lottery reaches 5 times the upper limit!");
            result.put(CommonContants.RESULT, Boolean.FALSE);
            result.put(CommonContants.ERROR, -11);
            result.put(CommonContants.MESSAGE, "抽奖次数达到5次上限!");
            return result;
        }*/
        Map<String, Object> actiCenter = actiCenterService.queryListActivityByStatusAndType
                (AppConstants.ActivityType.ELEVEN_BIG_TURNTABLE_ACTIVITY_TYPE);//验证活动是否存在并在有效期内
        if (actiCenter == null) {
            log.debug("--------------------::Activity type does not exist!");
            result.put(CommonContants.RESULT, Boolean.FALSE);
            result.put(CommonContants.ERROR, -1);
            result.put(CommonContants.MESSAGE, "活动类型不存在!");
            return result;
        }

        //时间校验，概率校验，库存校验
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
                log.debug("--------------------::" +
                        "No prizes, lottery probability, or time of production or insufficient stock of prizes!");
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
                    int d = differentDaysByMillisecond(addProDate,now);
                    if (d >= 0) {//达到增减概率时间
                        String remark = actiAwardPro.get("remark") + "";
                        String nowTimeStr = simpleDateFormat.format(new Date());
                        if (StringUtils.isNotBlank(remark) && !"null".equals(remark)) {
                            if (!remark.equals(nowTimeStr)) {//当天未更新过概率
                                fluctuationProbability(awardPros, actiAwardPro, name, awardId, stock,
                                        proStock, type, d, nowTimeStr);
                            } else {//当天已经更新过了。
                                AwardPro awardPro = new AwardPro(awardId, name, stock, proStock, type);
                                awardPros.add(awardPro);
                            }
                        } else {//从来未更新过概率
                            fluctuationProbability(awardPros, actiAwardPro, name, awardId, stock,
                                    proStock, type, d, nowTimeStr);
                        }
                    } else {//未达到增减概率时间
                        AwardPro awardPro = new AwardPro(awardId, name, stock, proStock,type);
                        awardPros.add(awardPro);
                    }
                } else {//未开启高级设置
                    AwardPro awardPro = new AwardPro(awardId, name, stock, proStock,type);
                    awardPros.add(awardPro);
                }
            }
            AwardPro ap = RamUtil.lottery(awardPros);

            Map<String, Object> para = new HashMap<>();
            para.put("id", ap.getId());
            ActiAward actiAward = actiAwardService.selectByPrimaryKey(ap.getId());
            String remark = actiAward.getRemark();//获取奖品类型,2红包，1房卡，3忽略类，其他非红包非房卡
            if (StringUtils.isNotBlank(remark)) {
                Integer awardType = Integer.parseInt(remark);
                if (awardType == 1) {
                    String cardNumStr = actiAward.getDescr();
                    cardNumStr = cardNumStr == null ? "1" : cardNumStr;
                    Integer cardNum = Integer.parseInt(cardNumStr);
                    para.put("reduce", cardNum);//库存减1
                } else {
                    para.put("reduce", 1);//库存减1
                }
            } else {
                para.put("reduce", 1);//库存减1
            }
            int updateStockResult = actiAwardService.updateStockByReduceAndId(para);
            if (updateStockResult > 0) {//更新库存成功

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
                            log.debug("--------------------::Red probability configuration table not configured!");
                            result.put(CommonContants.RESULT, Boolean.FALSE);
                            result.put(CommonContants.ERROR, -7);
                            result.put(CommonContants.MESSAGE, "红包概率配置表未配置或配置表中的库存不足！");
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
                        para1.put("reduce", 1);//库存减1

                        int updateStockResult1 = actiRedPacketService.updateStockByReduceAndId(para1);
                        if (updateStockResult1 > 0) {//更新库存成功
                            ActiRedPacketCfg actiRedPacketCfg = actiRedPacketService.selectByPrimaryKey(ap1.getId());
                            String gold = actiRedPacketCfg.getGold();
                            actiAwardRecord.setIsReview(0);//0为未审核
                            actiAwardRecord.setDescr(gold);
                        } else {
                            log.debug("--------------------::Red packets is low stocks!");
                            result.put(CommonContants.RESULT, Boolean.FALSE);
                            result.put(CommonContants.ERROR, -55);
                            result.put(CommonContants.MESSAGE, "红包库存不足！");
                            return result;
                        }
                    } else if (awardType == 1) {//房卡类型
                        actiAwardRecord.setIsReview(2);//无须审核为2
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
                        actiAwardRecord.setIsReview(3);//忽略类为3
                        actiAwardRecord.setSendTime(null);
                    } else {//非房卡，非红包
                        actiAwardRecord.setIsReview(0);//0为未审核
                        actiAwardRecord.setSendTime(null);
                    }
                } else {
                    log.debug("--------------------::Prize type exception!");
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
                        actiAwardPro.setAwardProStock("0");
                        actiAwardProMapper.updateByPrimaryKeySelective(actiAwardPro);//把库存为0的奖品概率调零

                        ActiAwardPro aproSources = actiAwardProMapper.selectByAwarId(proSources);
                        double aps = Double.parseDouble(aproSources.getAwardProStock()) + aap ;
                        aproSources.setAwardProStock(aps + "");
                        actiAwardProMapper.updateByPrimaryKeySelective(aproSources);//把概率还给原分摊的奖品
                    }
                }

                int r = actiAwardRecordService.insertSelective(actiAwardRecord); //插入兑换记录表
                if (r > 0) {
                    //构建返回值
                    Map<String, Object> retrunM = new HashMap<>();
                    retrunM.put("name", actiAwardRecord.getName());
                    String gold = actiAwardRecord.getDescr();
                    retrunM.put("gold", gold);//如果是红包，则这个不为空
                    retrunM.put("id",actiAwardRecord.getAwardId());

                    //更新memcached ，减去抽奖所需相应的局数
                    boolean b = false;
                    if (nowCount > 0 && updateCount >= 0) b = memcachedClient.set(memcKey, 0, updateCount);
                    if (b)  log.debug("--------------------::update memcache play card count success !");
                    else  log.debug("--------------------::update memcache play card count fail !");

                    log.debug("--------------------::Lottery success!");
                    result.put(CommonContants.RESULT, Boolean.TRUE);
                    result.put(CommonContants.ERROR, 0);
                    result.put(CommonContants.DATA, retrunM);
                    result.put(CommonContants.MESSAGE, "抽奖成功!");
                    return result;
                } else {
                    log.debug("--------------------::Insert record failed!");
                    result.put(CommonContants.RESULT, Boolean.FALSE);
                    result.put(CommonContants.ERROR, -4);
                    result.put(CommonContants.MESSAGE, "插入记录失败！");
                    return result;
                }
            } else {//更新库存失败，说明没有库存了。
                log.debug("--------------------::Insufficient stock!");
                result.put(CommonContants.RESULT, Boolean.FALSE);
                result.put(CommonContants.ERROR, -5);
                result.put(CommonContants.MESSAGE, "库存不足！");
                return result;
            }
        } else {
            log.debug("--------------------::Activity time not started or ended!");
            result.put(CommonContants.RESULT, Boolean.FALSE);
            result.put(CommonContants.ERROR, -2);
            result.put(CommonContants.MESSAGE, "活动时间未开始或者已结束！");
            return result;
        }
    }

    /**
     * @Author:zun.wei , @Date:2017/9/20 11:48
     * @Description:增减概率
     * @param awardPros
     * @param actiAwardPro
     * @param name
     * @param awardId
     * @param stock
     * @param proStock
     * @param type
     * @param d
     * @param nowTimeStr
     */
    private void fluctuationProbability(List<AwardPro> awardPros, Map<String, Object> actiAwardPro, String name,
                                        int awardId, int stock, double proStock, int type, int d, String nowTimeStr) {
        //给奖品添加概率
        double aap = Double.parseDouble(actiAwardPro.get("daliAddPro") + "") * (d + 1);
        double awardProStock = proStock + aap;
        AwardPro awardPro = new AwardPro(awardId, name, stock, awardProStock, type);
        awardPros.add(awardPro);
        ActiAwardPro apro = actiAwardProMapper.selectByAwarId(awardId);
        apro.setAwardProStock(awardProStock + "");
        apro.setRemark(nowTimeStr);
        actiAwardProMapper.updateByPrimaryKeySelective(apro);

        //从源奖品分摊概率给该奖品
        ActiAwardPro aproSources = actiAwardProMapper.selectByAwarId
                (Integer.parseInt(actiAwardPro.get("proSources") + ""));
        double aps = Double.parseDouble(aproSources.getAwardProStock()) - aap ;
        aproSources.setAwardProStock(aps + "");
        aproSources.setRemark(nowTimeStr);
        actiAwardProMapper.updateByPrimaryKeySelective(aproSources);//把概率分摊给该奖品
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
