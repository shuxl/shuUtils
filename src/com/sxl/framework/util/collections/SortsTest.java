package com.sxl.framework.util.collections;

import java.util.*;

/**
 * @author shuxiaolong
 * @time 2018/12/26 15:58
 * @description
 */
public class SortsTest {

    public static void main(String[] args) {
        List<SortsEntity> sortsEntityList = new ArrayList<>(Arrays.asList(
                new SortsEntity(5,true,new Date(2018,5,1)),
                new SortsEntity(4,false,new Date(2018,5,1)),
                new SortsEntity(2,false,null),
                new SortsEntity(3,false,new Date(2018,5,1)),
                new SortsEntity(1,false,new Date(2018,3,1)),
                new SortsEntity(2,true,new Date(2018,7,1)),
                new SortsEntity(2,false,new Date(2018,6,1)),
                new SortsEntity(3,true,null),
                new SortsEntity(4,false,new Date(2018,5,1)),
                new SortsEntity(3,false,new Date(2018,6,1)),
                new SortsEntity(2,false,new Date(2018,10,1)),
                new SortsEntity(2,true,new Date(2018,2,1)),
                new SortsEntity(1,true,null)
        ));

        Comparator<SortsEntity> comparator1 = new Comparator<SortsEntity>() {
            @Override
            public int compare(SortsEntity o1, SortsEntity o2) {
                if (null == o1.getSort1()) {
                    return 1;
                }
                if (null == o2.getSort1()) {
                    return -1;
                }
                return o1.getSort1() - o2.getSort1();
            }
        };

        Comparator<SortsEntity> comparator2 = new Comparator<SortsEntity>() {
            @Override
            public int compare(SortsEntity o1, SortsEntity o2) {
                if (o1.isSort2()) {
                    return -1;
                } else if (o2.isSort2()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };

        Comparator<SortsEntity> comparator3 = new Comparator<SortsEntity>() {
            @Override
            public int compare(SortsEntity o1, SortsEntity o2) {
                if (null == o1.getCreateTime()) {
                    return 1;
                }
                if (null == o2.getCreateTime()) {
                    return -1;
                }
                if (o1.getCreateTime().getTime() - o2.getCreateTime().getTime() == 0) {
                    return 0;
                } else {
                    return o1.getCreateTime().getTime() - o2.getCreateTime().getTime() > 0?1:-1;
                }
            }
        };

        List<Comparator<SortsEntity>> comparatorsList = new ArrayList<>(Arrays.asList(comparator1, comparator2, comparator3));

        Collections.sort(sortsEntityList, (o1, o2) -> {
            for (Comparator<SortsEntity> comparator : comparatorsList) {
                int comparatorResult = comparator.compare(o1,o2);
                if (0 != comparatorResult) { // FIXME 0 的判断非常重要，是排序优先级的基础
                    return  comparatorResult;
                }
            }
            return 0;
        });

        for (SortsEntity entity : sortsEntityList) {
            System.err.println(entity.toString());
        }
    }
}
