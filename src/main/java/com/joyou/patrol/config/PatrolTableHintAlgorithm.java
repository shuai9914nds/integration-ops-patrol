package com.joyou.patrol.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

@Slf4j
public class PatrolTableHintAlgorithm implements HintShardingAlgorithm<String> {
    @Override
    public Collection<String> doSharding(Collection<String> tableNameList, HintShardingValue<String> hintShardingValue) {
//        log.info("[MyTableHintShardingAlgorithm] hintShardingValue: [{}]", hintShardingValue);
//        Collection<String> tableResultList = new ArrayList<>();
//        int tableSize = tableNameList.size();
//        Collection<String> hintShardingValueValueList = hintShardingValue.getValues();
//        for (String tableName : tableNameList) {
//            for (Integer shardingValue : hintShardingValueValueList) {
//                if (tableName.endsWith(String.valueOf(shardingValue % 2))) {
//                    tableResultList.add(tableName);
//                }
//                if (tableResultList.size() >= tableSize) {
//                    return tableResultList;
//                }
//            }
//        }
//        return tableResultList;
        return null;
    }

    @Override
    public Properties getProps() {
        return null;
    }

    @Override
    public void init(Properties properties) {

    }
}
