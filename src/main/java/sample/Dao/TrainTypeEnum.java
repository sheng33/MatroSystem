package sample.Dao;

import java.util.HashMap;

public enum TrainTypeEnum {
    OneType(1,"一型"),TwoType(2,"二型"),ThreeType(3,"三型");

    private final Integer value;
    private final String name;
    private TrainTypeEnum(Integer value,String name){
        this.value = value;
        this.name = name;
    }
    public static HashMap<Integer, String> getAll(){
        HashMap<Integer, String> map = new HashMap<>();
        for (TrainTypeEnum c : TrainTypeEnum.values()){
            map.put(c.getValue(),c.getName());
        }
        return map;
    }
    public Integer getValue() {
        return value;
    }
    public String getName() {
        return name;
    }
    public static String getName(int index){
        for (TrainTypeEnum c : TrainTypeEnum.values()) {
            if(c.getValue()==index){
                return c.getName();
            }
        }
        return "";
    }
    public static Integer getValue(String name){
        for (TrainTypeEnum c : TrainTypeEnum.values()) {
            if(c.getName().equals(name)){
                return c.getValue();
            }
        }
        return -2;
    }
}
