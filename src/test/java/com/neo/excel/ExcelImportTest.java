package com.neo.excel;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import com.alibaba.fastjson.JSONObject;
import com.neo.entity.User;
import com.neo.entity.excel.UserInfo;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

/**
 * @author moxianbin
 * @date 2019/3/2.
 */
public class ExcelImportTest {

   private final static Logger log = LoggerFactory.getLogger(ExcelImportTest.class);

    //@Test
    public void test(){
        List<UserInfo> userInfos = ExcelImportUtil.importExcel(new File("/Users/moxianbin/git/study-service-pro/src/main/resources//模板.xlsx"),
                UserInfo.class, new ImportParams());
        log.info("{}", JSONObject.toJSONString(userInfos));
    }
}
