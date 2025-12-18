package enums;

public enum CinemaBranch {
    CGV_GOLDEN_PLAZA("CGV - Golden Plaza"),
    CGV_VIVOCITY("CGV - VivoCity");

    private final String title;


    CinemaBranch(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
