package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class JsonUtils {

    public static Object[][] getJsonData(){

        Object[][] data = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("src/test/resources/loginData.json");

            List<LoginData> loginList =
                    mapper.readValue(file,
                            new TypeReference<List<LoginData>>() {});

            data = new Object[loginList.size()][2];

            for(int i=0;i<loginList.size();i++){
                data[i][0] = loginList.get(i).getUsername();
                data[i][1] = loginList.get(i).getPassword();
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return data;
    }
}