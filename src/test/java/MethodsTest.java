import mvc.controller.interactions.GameBuilder;
import mvc.model.objects.enums.PublicObjective;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MethodsTest {
    @Test
    public void assertNotEquals(){
        GameBuilder gameBuilder = new GameBuilder(false);
        List<PublicObjective> list1 = new ArrayList<PublicObjective>();
        List<PublicObjective> list2 = new ArrayList<PublicObjective>();
        list1 = gameBuilder.createPublicObjectives();
        list2 = gameBuilder.createPublicObjectives();

        assertNotEquals("failure - there aren't duplicates", list1, list2);


    }


    public void coreTest(){
        GameBuilder gameBuilder = new GameBuilder(false);
        List<PublicObjective> testRandomPublicObjectivesCard = new ArrayList<PublicObjective>();
        testRandomPublicObjectivesCard = gameBuilder.createPublicObjectives();
    }
}
