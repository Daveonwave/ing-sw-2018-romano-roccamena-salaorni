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
        int sum = points.getFavorTokensPoints() + points.getOpenSpacesLostPoints() + points.getPrivateObjectivePoints() + points.getPublicObjectivePoints();

        assertEquals(23, points.getTotalPoints());
        assertEquals(23, sum);
    }
}