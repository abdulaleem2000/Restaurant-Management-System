package recipe;

import database.DbHandler;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class Recipe {
    public boolean chkRecipe(TextField id) throws IOException, SQLException {
        DbHandler db = new DbHandler();
        String rId = id.getText();

        if( db.checkRecId(rId))
        {
            return true;
        }
        return false;
    }

    public void addRecp(TextField name,TextField id, TextField price) throws IOException, SQLException {
        DbHandler db = new DbHandler();
        String rId = id.getText();
        String rName = name.getText();
        String rPrice =  price.getText();
        db.insertRecipe(rName,rId,rPrice);

    }

    public void removeRecp(TextField id) throws IOException, SQLException {
        DbHandler db = new DbHandler();
        String rId = id.getText();

        db.deleteRecipe(rId);

    }
}
