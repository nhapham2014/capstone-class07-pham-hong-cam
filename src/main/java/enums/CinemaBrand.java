package enums;

public enum CinemaBrand {
    BHD("bhd"),
    CGV("cgv"),
    MEGAGS("megags");


    private final String title;


    CinemaBrand(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
