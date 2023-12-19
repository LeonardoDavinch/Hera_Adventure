package org.example;

import java.io.*;

public class Config {

    GamePanel gp;

    public  Config (GamePanel gp){
        this.gp = gp;

    }
    public  void  saveConfig(){

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));

            //Full screan
            if(gp.fullScreanOn == true){
                bw.write("On");
            }
            if(gp.fullScreanOn == false){
                bw.write("Off");
            }
            bw.newLine();

            //Musick volume
            bw.write(String.valueOf(gp.music.volumScale));
            bw.newLine();

            //se volume
            bw.write(String.valueOf(gp.se.volumScale));
            bw.newLine();

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public  void  loadConfig(){

        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));

            String s = br.readLine();

            //Full screan
            if(s.equals("On")){
                gp.fullScreanOn = true;
            }
            if(s.equals("Off")){
                gp.fullScreanOn = false;
            }

            //music volume
            s = br.readLine();
            gp.music.volumScale = Integer.parseInt(s);

            //se volume
            s = br.readLine();
            gp.se.volumScale = Integer.parseInt(s);

            br.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
