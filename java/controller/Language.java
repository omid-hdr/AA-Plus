package controller;

public enum Language {
    //TODO complete this.maybe not important!
    LOGIN("ورود"),
    REGISTER("ثبت نام"),
    GUEST("مهمان"),
    RESET("پاک کردن"),
    SETTING("تنظیمات"),
    PROFILE_MENU(""),
    START_GAME(""),
    WELCOME(""),
    PLAYER2_GAME(""),
    EXIT(""),
    SCOREBOARD(""),
    CHANGE_LANGUAGE("تغییر زبان"),
    BACK("برگشت"),
    MUTE("سکوت"),
    WHITE_BLACK("سیاه و سفید"),
    MAIN_MANU(""),
    RESTART_GAME(""),
    SET_LEVEL(""),
    MEDIUM(""),
    HARD(""),
    EASY(""),
    AMOUNT_OF_BALLS(""),
    MAPS(""),
    DIFFICULTY(""),
    SELECT_AVATAR(""),
    CHANGE_USERNAME(""),
    CHANGE_PASSWORD(""),
    LOGOUT(""),
    DELETE_ACCOUNT(""),
    SELECT_IMAGE(""),
    SHOOT1("پرتاب 1"),
    SHOOT2("پرتاب 2"),
    FREEZE("یخ زدن");
    private static boolean isFarsi = false;
    private final String farsi;

    Language(String farsi) {
        this.farsi = farsi;
    }

    public static boolean isIsFarsi() {
        return isFarsi;
    }

    public static void setIsFarsi(boolean isFarsi) {
        Language.isFarsi = isFarsi;
    }

    public String getString() {
        if (isFarsi)
            return this.farsi;
        else return this.toString().toLowerCase().replaceAll("_", " ");
    }
}
