package mvc.view.gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mvc.model.objects.Window;

public class WindowView extends ObjectView{
    //Veduta finestra

    private Window window;
    private CellView[][] cells;

    //costruttori
    public WindowView(ImageView imageView, Window window, CellView[][] cells) {
        super(imageView);
        this.window = window;
        this.cells = cells;
    }

    //Setter/Getter
    public Window getWindow() {
        return window;
    }
    public CellView[][] getCells() {
        return cells;
    }

    public void setWindow(Window window) {
        this.window = window;
    }
    public void setCells(CellView[][] cells) {
        this.cells = cells;
    }
    public Image imagePath() {
        String path = "";
        switch (this.window.getName()){
            case "bellesguard":
                path = "bellesguard.PNG";
                break;
            case "fractal drops":
                path = "fractaldrops.PNG";
                break;
            case "luz celestial":
                path = "luzcelestial.PNG";
                break;
            case "sun catcher":
                path = "suncatcher.PNG";
                break;
            case "aurora sagradis":
                path = "auroraesagradis.PNG";
                break;
            case "chromatic splendor":
                path = "chromaticsplendor.PNG";
                break;
            case "kaleidoscopic dream":
                path = "kaleidoscopicdream.PNG";
                break;
            case "via lux":
                path = "vialux.PNG";
                break;
            case "aurorae magnificus":
                path = "auroraemagnificus.PNG";
                break;
            case "battlo":
                path = "battlo.PNG";
                break;
            case "comitas":
                path = "comitas.PNG";
                break;
            case "firelight":
                path = "firelight.PNG";
                break;
            case "firmitas":
                path = "firmitas.PNG";
                break;
            case "fulgor del cielo":
                path = "fulgordelcielo.PNG";
                break;
            case "gravitas":
                path = "gravitas.PNG";
                break;
            case  "industria":
                path = "industria.PNG";
                break;
            case "lux astram":
                path = "luxastram.PNG";
                break;
            case "ripple of light":
                path = "ripplesoflight.PNG";
                break;
            case "shadow thief":
                path = "shadowthief.PNG";
                break;
            case "virtus":
                path = "virtus.PNG";
                break;
            case "symphony of light":
                path = "symphonyoflight.PNG";
                break;
            case "water of lgiht":
                path = "wateroflife.PNG";
                break;
            case "lux mundi":
                path = "luxmundi.PNG";
                break;
            case "suns glory":
                path = "sunsglory.PNG";
                break;
        }
        return new Image(getClass().getResourceAsStream(path));
    }
}
