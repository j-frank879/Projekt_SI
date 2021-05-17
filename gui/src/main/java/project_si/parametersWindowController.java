package project_si;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class parametersWindowController implements Initializable  {
    public Data data;
    @FXML
    public RadioButton nkb,gray;
    @FXML
    public RadioButton cross1,cross2,cross3;
    @FXML
    public TextField minX,maxX,minY,maxY,pop_size,num_gen,p_cross,p_m,function;
    @FXML
    public Button closeButton;

    public void setData(Data data){
        this.data = data;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
public void init()
{
   /* if(data.code==0)
{
    nkb.setSelected(true);
}
else if(data.code==1)
{
    gray.setSelected(true);
}

    if((data.crossover>0)&&(data.kindOfCrossing==1))
    {
        cross1.setSelected(true);
    }
    if((data.crossover>0)&&(data.kindOfCrossing==2))
    {
        cross2.setSelected(true);
    }
    if((data.crossover>0)&&(data.kindOfCrossing==3))
    {
        cross3.setSelected(true);
    }
    p_m.setText(String.valueOf(data.mutation));
    p_cross.setText(String.valueOf(data.crossover));
    function.setText(data.function);
    minX.setText(String.valueOf(data.minX));
    maxX.setText(String.valueOf(data.maxX));
    minY.setText(String.valueOf(data.minY));
    maxY.setText(String.valueOf(data.maxY));
    pop_size.setText(String.valueOf(data.size_population));
    num_gen.setText(String.valueOf(data.generation));

*/
}

    public void handleCloseButtonAction(ActionEvent event) {


        int x=0; //0-data prawidlowe 1-niepoprawne data
       /* if(nkb.isSelected())
        {data.code=0;
        }
        else if(gray.isSelected())
        {data.code=1;

        }
        else if(data.code==-1)
        {
            JOptionPane.showMessageDialog(null, "Zaznacz jedną wartość(NKB lub Gray).");x=1;}

        if(!(cross1.isSelected())&&(!(cross2.isSelected()))&&(!(cross3.isSelected())))
        {data.crossover=0;
        }

        else if(data.crossover==-1)
        {JOptionPane.showMessageDialog(null, "Zaznacz jedną wartość.");x=1;}

        double c=Double.parseDouble(p_m.getText());
            if ((c<0)||(c>1))
            {JOptionPane.showMessageDialog(null, "Prawdopodobienstwo mutacji musi byc od 0.0 do 1.0.");x=1;
            }
            else
                data.mutation=c;

        if((cross1.isSelected())||(cross2.isSelected())||(cross3.isSelected()))
        { c=Double.parseDouble(p_cross.getText());
            if ((c<0)||(c>1))
            {JOptionPane.showMessageDialog(null, "Prawdopodobienstwo krzyzowania musi byc od 0.0 do 1.0.");x=1;
            }
            else
                data.crossover=c;
        }
        if((nkb.isSelected()))
        {data.code=0;
        }
        if((gray.isSelected()))
        {data.code=1;
        }
        if(function.getText().equals(""))
        {x=1;
            JOptionPane.showMessageDialog(null, "Function.");
        }
        else {data.function=function.getText();}


        if((Integer.parseInt(minX.getText()))>(Integer.parseInt(maxX.getText()))||
                (Integer.parseInt(minY.getText()))>(Integer.parseInt(minY.getText())))
        {x=1;
            JOptionPane.showMessageDialog(null, "Wartosc min nie moga byc wieksze od max");
        }
        else{
            data.minX=Integer.parseInt(minX.getText());
            data.maxX=Integer.parseInt(maxX.getText());
            data.minY=Integer.parseInt(minY.getText());
            data.maxY=Integer.parseInt(maxY.getText());}

        data.size_population=Integer.parseInt(pop_size.getText());
        data.generation=Integer.parseInt(num_gen.getText());*/
        if(x==0)
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}




