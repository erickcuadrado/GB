import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;











import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;











import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.Random;

import javax.swing.*;
import javax.imageio.ImageIO;




















import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONML;
import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.google.gson.annotations.JsonAdapter;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;




public class uBar extends JPanel {
	
	 private JTextField[] fields;
	 private static JComboBox comboBox;

	  // Create a form with the specified labels, tooltips, and sizes.
	  public uBar(String[] labels, char[] mnemonics, int[] widths, String[] tips) {
	    super(new BorderLayout());
	    JPanel labelPanel = new JPanel(new GridLayout(labels.length, 1));
	    JPanel fieldPanel = new JPanel(new GridLayout(labels.length, 1));
	    add(labelPanel, BorderLayout.WEST);
	    add(fieldPanel, BorderLayout.CENTER);
	    fields = new JTextField[labels.length];

	    for (int i = 0; i < labels.length; i += 1) {
	      fields[i] = new JTextField();
	      if (i < tips.length)
	        fields[i].setToolTipText(tips[i]);
	      if (i < widths.length)
	        fields[i].setColumns(widths[i]);

	      JLabel lab = new JLabel(labels[i], JLabel.RIGHT);
	      lab.setLabelFor(fields[i]);
	      if (i < mnemonics.length)
	        lab.setDisplayedMnemonic(mnemonics[i]);

	      labelPanel.add(lab);
	      JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
	      p.add(fields[i]);
	      fieldPanel.add(p);
	    }
	  }

	  public uBar() {
		// TODO Auto-generated constructor stub
	}

	public String getText(int i) {
	    return (fields[i].getText());
	  }
	
	
	public static void main (String [] args) throws Exception{
		
		JFrame jfM = new JFrame("JPanel En Java");
		jfM.setSize(800, 600);
		/*JTextField entrada = new JTextField("dir");
		entrada.setBounds(750, 300, 100, 30);
		entrada.setBorder(null);
		jfM.add(entrada);*/
		//jfM.show();
		
		Random rng = new  Random (); //Random para hacer cosas random!!
		
		//La dirección es fija por ahora
		String dir = "San Pablo 180 Azcapotzalco Distrito Federal";
		GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyCHAxROe3GXVIhuRtJETSBQzpf71KmXpaI");
		GeocodingResult[] results =  GeocodingApi.geocode(context,dir).await();

		System.out.println(results[0].formattedAddress);
		
		//En results es donde se guarda el json de la dirección
		//de ahí mismo se obtiene la latitud y longitud con las funciones de abajo

		double lat_act = results[0].geometry.location.lat;
		double long_act = results[0].geometry.location.lng;
		
		//Limites para randomizar antros
		double limite_sup_lat = lat_act + 0.09;
		double limite_sup_long = long_act + 0.09;
		double limite_inf_lat = lat_act - 0.09;
		double limite_inf_long = long_act - 0.09;
		
		//Pasamos a string la longitud y latitud de la dirección inicial para usarla en...
		String lat_use = String.valueOf(lat_act);
		String long_use = String.valueOf(long_act);
		
		System.out.println("Coordenadas iniciales:");
		System.out.println(lat_act);
		System.out.println(long_act);
		
		//... este URL, para el mapa estatico
		String epicURL = "https://maps.googleapis.com/maps/api/staticmap?center="
				+ lat_use
				+ ","
				+ long_use
				+ "&size=800x600&zoom=11"
				+ "&markers=color%3ablue%7CLABEL:F%7C"
				+ lat_use
				+ ","
				+ long_use
				/*+ "&format=png&key=AIzaSyCHAxROe3GXVIhuRtJETSBQzpf71KmXpaI"*/;
		//coordenas de la dirección inicial + "19.5017614197085,-99.1859707802915"		
		
		
		
		//CARACTERÍSTICAS GENERALES
		String [] carac = new String[10];
		carac[0] = "No cover";
		carac[1] = "2x1";
		carac[2] = "Barra libre";
		carac[3] = "Botella gratis";
		carac[4] = "Zona para fumar";
		carac[5] = "Botana gratis";
		carac[6] = "Dedicatorias";
		carac[7] = "Shots gratis";
		carac[8] = "Happy hour";
		carac[9] = "Ladies night";
		
		//arreglos de longitud, latitud y caracteristicas de los 10 antros
		double [] lat_ant = new double [10];
		double [] long_ant = new double [10];
		
		String[] carac_ant = new String[10];
		for (int i=0;i<10;i++)
			carac_ant[i] = "";
		
		//Se crea primero las coordenadas en el rango sup e inferior para long y lat
		//luego se usa un rng para agregar características
		//Todavia no está arreglado para que no se repitan características
		int gnr = 0;
		int z[]= new int[3];
		z[0]= 10;
		z[1]= 11;
		z[2]=12;
		String aux[][]=new String[10][3];
		
		for (int i=0;i<10;i++)
			{
				lat_ant[i] = limite_inf_lat + (limite_sup_lat - limite_inf_lat) * rng.nextDouble();
				long_ant[i] = limite_inf_long + (limite_sup_long - limite_inf_long) * rng.nextDouble();
				for (int j=0;j<3;j++)
				{
					do{
						gnr = rng.nextInt(10);
						z[j]=gnr;
					}while(z[0]==z[1]||z[0]==z[2]||z[1]==z[2]);
					carac_ant[i] += carac[gnr];
					aux[i][j]=carac[gnr];
					if (j!=2)
					carac_ant[i] += ",";
				}
			}
		
		String []ubi= new String[10];
		//Imprime todos los datos de los antros uno por uno
		for (int i=0; i<10; i++){
			int counter=i+1;
			String latlong=null;
			System.out.println("Antro: "+ counter+ " " );
			System.out.println(carac_ant[i]);
			System.out.println(lat_ant[i]);
			System.out.println(long_ant[i]);
			System.out.println("");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(lat_ant[i]);
	        stringBuilder.append(long_ant[i]);
			//latlong=stringBuilder;
	        LatLng latlongi=new LatLng(lat_ant[i], long_ant[i]);
			GeoApiContext context2 = new GeoApiContext().setApiKey("AIzaSyCHAxROe3GXVIhuRtJETSBQzpf71KmXpaI");
			GeocodingResult[] result  =GeocodingApi.reverseGeocode(context2, latlongi).await();
			ubi[i]=result[0].formattedAddress;
			System.out.println(result[0].formattedAddress);
			
		}
		//Pasamos las long y lat de los antros a cadenas para agregarse como marcadores al mapa
		String [] st_lat_ant = new String[10];
		String [] st_long_ant = new String [10];
		
		for (int i=0; i<10;i++){
			st_lat_ant[i] = String.valueOf(lat_ant[i]);
			st_long_ant[i] = String.valueOf(long_ant[i]);
		}
		/*System.out.println("");
		System.out.println("");
		for (int i=0; i<10; i++){

			System.out.println(lat_ant[i]);
			System.out.println(long_ant[i]);
		}*/
		
		//Se añaden las coordenadas de los antros a la URL para el mapa estático
		//cada uno tendra un marcador de color rojo
		for (int i=0; i<10; i++){
		epicURL += "&markers=color=red%7CLABEL:F%7C"
				+ st_lat_ant[i]
				+ ","
				+ st_long_ant[i];
		}

		System.out.println(epicURL);
		URL eURL = new URL(epicURL);
		BufferedImage temp = ImageIO.read(eURL); 
		
		//se agrega el mapa al JFrame para su muestra
		JLabel mapa = new JLabel("Mapa");
		mapa.setIcon(new ImageIcon(temp));
		
		jfM.add(mapa);

		jfM.show();
		
		


		String[] labels = { "Ubicación", "Tiempo (min)" };
	    char[] mnemonics = { 'U', 'M' };
	    int[] widths = { 15, 15  };
	    String[] descs = { "First Name", "Tiempo (min)" };	    

	    String[] options = {"Ninguna", "No cover","2x1","Barra libre","Botella gratis","Zona para fumar","Botana gratis", "Dedicatorias", "Shots gratis","Happy hour", "Ladies night"};

	    final uBar form = new uBar(labels, mnemonics, widths, descs);

	    JButton submit = new JButton("Submit Form");
	    comboBox=new JComboBox(options);
	    JLabel labelcarac= new JLabel("Caracteristicas");
	    
	    ///Aqui se mete el codigo en el botón
	    submit.addActionListener(new ActionListener() {
	    	
	      public void actionPerformed(ActionEvent e) {
	    	  int i, j, k;
	    	  String caractselec=null;
	        System.out.println(form.getText(0) + " " + form.getText(1) + ". " + comboBox.getSelectedItem());
	        caractselec=(String) comboBox.getSelectedItem();
	        for (i=0; i<10; i++){
	        	for (j=0; j<3; j++){
	        		if(aux[i][j]==caractselec)
	        		{
	        			k=i+1;
	        			System.out.println("El antro "+ k+ " tiene la caracteritica"+ caractselec);
	        			//System.out.println(aux[i][j]);
	        		}
	        	}
	        	
	        }
	        String espacio="%7C";
	       // String nueva[]="";
	        OkHttpClient client = new OkHttpClient();
	       String API_KEY= "AIzaSyDe1lmZlUUUNwla67fUmAVSOvFpfE2lb8U";
	       uBar request = new uBar();
	       String origen=dir;
	      /* for (i=0; i<9; i++)
	       {
	    	   for(j=0; j<(ubi[i].length()); j++)
	    	   {
	    		   //if(ubi[i].substring(j, j+1)== " ")
	    			   //nueva[i]=
	    	   }
	       }*/
	       for (i=0; i<9; i++){
	    	   
	       String url_request = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=Vancouver+BC%7CSeattle&destinations=San+Francisco%7CVictoria+BC&mode=bicycling&language=fr-FR&key=" + API_KEY;
	       url_request = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="+lat_use+","+long_use+"&destinations="+lat_ant[i]+","+long_ant[i]+"&key=" + API_KEY;	       Request request2 = new Request.Builder()
	        .url(url_request)
	        .build();

	       Response response = null;
		try {
			response = client.newCall(request2).execute();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	       try {
	    	   //una vez teniendo la respuesta de request tendremos un array de JSON
	    	   JSONObject nodeRoot  = new JSONObject(response.body().string());
	    	   System.out.println(nodeRoot);
	    	   //La variable nodeStats genera un Arreglo JSON con elementos
	    	   JSONArray nodeStats = nodeRoot.getJSONArray("rows");
	    	   System.out.println(i+" "+nodeStats);
	    	   String auxn =nodeStats.toString();
	    	   //Con la linea de aabajo quitamos los corchetes para que pueda leer bien 
	    	   auxn=auxn.substring(1, auxn.length()-1);
	    	   System.out.println(i+" "+auxn);
	    	   //System.out.println(nodeStats.getClass().getName());
	    	   
	    	  //Generamos un JSON object con una nueva variables y despues creamos un nuevo array con menos elementos 
	    	  JSONObject auxn2 = new JSONObject(auxn);
	    	  JSONArray otro = auxn2.getJSONArray("elements");
	    	  System.out.println(i+" "+otro);
	    	  
	    	  String auxn3 =otro.toString();
	    	  auxn3=auxn3.substring(1, auxn3.length()-1);
	    	  System.out.println(i+" "+auxn3);
	    	  JSONObject otro2 = new JSONObject(auxn3);
	    	  JSONObject otro3 = otro2.getJSONObject("duration");
	    	  JSONObject otro4 = otro2.getJSONObject("distance");
	    	  //tiempo y distancia obtiene el tiempo y distancia entre 2 puntos
	    	  String tiempo = otro3.getString("text");
	    	  String distancia = otro4.getString("text");
	    	  tiempo=tiempo.substring(0, 2);	
	    	  distancia=distancia.substring(0, 4);
	    	  
	    	  //Estos ifs quita los espacios " " para poder hacer un cast
	    		  if(tiempo.substring(2,2)==" ")
	    		  tiempo=tiempo.substring(0,1);
	    	  
	    		  if(distancia.substring(4,4)==" ")
	    		  distancia=distancia.substring(1,3);
	    		  if(distancia.substring(3,3)==" ")
	    		  distancia=distancia.substring(1,2);
	    	  //Generación de Cast
	    	 Float time=Float.parseFloat(tiempo);
	    	  Float dist=Float.parseFloat(distancia);
	    	  System.out.println(time);
	    	  System.out.println(dist);
	    	  
	    	/*  String auxn4 =otro3.toString();
	    	  auxn4=auxn4.substring(1, auxn4.length()-1);
	    	  JSONObject otro4 = new JSONObject(auxn4);
	    	  JSONObject otro5 = otro4.getJSONObject("text");
	    	  System.out.println(i+" "+otro5);*/
	    	  
	    	  // JSONObject nodeStats2 = nodeStats.getJSONObject("elements");
	    	  
	    	  // JSONObject sSDR = nodeStats.getJSONObject("rows");
	    	  // System.out.println(sSDR);
	    	// String json=response.body().string();
	    	 
			System.out.println();
		} catch (IOException | JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	       }
	      
	        
	      
	      }
	    });

	    JFrame f = new JFrame("Text Form Example");
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.getContentPane().add(form, BorderLayout.NORTH);
	    JPanel p = new JPanel();
	    p.add(submit);
	    f.getContentPane().add(p, BorderLayout.SOUTH);
	    f.getContentPane().add(labelcarac, BorderLayout.WEST);
	    f.add(comboBox);
	    f.pack();
	    f.setVisible(true);
	    
	    
		
		
		

	}
}
