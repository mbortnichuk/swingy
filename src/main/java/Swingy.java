import com.unitfactory.mbortnic.messages.Messages;
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
            if (args.length < 1 || args.length > 4) {
                System.out.println(Messages.USAGE);
                System.exit(1);
            }
            if (args[0].equals("console")) {
                ConsoleDisplay.start();
            } else if (args[0].equals("gui")) {
                GuiDisplay guiDisplay = new GuiDisplay();
                guiDisplay.guiView();
            } else {
                System.out.println(Messages.USAGE);
            }
        } catch (Exception e) {
            System.out.println(Messages.USAGE);
        } finally {
            Writer.close();
        }
    }
}
