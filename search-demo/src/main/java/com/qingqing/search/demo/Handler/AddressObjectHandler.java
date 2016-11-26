package com.qingqing.search.demo.handler;

import com.qingqing.search.client.handler.ObjectAbstractHandler;
import com.qingqing.search.demo.domain.Address;

import java.util.Map;

/**
 * Created by yaoqijun.
 * Date:2016-11-26
 * Email:yaoqijunmail@gmail.io
 * Descirbe:
 */
public class AddressObjectHandler extends ObjectAbstractHandler<Address> {

    @Override
    protected Address convertInnerObject(Map<String, Object> map) {
        return new Address(map.get("province").toString(), map.get("city").toString());
    }
}
