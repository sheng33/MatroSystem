package sample.Dao;

public  enum RuningTimeEnum {
    Inbound(0,"入站"),Outbund(1,"出站"),Waiting(2,"等待中"),Inroute(3,"途中"),Error(-1,"故障"),Nodeparture(4,"未发车");
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