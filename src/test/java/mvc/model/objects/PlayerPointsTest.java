package mvc.model.objects;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerPointsTest {

    @Test
    public void getTotalPoints() {
        PlayerPoints points =  new PlayerPoints(0, 0, 0, 0);
        points.setPublicObjectivePoints(15);
        points.setPrivateObjectivePoints(10);
        points.setFavorTokensPoints(1);
        points.setOpenSpacesLostPoints(-3);
        assertEquals(23, points.getTotalPoints());
    }
}