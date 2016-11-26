package com.qingqing.search.demo;

import com.qingqing.search.demo.domain.Master;
import com.qingqing.search.demo.mapper.MasterMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by yaoqijun.
 * Date:2016-11-26
 * Email:yaoqijunmail@gmail.io
 * Descirbe: Master 配置处理方式
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MasterMapperTest {

    @Autowired
    private MasterMapper masterMapper;

    @Test
    public void testFindAll(){
        List<Master> masters = masterMapper.findAll();
        for (Master master : masters) {
            System.out.println(master.toString());
        }
    }
}
