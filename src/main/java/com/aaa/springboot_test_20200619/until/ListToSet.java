package com.aaa.springboot_test_20200619.until;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * List集合转换为Set集合
 */
public class ListToSet {
    public static Set<String> changeListToSet(List<String> source){
        Set<String> set=new HashSet<>();
        Iterator<String> iterator=source.iterator();
        while (iterator.hasNext()){
            set.add(iterator.next());
        }
        return set;
    }
}
