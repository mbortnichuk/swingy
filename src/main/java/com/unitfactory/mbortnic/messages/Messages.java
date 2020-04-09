package com.unitfactory.mbortnic.messages;

import com.unitfactory.mbortnic.utils.Util;

public class Messages {

    public static final String ENEMY_DEFEATED = Util.ANSI_GREEN + "You have defeated your enemy!" + Util.ANSI_RESET;
    public static final String ON_EXIT = Util.ANSI_GREEN + "Thank you for playing!" + Util.ANSI_RESET;
    public static final String INVALID_INPUT = Util.ANSI_RED + "Invalid input. Try again." + Util.ANSI_RESET;
    public static final String INVALID_NAME = Util.ANSI_YELLOW + "Please enter your hero name!" + Util.ANSI_RESET;
    public static final String MAX_EXP_GAINED = Util.ANSI_GREEN + "Your adventure will continue... But next time..." + Util.ANSI_RESET;
    public static final String ENEMY_ENCOUNTER = Util.ANSI_YELLOW + "You've encountered your enemy" + Util.ANSI_RESET;
    public static final String COWARDICE = Util.ANSI_PURPLE + "You've lost 10XP coward!\n" + Util.ANSI_RESET;
    public static final String LOST_BATTLE = Util.ANSI_RED + "You lost your battle!\nSWINGY is over for you!" + Util.ANSI_RESET;
    public static final String WON_BATTLE = Util.ANSI_GREEN + "Battle won. 500 Experience gained." + Util.ANSI_RESET;
    public static final String LEVEL_UP = Util.ANSI_GREEN + "You've got level up!" + Util.ANSI_RESET;
    public static final String READ_FILE_ERROR = Util.ANSI_YELLOW + "Error occured while updating hero statistics in resources file: " + Util.ANSI_RESET;
    public static final String USAGE = Util.ANSI_YELLOW + "Usage: java -jar swingy.jar [console/gui]" + Util.ANSI_RESET;

}
