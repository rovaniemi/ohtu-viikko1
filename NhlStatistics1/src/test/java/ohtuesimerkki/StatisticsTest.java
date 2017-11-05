package ohtuesimerkki;

import ohtuesimerkki.Player;
import ohtuesimerkki.Reader;
import ohtuesimerkki.Statistics;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp(){
        stats = new Statistics(readerStub);
    }

    @Test
    public void newStats() {
        assertNotEquals(stats, null);
    }

    @Test
    public void searchTest() {
        assertNotEquals(stats.search("Gretzky"), null);
    }

    @Test
    public void notFoundReturnNull() {
        assertEquals(stats.search("Hurri"), null);
    }

    @Test
    public void rightNumberOfPlayersInTeam() {
        assertEquals(stats.team("EDM").size(), 3);
    }

    @Test
    public void topScorersFindBestPlayer() {
        assertEquals(stats.topScorers(1).get(0).getName(), "Gretzky");
    }
}