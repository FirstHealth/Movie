package com.bw.movie.convert;

import com.bw.movie.bean.movieinfo.MovieActorBean;
import com.bw.movie.bean.movieinfo.MovieDirectorBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *  GreenDao 需要的转换类
 *  这个类是吧 List 转成 String
 *  因为GreenDao不支持直接保存List，所以转成GreenDao支持的类型
 */
public class ListToStringConvert_MovieDirectorBean implements PropertyConverter<List<MovieDirectorBean>, String> {
    private final Gson mGson;
    public ListToStringConvert_MovieDirectorBean() {
        mGson = new Gson();
    }

    /**
     * 从数据库中查出，转换成我们Bean类的类型
     * @param databaseValue 数据库中的字符串数据
     * @return 想要的集合
     */
    @Override
    public List<MovieDirectorBean> convertToEntityProperty(String databaseValue) {
        Type type = new TypeToken<ArrayList<MovieDirectorBean>>(){}.getType();
        ArrayList<MovieDirectorBean> itemList= mGson.fromJson(databaseValue, type);
        return itemList;
    }


    /**
     * 这里面是把原来的数据，转换成想要入库的数据
     * @param entityProperty 原来的List集合
     * @return String 入库的数据字符串
     */
    @Override
    public String convertToDatabaseValue(List<MovieDirectorBean> entityProperty) {
        String dbString = mGson.toJson(entityProperty);
        return dbString;
    }
}
