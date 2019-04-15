package metier;

import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class ConvertisseurSOAP  {

    private final String URLSERVICE="http://192.168.177.1:8080/WebServiceConvertisseur/WebServiceConvertisseur?WSDL";

    public ArrayList <String> getConversionTable (){

        ArrayList <String> result = new ArrayList<>() ;

        try{
            Log.d("testsoap","Init début");
            SoapObject request = new SoapObject ( "http://service/", "getConversionTable");
            Log.d("testsoap","creation du SOAPObject");
            SoapSerializationEnvelope enveloppe =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
            enveloppe.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE (URLSERVICE);
            Log.d("testsoap","creation de l'enveloppe ok");

            androidHttpTransport.call("getConversionTable", enveloppe);


            Log.d("testsoap","Transport ok");

            SoapObject objetSOAP = (SoapObject)enveloppe.bodyIn;
            for (int i=0; i < objetSOAP.getPropertyCount(); i++)
            {
                result.add(objetSOAP.getProperty(i).toString());
                Log.d("testsoap",objetSOAP.getProperty(i).toString());
            }

        } catch (IOException e) {
            Log.d("testsoap",e.toString());
        } catch (XmlPullParserException e) {
            Log.d("testsoap",e.toString());
        }
        catch (Exception e) {
            Log.d("testsoap",e.toString());
        }

        Log.d("testsoap","result= "+result.toString());
        return result;
    }

    public String Convertir(String source,String cible, String montant){
        String result="";

        try{
            Log.d("testsoap","Init début");
            SoapObject request = new SoapObject ( "http://service/", "convertir");
            //passage des paramètres
            request.addProperty("source",source);
            request.addProperty("cible",cible);
            request.addProperty("montant",montant);

            Log.d("testsoap","creation du SOAPObject");
            SoapSerializationEnvelope enveloppe =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
            enveloppe.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE (URLSERVICE);
            Log.d("testsoap","creation de l'enveloppe ok");

            androidHttpTransport.call("convertir", enveloppe);


            Log.d("testsoap","Transport ok");

            SoapObject objetSOAP = (SoapObject)enveloppe.bodyIn;

            objetSOAP.getProperty(0).toString();
            Log.d("testsoap","le resultat de la conversion est: "+objetSOAP.getProperty(0).toString());


        } catch (IOException e) {
            Log.d("testsoap",e.toString());
        } catch (XmlPullParserException e) {
            Log.d("testsoap",e.toString());
        }
        catch (Exception e) {
            Log.d("testsoap",e.toString());
        }

        Log.d("testsoap","result= "+result.toString());

        return result;

    }



}
