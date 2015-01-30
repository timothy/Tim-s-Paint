package fundraw.com.timspaint;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by tbrad_000 on 1/28/2015.
 */
public class MyButtons {


    public MyButtons(){

    }

    //color button
        public void color_button(View v, final MyView myView){

            final Dialog color_Dialog = new Dialog(v.getContext());
            color_Dialog.setTitle("Choose a Color");//set the title
            color_Dialog.setContentView(R.layout.color_chooser);//set the layout


            ImageButton Color1 = (ImageButton)color_Dialog.findViewById(R.id.one);

            Color1.setOnClickListener(new View.OnClickListener() {//This is the color Button
                @Override
                public void onClick(View v) {

                    myView.color_change(0xFF660000);
                    color_Dialog.dismiss();
                }
            });

            ImageButton Color2 = (ImageButton)color_Dialog.findViewById(R.id.two);

            Color2.setOnClickListener(new View.OnClickListener() {//This is the color Button
                @Override
                public void onClick(View v) {

                    myView.color_change(0xFFFF0000);
                    color_Dialog.dismiss();
                }
            });

            ImageButton Color3 = (ImageButton)color_Dialog.findViewById(R.id.three);

            Color3.setOnClickListener(new View.OnClickListener() {//This is the color Button
                @Override
                public void onClick(View v) {

                    myView.color_change(0xFFFF6600);
                    color_Dialog.dismiss();
                }
            });

            ImageButton Color4 = (ImageButton)color_Dialog.findViewById(R.id.four);//line two

            Color4.setOnClickListener(new View.OnClickListener() {//This is the color Button
                @Override
                public void onClick(View v) {

                    myView.color_change(0xFF0000FF);
                    color_Dialog.dismiss();
                }
            });

            ImageButton Color5 = (ImageButton)color_Dialog.findViewById(R.id.five);

            Color5.setOnClickListener(new View.OnClickListener() {//This is the color Button
                @Override
                public void onClick(View v) {

                    myView.color_change(0xFF990099);
                    color_Dialog.dismiss();
                }
            });

            ImageButton Color6 = (ImageButton)color_Dialog.findViewById(R.id.six);

            Color6.setOnClickListener(new View.OnClickListener() {//This is the color Button
                @Override
                public void onClick(View v) {

                    myView.color_change(0xFFFF6666);
                    color_Dialog.dismiss();
                }
            });

            ImageButton Color7 = (ImageButton)color_Dialog.findViewById(R.id.seven);

            Color7.setOnClickListener(new View.OnClickListener() {//This is the color Button
                @Override
                public void onClick(View v) {

                    myView.color_change(0xFF787878);
                    color_Dialog.dismiss();
                }
            });
            ImageButton Color8 = (ImageButton)color_Dialog.findViewById(R.id.eight);

            Color8.setOnClickListener(new View.OnClickListener() {//This is the color Button
                @Override
                public void onClick(View v) {

                    myView.color_change(0xFF000000);
                    color_Dialog.dismiss();
                }
            });

            ImageButton Color9 = (ImageButton)color_Dialog.findViewById(R.id.nine);

            Color9.setOnClickListener(new View.OnClickListener() {//This is the color Button
                @Override
                public void onClick(View v) {

                    myView.color_change(0xFF009900);
                    color_Dialog.dismiss();
                }
            });

            ImageButton Color10 = (ImageButton)color_Dialog.findViewById(R.id.ten);

            Color10.setOnClickListener(new View.OnClickListener() {//This is the color Button
                @Override
                public void onClick(View v) {

                    myView.color_change(0xFF009999);
                    color_Dialog.dismiss();
                }
            });

            ImageButton Color11 = (ImageButton)color_Dialog.findViewById(R.id.eleven);

            Color11.setOnClickListener(new View.OnClickListener() {//This is the color Button
                @Override
                public void onClick(View v) {

                    myView.color_change(0xFFFFFFFF);
                    color_Dialog.dismiss();
                }
            });

            ImageButton Color12 = (ImageButton)color_Dialog.findViewById(R.id.twelve);

            Color12.setOnClickListener(new View.OnClickListener() {//This is the color Button
                @Override
                public void onClick(View v) {

                    myView.color_change(0xFFFFCC00);
                    color_Dialog.dismiss();
                }
            });

            color_Dialog.show();// this will show my color dialog
        }



    public void sizeChanger(View v, final MyView myView){

        final Dialog penSize_Dialog = new Dialog(v.getContext());
        penSize_Dialog.setTitle("Choose Pen size:");//set the title
        penSize_Dialog.setContentView(R.layout.size_change);//set the layout

        ImageButton fin_tip = (ImageButton)penSize_Dialog.findViewById(R.id.fine_tip);

        fin_tip.setOnClickListener(new View.OnClickListener() {//listen for clicks on the three size buttons, starting with the small one:
            @Override
            public void onClick(View v) {

                myView.size_change(1);
                penSize_Dialog.dismiss();
            }
        });

        ImageButton norm_tip = (ImageButton)penSize_Dialog.findViewById(R.id.norm_tip);

        norm_tip.setOnClickListener(new View.OnClickListener() {//listen for clicks on the three size buttons, starting with the small one:
            @Override
            public void onClick(View v) {

                myView.size_change(5);
                penSize_Dialog.dismiss();
            }
        });

        ImageButton big_tip = (ImageButton)penSize_Dialog.findViewById(R.id.big);

        big_tip.setOnClickListener(new View.OnClickListener() {//listen for clicks on the three size buttons, starting with the small one:
            @Override
            public void onClick(View v) {

                myView.size_change(10);
                penSize_Dialog.dismiss();
            }
        });

        Button custom_tip = (Button)penSize_Dialog.findViewById(R.id.custom_button);

        custom_tip.setOnClickListener(new View.OnClickListener() {//listen for clicks on the three size buttons, starting with the small one:
            @Override
            public void onClick(View v) {

                final Dialog customSize_Dialog = new Dialog(v.getContext());
                customSize_Dialog.setTitle("Choose Pen size:");//set the title
                customSize_Dialog.setContentView(R.layout.custom_size);//set the layout

                final EditText et = (EditText) customSize_Dialog.findViewById(R.id.editText);

                Button change_tip = (Button)customSize_Dialog.findViewById(R.id.change);
                change_tip.setOnClickListener(new View.OnClickListener() {//listen for clicks on the three size buttons, starting with the small one:
                    @Override
                    public void onClick(View v) {
                        int size = Integer.parseInt(et.getText().toString());
                        myView.size_change(size);
                        customSize_Dialog.dismiss();
                    }
                });
                penSize_Dialog.dismiss();
                customSize_Dialog.show();
            }
        });
        penSize_Dialog.show();
    }

    public void redo_btn(View v , MyView myView) //Undo button
    {
            myView.redo();
    }

    public void undo_btn(View v , MyView myView) //Undo button
    {
            try {
                myView.undo();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    public void clear_btn(View v,final MyView myView){

        AlertDialog.Builder Clear_Alert = new AlertDialog.Builder(v.getContext());//this is create a pop up message
        Clear_Alert.setTitle("Erase Everything?");// this is the title of the message
        Clear_Alert.setMessage("Are you sure you want to erase notes and clear the screen?");//This is the body of the message

        Clear_Alert.setPositiveButton("Yes", new DialogInterface.OnClickListener(){// this is the button that is created. It is called Yes
            public void onClick(DialogInterface dialog, int which){
                myView.clear();
            }
        });
        Clear_Alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){// this is the button that is created. It is called Cancel
            public void onClick(DialogInterface dialog, int which){

                dialog.cancel();//this will exit the pop up message
            }
        });
        Clear_Alert.show();
    }

    public void save_btn(final View v,final MyView myView){

        AlertDialog.Builder Save_Alert = new AlertDialog.Builder(v.getContext());
        Save_Alert.setTitle("Note Saving");
        Save_Alert.setMessage("Are you sure you want to save notes to device Gallery?");

        Save_Alert.setPositiveButton("Yes!!", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){

                myView.setDrawingCacheEnabled(true);// make a non-scaled bitmap representing this view or null if cache is disabled.

                String check = myView.file_save(v.getContext());//this accesses the file save method in my view

                myView.destroyDrawingCache();//Destroy the drawing cache so that any future drawings saved won't use the existing cache

                if(check != null){// this will check to see if the notes were really saved or not
                    Toast it_works = Toast.makeText(myView.getContext(),
                            "Notes saved to Gallery!", Toast.LENGTH_LONG);
                    it_works.show();
                }
                else if(check == null){
                    Toast not_working = Toast.makeText(myView.getContext(),
                            "!!!WARNING DID NOT SAVE NOTES!!!", Toast.LENGTH_LONG);
                    not_working.show();
                }

            }
        });
        Save_Alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){

                dialog.cancel();
            }
        });
        Save_Alert.show();

    }


}
