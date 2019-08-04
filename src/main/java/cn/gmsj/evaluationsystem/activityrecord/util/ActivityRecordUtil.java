package cn.gmsj.evaluationsystem.activityrecord.util;

import cn.gmsj.evaluationsystem.activityrecord.domain.entity.ActivityRecordEntity;
import cn.gmsj.evaluationsystem.activityrecord.domain.repository.ActivityRecordRepository;
import cn.gmsj.evaluationsystem.activityrecord.enums.ActivityRecord;
import cn.gmsj.evaluationsystem.activityrecord.enums.ActivityRecordType;
import cn.gmsj.evaluationsystem.enums.UserDataType;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import cn.gmsj.evaluationsystem.userreg.domain.entity.UserRegEntity;
import cn.gmsj.evaluationsystem.utils.StringUtil;

/**
 * 活动记录
 * Created by XiaoWen on 2019/8/3
 */
public class ActivityRecordUtil {

    /**
     * 创建活动记录  -- 双向记录
     * 例如:贵州省能源局通过了你的入库申请
     * @param userRegEntity1
     * @param userRegEntity2
     * @param activityRecord
     * @param activityRecordType
     * @param activityRecordRepository
     */
    public static Boolean createActivityRecordByTarget(UserRegEntity userRegEntity1, UserRegEntity userRegEntity2,
                                                    ActivityRecord activityRecord, ActivityRecordType activityRecordType,
                                                    ActivityRecordRepository activityRecordRepository){
        if(null == userRegEntity1 || null == userRegEntity2
                || null == activityRecord || null == activityRecordType){
            return false;
        }
        /**
         * 拼装消息
         */
        String message ;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(userRegEntity1.getUserDataType().getName());
        stringBuffer.append(userRegEntity1.getUname());
        stringBuffer.append(activityRecordType.getName()+"了");
        stringBuffer.append(userRegEntity2.getUname()+"("+userRegEntity2.getNumberId()+")的");
        stringBuffer.append(activityRecord.getName());
        message = stringBuffer.toString();
        /**
         * 创建记录  -- 双向记录
         */
        ActivityRecordEntity activityRecordEntity1 = new ActivityRecordEntity();
       // activityRecordEntity1.setUserRegEntity(userRegEntity1);
        activityRecordEntity1.setMessage(message);
        activityRecordEntity1.setUserDataType(UserDataType.SPECIALIST);
        ActivityRecordEntity activityRecordEntity2 = new ActivityRecordEntity();
        //activityRecordEntity2.setUserRegEntity(userRegEntity2);
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
        }else{
            stringBuffer.append(userEntity.getUnitName());
        }
        stringBuffer.append(activityRecordType.getName()+"了");
        stringBuffer.append(activityRecord.getName());
        message = stringBuffer.toString();
        /**
         * 创建记录  -- 单向记录
         */
        ActivityRecordEntity activityRecordEntity = new ActivityRecordEntity();
        if(StringUtil.equals(userEntity.getUserDataType().getName(),UserDataType.SPECIALIST.getName())){
            stringBuffer.append(userEntity.getName()+"("+userEntity.getIdNumber()+")");
        }else{
            stringBuffer.append(userEntity.getUnitName());
        }
        activityRecordEntity.setMessage(message);
        activityRecordEntity.setUserDataType(UserDataType.SPECIALIST);
        activityRecordRepository.save(activityRecordEntity);
        return true;
    }

}
