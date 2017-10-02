package kodutoo2.validation;

import java.util.Arrays;
import java.util.List;

public class StudentCardValidator implements CardValidator {

    private List<String> allowedSchoolIds = Arrays.asList("MHG", "KARDLA", "NOO", "REAAL");
    private List<String> allowedSocialIdFirstNumbers = Arrays.asList("5", "6");

    @Override
    public boolean validateCard(String socialId, String schoolId) {
        if(socialId == null || schoolId == null){
            return false;
        }
        else if(allowedSchoolIds.contains(schoolId) &&
                allowedSocialIdFirstNumbers.contains(Character.toString(socialId.charAt(0)))){
            return true;
        }
        return false;
    }
}
