package cn.gmsj.evaluationsystem.activityrecord.util;

import cn.gmsj.evaluationsystem.activityrecord.domain.entity.ActivityRecordEntity;
import cn.gmsj.evaluationsystem.activityrecord.domain.repository.ActivityRecordRepository;
import cn.gmsj.evaluationsystem.activityrecord.enums.ActivityRecord;
import cn.gmsj.evaluationsystem.activityrecord.enums.ActivityRecordType;
import cn.gmsj.evaluationsystem.enums.UserDataType;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import cn.gmsj.evaluationsystem.utils.StringUtil;

/**
 * 活动记录
 * Created by XiaoWen on 2019/8/3
 */
public class ActivityRecordUtil {

    /**
     * 创建活动记录  -- 双向记录
     * 例如:贵州省能源局通过了你的入库申请
     * @param userEntity1
     * @param userEntity2
     * @param activityRecord
     * @param activityRecordType
     * @param activityRecordRepository
     */
    public static Boolean createActivityRecordByTarget(UserEntity userEntity1, UserEntity userEntity2,
                                                    ActivityRecord activityRecord, ActivityRecordType activityRecordType,
                                                    ActivityRecordRepository activityRecordRepository){
        if(null == userEntity1 || null == userEntity2
                || null == activityRecord || null == activityRecordType){
            return false;
        }
        /**
         * 拼装消息
         */
        String message ;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(userEntity1.getUserDataType().getName());
        if(StringUtil.equals(userEntity1.getUserDataType().getName(),UserDataType.SPECIALIST.getName())){
            stringBuffer.append(userEntity1.getName()+"("+userEntity1.getIdNumber()+")");
        }else if(StringUtil.equals(userEntity1.getUserDataType().getName(),UserDataType.THIRD_PARTY.getName())){
            stringBuffer.append(userEntity1.getUnitName());
        }else{
            stringBuffer.append(userEntity1.getUserDataType().getName() + userEntity1.getName());
        }
        stringBuffer.append(activityRecordType.getName()+"了");
        if(StringUtil.equals(userEntity1.getUserDataType().getName(),UserDataType.SPECIALIST.getName())){
            stringBuffer.append(userEntity2.getName()+"("+userEntity2.getIdNumber()+")的");
        }else if(StringUtil.equals(userEntity1.getUserDataType().getName(),UserDataType.THIRD_PARTY.getName())){
            stringBuffer.append(userEntity2.getUnitName()+"的");
        }else{
            stringBuffer.append(userEntity1.getUserDataType().getName() + userEntity1.getName()+"的");
        }
        stringBuffer.append(activityRecord.getName());
        message = stringBuffer.toString();
        /**
         * 创建记录  -- 双向记录
         */
        ActivityRecordEntity activityRecordEntity1 = new ActivityRecordEntity();
       activityRecordEntity1.setUserEntity(userEntity1);
        activityRecordEntity1.setMessage(message);
        activityRecordEntity1.setUserDataType(UserDataType.SPECIALIST);
        ActivityRecordEntity activityRecordEntity2 = new ActivityRecordEntity();
        activityRecordEntity2.setUserEntity(userEntity2);
        activityRecordEntity2.setMessage(message);
        activityRecordEntity1.setUserDataType(UserDataType.BUREAU_ENERGY);
        activityRecordRepository.save(activityRecordEntity1);
        activityRecordRepository.save(activityRecordEntity2);
        return true;
    }


    /**
     * 创建活动记录 -- 单向记录
     * 例如:张三(身份证号)提交了入库申请
     * @param userEntity
     * @param activityRecord
     * @param activityRecordType
     * @param activityRecordRepository
     */
    public static Boolean createActivityRecordByOneself(UserEntity userEntity, ActivityRecord activityRecord,
                                                        ActivityRecordType activityRecordType, ActivityRecordRepository
                                                             activityRecordRepository){
        if(null == userEntity || null == activityRecord || null == activityRecordType){
            return false;
        }
        /**
         * 拼装消息
         */
        String message;
        StringBuffer stringBuffer = new StringBuffer();
        if(StringUtil.equals(userEntity.getUserDataType().getName(),UserDataType.SPECIALIST.getName())){
            stringBuffer.append(userEntity.getName()+"("+userEntity.getIdNumber()+")");
        }else if(StringUtil.equals(userEntity.getUserDataType().getName(),UserDataType.THIRD_PARTY.getName())){
            stringBuffer.append(userEntity.getUnitName());
        }else{
            stringBuffer.append(userEntity.getUserDataType().getName() + userEntity.getName());
        }
        stringBuffer.append(activityRecordType.getName()+"了");
        stringBuffer.append(activityRecord.getName());
        message = stringBuffer.toString();
        /**
         * 创建记录  -- 单向记录
         */
        ActivityRecordEntity activityRecordEntity = new ActivityRecordEntity();
        activityRecordEntity.setMessage(message);
        activityRecordEntity.setUserDataType(UserDataType.SPECIALIST);
        activityRecordRepository.save(activityRecordEntity);
        return true;
    }

}
