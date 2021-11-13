package uwu.misaka.reznya.service;

import com.badlogic.gdx.utils.Array;
import uwu.misaka.reznya.Nyahoi;
import uwu.misaka.reznya.entities.Player;

public class GameLogic {
    public static Array<Player> playersInRound;
    public static Player lastPlayer = null;

    public static boolean checkLose() {
        for (Player p : Nyahoi.players) {
            if (p.isYou) {
                return false;
            }
        }
        System.out.print("You're baka");
        return true;
    }

    public static boolean checkWin() {
        if (Nyahoi.players.size == 1 && Nyahoi.players.first().isYou) {
            System.out.print("You're nyahoi");
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
        if (lastPlayer.moved && lastPlayer.shouted && Nyahoi.canMovement) {
            int nyahoi = playersInRound.indexOf(lastPlayer, false);
            while (true) {
                nyahoi++;
                if (playersInRound.indexOf(lastPlayer, false) >= playersInRound.size) {
                    nyahoi = 0;
                }
                if (Nyahoi.players.contains(playersInRound.get(nyahoi), false)) {
                    lastPlayer = playersInRound.get(nyahoi);
                    break;
                }
            }
            lastPlayer.shouted = false;
            lastPlayer.moved = false;
        }
        checkLose();
        checkWin();
    }
}
