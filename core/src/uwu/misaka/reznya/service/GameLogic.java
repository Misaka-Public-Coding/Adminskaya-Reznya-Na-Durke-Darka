package uwu.misaka.reznya.service;

import com.badlogic.gdx.utils.Array;
import uwu.misaka.reznya.Nyahoi;
import uwu.misaka.reznya.entities.Player;

public class GameLogic {
    public static Array<Player> playersInRound;
    public static Player lastPlayer = null;
    static int lastNyahoi = 0;

    public static boolean checkLose() {
        for (Player p : Nyahoi.players) {
            if (p.isYou) {
                return false;
            }
        }
        Nyahoi.loadLoseMenu();
        return true;
    }

    public static boolean checkWin() {
        if (Nyahoi.players.size == 1 && Nyahoi.players.first().isYou) {
            Nyahoi.loadWinMenu();
            return true;
        }
        ;
        return false;
    }

    public static void initLogic() {
        playersInRound = Nyahoi.players;
        lastPlayer = playersInRound.first();
    }

    public static void logicTurn() {
        //todo проверка на движение объектов
        if (!lastPlayer.isYou) {
            Nyahoi.makeABotMove(lastPlayer);
        }
        if ((lastPlayer.moved && lastPlayer.shouted && Nyahoi.canMovement) || !Nyahoi.players.contains(lastPlayer, false)) {
            int nyahoi = playersInRound.indexOf(lastPlayer, false);
            if (nyahoi < 0) {
                nyahoi = lastNyahoi;
            }
            while (true) {
                nyahoi++;
                if (nyahoi >= playersInRound.size) {
                    nyahoi = 0;
                }
                if (Nyahoi.players.contains(playersInRound.get(nyahoi), false)) {
                    lastPlayer = playersInRound.get(nyahoi);
                    lastNyahoi = nyahoi;
                    break;
                }
            }
            lastPlayer.shouted = false;
            lastPlayer.moved = false;
        }
        if (checkLose() || checkWin()) {
            return;
        }
        ;
    }
}
