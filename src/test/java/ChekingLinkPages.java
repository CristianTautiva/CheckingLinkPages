import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ChekingLinkPages {

    private WebDriver driver;

    public ChekingLinkPages(WebDriver driver){
        this.driver = driver;
    }

     public boolean chekingPageLinks(){
        List<WebElement> links =  driver.findElements(By.tagName("a"));
        List<String> brokenLinks = new ArrayList<String>();
        List<String> okLinks = new ArrayList<String>();
        String  url ="";
        HttpURLConnection httpConnection = null;
        int responseCode = 200;
        Iterator <WebElement> it = links.iterator();

        while(it.hasNext()){
           url = it.next().getAttribute("href");
            if(url == null || url.isEmpty()){
                System.out.println(url+"url no esta definida o es invalida");
                continue;
            }

            try{
                httpConnection = (HttpURLConnection) (new URL(url).openConnection());
                httpConnection.setRequestMethod("HEAD");
                httpConnection.connect();
                responseCode = httpConnection.getResponseCode();

                if(responseCode > 400){
                    System.out.println("Codigo HTTP: "+responseCode);
                    System.out.println("ERROR LINK ROTO: "+url);
                    brokenLinks.add(url);

                }else {
                    System.out.println("Codigo HTTP: "+responseCode);

                    System.out.println("LINK VALIDO: "+url);
                    okLinks.add(url);
                }
            }catch(Exception e) {
                System.out.println("Entre a reportar el error!");
                e.printStackTrace();
            }
            }

        System.out.println("Link Validos: "+okLinks.size());
        System.out.println("Link Rotos: "+brokenLinks.size());

        if(brokenLinks.size()>0){
            for (int i=0; i< brokenLinks.size(); i++){
                System.out.println("Link N°"+i+" : "+brokenLinks.get(i));
            }
            return false;
        }else{

            return true;
        }

        }


    }


