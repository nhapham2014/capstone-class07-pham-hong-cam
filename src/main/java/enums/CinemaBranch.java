package enums;

public enum CinemaBranch {
    CGV_GOLDEN_PLAZA("CGV - Golden Plaza"),
    CGV_VIVO_CITY("CGV - VivoCity"),
    CGV_VINCOM_GO_VAP("CGV - Vincom Gò Vấp");



    private final String title;

    CinemaBranch(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
