package com.qianfeng.fxmall.goods.dao.impl;

import com.qianfeng.fxmall.commons.info.SystemConstantsUtils;
import com.qianfeng.fxmall.commons.mybatis.MyBatisSessionFactoryUtils;
import com.qianfeng.fxmall.goods.bean.WxbGood;
import com.qianfeng.fxmall.goods.dao.IGoodsDAO;
import com.qianfeng.fxmall.goods.mapper.GoodsMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * MyBatis的商品数据访问层
 *
 * 注意：
 * 异常在dao层不要捕获
 */
@Component
public class GoodsDAOImpl implements IGoodsDAO {
    @Autowired
    private SqlSession session;
    @Override
    public List<WxbGood> queryGoodsByPage(Integer page) throws Exception {
        GoodsMapper goodsMapper = session.getMapper(GoodsMapper.class);
        List<WxbGood> goods = goodsMapper.queryGoodsByPage(page, SystemConstantsUtils.Page.PAGE_SIZE);
        return goods;
    }

    @Override
    public void insertGoods(WxbGood wxbGood) {
        GoodsMapper goodsMapper = session.getMapper(GoodsMapper.class);
        goodsMapper.insertGoods(wxbGood);
        session.commit();
    }
}
