package enums;

public enum CinemaBrand {
    CGV("cgv"),
    BHD("bhd"),
    CGV_VINCOM_GO_VAP("CGV - Vincom Gò Vấp");



    private final String title;

    CinemaBrand(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
