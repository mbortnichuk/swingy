import com.unitfactory.mbortnic.view.console.ConsoleDisplay;
import com.unitfactory.mbortnic.view.gui.GuiDisplay;
import com.unitfactory.mbortnic.writer.Writer;

public class Swingy {

    public static void main(String[] args) {

        /**
         * TODO: Fix flee-fight conditions in collidedWithEnemy()  -  optional
         * TODO: fix players.txt file from loosing info
         */
        try {
            Writer.createPlayersFile();
            if (args.length != 1 && args.length > 4) {
                System.out.println("Usage: java -jar swingy.jar [console/gui]");
                System.exit(1);
            }
            if (args[0].equals("console")) {
                ConsoleDisplay.start();
//                System.out.println("console");
            } else if (args[0].equals("gui")) {
                GuiDisplay guiDisplay = new GuiDisplay();
                guiDisplay.guiView();
//                System.out.println("gui");
            } else {
                System.out.println("Usage: java -jar swingy.jar [console/gui]");
            }
        } catch (Exception e) {
            System.out.println("Usage: java -jar swingy.jar [console/gui]");
        } finally {
            Writer.close();
        }
    }
}
