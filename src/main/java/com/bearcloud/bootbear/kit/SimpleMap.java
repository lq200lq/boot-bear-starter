package com.bearcloud.bootbear.kit;


import java.util.HashMap;

public class SimpleMap extends HashMap<String,Object> {

    /**
     * 批量放值，顺序为k,v,k,v...
     * @param args
     * @return
     */
    public SimpleMap puts(Object...args){
        int i = 0;
        String k = "";
        for(Object arg:args){
            if(i == 0){
                k = arg.toString();
                i++;
            }else{
                super.put(k,arg);
                i--;
            }
        }
        return this;
    }


    @Override
    public SimpleMap put(String k, Object v){
        super.put(k,v);
        return this;
    }

    /**
     * 满足条件时放值
     * @param k
     * @param v
     * @param boo
     * @return
     */
    public SimpleMap put(String k, Object v,boolean boo){
        if(boo){
            super.put(k,v);
        }
        return this;
    }

    public String get(String k){
        Object obj = super.get(k);
        if(obj != null){
            return obj.toString();
        }
        return null;
    }


}
