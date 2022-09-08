//--- REFERENCIA BIBLIOGRÁFICA: --- https://www.youtube.com/watch?v=GmL1Nmj9TqY --- https://www.youtube.com/watch?v=kSmwtbRgoDs ---
//https://www.youtube.com/watch?v=GmL1Nmj9TqY
//https://www.youtube.com/watch?v=kSmwtbRgoDs

//------------------ StringBuilder ---Y-- json -------------------------------

// Alternativa al servicio web API "Yahoo Weather"

/* Librer-ias utilizadas:
 * json-20220320.jar
 * 
 * 
 * */

package com.tutorialstringbuilder;

//import java.net.http;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Scanner;

import org.json.JSONObject;
import org.json.JSONArray;
//import net.minidev.json.writer.JsonReader;

//import java.lang.reflect.Array;

public class ConsumoAPI {

	public static void main(String[] args) {
		System.out.println("hola mundo\n");

		try {			
			// Solicitar una petici-on
			// https://api.open-meteo.com/v1/forecast?latitude=-0.22985&longitude=-78.52495&hourly=temperature_2m,relativehumidity_2m,windspeed_10m&timezone=America%2FGuayaquil
			URL url = new URL( "https://api.open-meteo.com/v1/forecast?latitude=-0.22985&longitude=-78.52495&hourly=temperature_2m,relativehumidity_2m,windspeed_10m&timezone=America%2FGuayaquil" );
			
			HttpURLConnection coneccion = (HttpURLConnection) url.openConnection();
			coneccion.setRequestMethod( "GET" );
			coneccion.connect();
			
			// Probar si la petici-on es correcta (¡Petici-on correcta!)
			int responseCode = coneccion.getResponseCode();
			System.out.println("\nresponseCode: "+ responseCode );
			if ( responseCode != 200 )
			{
				throw new RuntimeException( "Ocurri-o un error: "+responseCode );
			}else {
				//Abrir un scanner que lea el flujo de datos
				StringBuilder informationString = new StringBuilder();
				Scanner scanner = new Scanner( url.openStream() );
				while( scanner.hasNext() ) {
					informationString.append( scanner.nextLine() );
					
				}
				scanner.close();
				//Pintar la informaci-on contenida por consola
				System.out.println("\nToda la informaci-on JSON: \n"+informationString);
				
				//JSONObject jsonObject = new JSONObject(informationString.toString());
				//JSONArray jsonArray = jsonObject.getJSONArray("temperature_2m");
				//System.out.println("\nHora: "+jsonArray);
				
				
				//JSONArray jsonArray = new JSONArray(informationString.toString());
				//JSONObject jsonObject = jsonArray.getJSONObject(2);				
				//System.out.println("\nHora: "+jsonObject);
				// =====Las variables de tipo objeto siempre se inicializan con "new"
				// =====de lo contrario, aunque el compilador no de error, el contenido
				// =====de dichas variables se ver-an afectadas de alguna manera.
				// =====Entonces, usar "new" cuando se empiece usando String, JSONObject, y JSONArray
				
				JSONObject jsonObject = new JSONObject(informationString.substring(0));// .substring(0)				
				JSONObject jsonObject_h= new JSONObject( jsonObject.getJSONObject("hourly").toString() );
				System.out.println("\nCadaHora: "+jsonObject_h);
				//OBSERVACI-ON: Es probable que la implemnetaci-on est-e mal.
				//Tambi-en existe la posibilidad de que la librer-ia JSON-Java tenga errores
				//puesto que al imprimir muestra en desorden los objetos, primero "temperature_2m"
				//y luego "time", adem-as se pierden dos objetos, tan solo aparecen dos.
				//Editado: Usar "new" en nuevos Objetos incluso en el tipo String, se recomienda copiar las variables al reutilizarlas.
				
				//--------
				JSONArray jsonArray_ti= new JSONArray( jsonObject_h.getJSONArray("time") );
				System.out.println("\ntime: "+jsonArray_ti);
				
				//String jsonObject_tiN= (String)jsonArray_ti.getJSONObject(10).toString();
				//System.out.println("\n\t time[1]: "+jsonObject_tiN);
				
				//String jsonObject_tiN= (String)jsonArray_ti.getJSONArray(10).toString();
				//System.out.println("\n\t time[1]: "+jsonObject_tiN);
				
				int N=2;
				String jsonObject_tiN = new String();
				jsonObject_tiN = jsonArray_ti.getString(N);// Recordar que los elementos del array son de tipo String.
				System.out.println("\t time[N]: "+jsonObject_tiN);				
				//System.out.println("\n\t time[1]: "+jsonArray_ti.getString(2));				
				
				//---------- Temperature_2m-------------
				JSONArray jsonArray_te = new JSONArray( jsonObject_h.getJSONArray("temperature_2m") );//Copiar las variables para no tener problemas
				System.out.println("\ntemperature_2m: "+jsonArray_te);
				// Recordar que los elementos del array son de tipo Double.
				Double jsonObject_teN = new Double( jsonArray_te.getDouble(N) );//Solo para el caso de tipo Double usar "new" es "deprecated" 
				System.out.println("\t temperature_2m[N]: "+jsonObject_teN);
				
				//---------- Relativehumidity_2m ---------
				JSONArray jsonArray_rh = new JSONArray( jsonObject_h.getJSONArray("relativehumidity_2m") );//Copiar las variables para no tener problemas
				// En la notaci-on original aparecen los elementos con parte decimal ".0", hay que investigar xq desaparecen.
				System.out.println("\nrelativehumidity_2m: "+jsonArray_rh);
				// Recordar que los elementos del array son de tipo Double.
				Double jsonObject_rhN = new Double( jsonArray_rh.getDouble(N) );//Solo para el caso de tipo Double usar "new" es "deprecated" 
				System.out.println("\t relativehumidity_2m[N]: "+jsonObject_rhN);
					
				
				// --------Windspeed_10m-----------
				// //Copiar las variables para no tener problemas, es "windspeed_10m"
				JSONArray jsonArray_ws = new JSONArray( jsonObject_h.getJSONArray("windspeed_10m") );//Copiar las variables para no tener problemas
				System.out.println("\nwindspeed_10m: "+jsonArray_ws);
				
				//String jsonObject_tiN= (String)jsonArray_ti.getJSONObject(10).toString();
				//System.out.println("\n\t time[1]: "+jsonObject_tiN);
				
				//String jsonObject_tiN= (String)jsonArray_ti.getJSONArray(10).toString();
				//System.out.println("\n\t time[1]: "+jsonObject_tiN);
				
				//int N=2; // poner atenci-on para no duplicar variables locales
				//Double jsonObject_wsN = new Double(123.45);//Siempre debe tener un argumento, pero es "deprecated"
				Double jsonObject_wsN = new Double( jsonArray_ws.getDouble(N) );//Solo para el caso de tipo Double usar "new" es "deprecated" 
				System.out.println("\t windspeed_10m[N]: "+jsonObject_wsN);				
				//System.out.println("\n\t time[1]: "+jsonArray_ti.getString(2));
				
				
				//----------				
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}	
		
		

		

	}

}
