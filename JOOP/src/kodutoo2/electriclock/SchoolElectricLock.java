package kodutoo2.electriclock;

import kodutoo2.validation.StudentCardValidator;

public class SchoolElectricLock {

    private String schoolId;
    private StudentCardValidator studentCardValidator;

    public SchoolElectricLock(String schoolId) {
        this.schoolId = schoolId;
        studentCardValidator = new StudentCardValidator();
    }

    public void processCard(String socialId) {
        if (socialId == null) {
            System.out.println("Isikukoodi ei tuvastatud.");
        } else {
            boolean isApproved = studentCardValidator.validateCard(socialId, schoolId);
            if (isApproved) {
                System.out.println(String.format("Õpilane %s koolist %s on lubatud uksest sisse", socialId, schoolId));
            } else {
                System.out.println(String.format("Õpilane %s koolist %s ei ole lubatud uksest sisse", socialId, schoolId));
            }
        }

    }
}
