package sample.Dao;

public  enum RuningTimeEnum {
    Inbound(1,"入站"),Outbund(2,"已到站"),Waiting(3,"出站"),Inroute(4,"途中"),End(5,"已结束"),Error(-1,"故障"),Nodeparture(0,"未发车");
    private final Integer value;
    private final String name;
    private RuningTimeEnum(Integer value,String name){
        this.value = value;
        this.name = name;
    }
    public Integer getValue() {
        return value;
    }
    public String getName() {
        return name;
    }
    public static String getName(int index){
        for (RuningTimeEnum c : RuningTimeEnum.values()) {
            if(c.getValue()==index){
                return c.getName();
            }
        }
        return "";
    }
    public static Integer getValue(String name){
        for (RuningTimeEnum c : RuningTimeEnum.values()) {
            if(c.getName().equals(name)){
                return c.getValue();
            }
        }
        return -2;
    }
}