package com.qianfeng.fxmall.memeber.mapper;

import com.qianfeng.fxmall.memeber.po.WxbMemeber;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemeberMapper {
    WxbMemeber checkQQNum(@Param("qqNum") String qqNum);
}
