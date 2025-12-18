package enums;

public enum Movie {
    AVATAR_2("AVATAR 2"),
    GAI_GIA("GÁI GIÀ LẮM CHIÊU"),
    JOHN_WICK("John Wick"),
    MAN_OF_STELL("Man of Steel");

    private final String title;


    Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
