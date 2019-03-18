import com.bytd.crm.roadinfo.core.JsonHelper;
import com.bytd.crm.roadinfo.dao.DRoadInfoDao;
import com.bytd.crm.roadinfo.entities.DRoadInfo;

public class GsonTest {
    public static void main(String[] args){

        try {
            DRoadInfo dRoadInfo = DRoadInfoDao.queryValidRoadInfoById(76702);
            System.out.println(JsonHelper.objectToJson(dRoadInfo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Person{
    private int a;
    void setA(int inputA){
        a = inputA;
    }

    int getA(){
        return a;
    }
}

class Student extends Person {
    private String b;
    void SetB(String inputB){
        b = inputB;
    }

    String getB(){
        return b;
    }
}
