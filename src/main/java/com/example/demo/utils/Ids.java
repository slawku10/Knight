package com.example.demo.utils;

import java.util.HashSet;
import java.util.Set;

public class Ids {
        static public int getNewId(Set<Integer> keysSoFar) {
        if (keysSoFar.isEmpty()){
            return 0;
        } else{
            Integer newId = keysSoFar.stream().max((o1,o2) ->o1.compareTo(o2)).get();
            return newId + 1;
        }
    }

//    static private int getNewId(Set<Integer> keysSoFar) {
//        if (keysSoFar.isEmpty()){
//            return 0;
//        } else {
//            Set<Integer> keySet = new HashSet<>(knights.keySet());
//            for (int i = 1; i<Integer.MAX_VALUE; i++){
//                if (keySet.contains(i)){
//                    continue;
//                } else {
//                    return i;
//                }
//            }
//        }
//        System.out.println("Key has max size of integer");
//        throw new IndexOutOfBoundsException();
//    }
}
