package sample.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainDao {
    private int trainId;
    private String trainName;
    private String trainType;
    private String serviceTime;
    private String retirementTime;
    private int overhaulsNumber;
    private String lastRepairTime;
    private int state;
}
