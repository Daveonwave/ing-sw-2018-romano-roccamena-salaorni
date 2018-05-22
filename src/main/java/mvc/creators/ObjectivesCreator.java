package mvc.creators;

import mvc.model.objects.PrivateObjectiveCard;
import mvc.model.objects.PublicObjectiveCard;
import mvc.model.objects.private_objectives_cards.*;
import mvc.model.objects.public_objectives_cards.*;

import java.util.ArrayList;
import java.util.List;

public class ObjectivesCreator {
    //Creatore carte obiettivo del gioco

    public static List<PrivateObjectiveCard> createPrivateObjectiveCards() {
        List<PrivateObjectiveCard> cards = new ArrayList<PrivateObjectiveCard>();

        //Crea ogni carta obiettivo privata
        cards.add(new ShadesOfBlue());
        cards.add(new ShadesOfGreen());
        cards.add(new ShadesOfPurple());
        cards.add(new ShadesOfRed());
        cards.add(new ShadesOfYellow());

        return cards;
    }
    public static List<PublicObjectiveCard> createPublicObjectiveCards() {
        List<PublicObjectiveCard> cards = new ArrayList<PublicObjectiveCard>();

        //Crea ogni carta obiettivo publica
        cards.add(new ColorDiagonals());
        cards.add(new ColorVariety());
        cards.add(new ColumnColorVariety());
        cards.add(new DeepShades());
        cards.add(new LightShades());
        cards.add(new MediumShades());
        cards.add(new RowColorVariety());
        cards.add(new RowShadeVariety());
        cards.add(new ShadeVariety());

        return cards;
    }
}
