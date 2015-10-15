import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;



public class crear_fichero {
	static String datos_fichero;
	public static String TEXT; 
	public static Integer nohaydatos = 0;
	public static String archivo_entrada_datos;
	public static String landis(Date Fecha,String tag,String ruta_archivo_landis,String ruta_archivo_pi_ufl){
	

		try {
			FileReader fr;
			Format formatter = new SimpleDateFormat("HH");
			String hora= formatter.format(Fecha);
			formatter = new SimpleDateFormat("mm");
			String minutos= formatter.format(Fecha);
			formatter = new SimpleDateFormat("dd");
			String dia= formatter.format(Fecha);
			formatter = new SimpleDateFormat("yyyy");
			String año= formatter.format(Fecha);
			formatter = new SimpleDateFormat("MM");
			String mes= formatter.format(Fecha);
			
			GregorianCalendar cal_fecha = new GregorianCalendar ();
			cal_fecha.setTime(Fecha);
			cal_fecha.add(Calendar.HOUR_OF_DAY,-1);
			Date Fecha_menos_una_hora = cal_fecha.getTime();
			
		//	formatter = new SimpleDateFormat("HH");
		//	 String hora_anterior= formatter.format(Fecha_menos_una_hora);
		//	formatter = new SimpleDateFormat("mm");
		//	 String minutos_hora_anterior= formatter.format(Fecha_menos_una_hora);
			formatter = new SimpleDateFormat("dd");
			 String dia_hora_anterior= formatter.format(Fecha_menos_una_hora);
			formatter = new SimpleDateFormat("yyyy");
			 String año_hora_anterior= formatter.format(Fecha_menos_una_hora);
			formatter = new SimpleDateFormat("MM");
			 String mes_hora_anterior= formatter.format(Fecha_menos_una_hora);
			
			 
			 
			 
			if (hora.equals("00") || hora.equals("24")){
				archivo_entrada_datos = ruta_archivo_landis + dia_hora_anterior + mes_hora_anterior + año_hora_anterior + tag+ "PM1TM1.txt";	
			}else{
			archivo_entrada_datos = ruta_archivo_landis + dia + mes + año + tag+ "PM1TM1.txt";
			}
			
			
			
			System.out.println("Fichero origen...");
			System.out.println(archivo_entrada_datos);
			boolean exists = (new File(archivo_entrada_datos)).exists();
			//System.out.println(exists);
			if (exists) {
				fr = new FileReader(archivo_entrada_datos);
				@SuppressWarnings("resource")
				BufferedReader myInput = new BufferedReader(fr);
				String s;
				StringBuffer b = new StringBuffer();
				TEXT= null;
				while ((s = myInput.readLine()) != null)  {
					if (s.startsWith(dia + "/"+ mes + "/" + año + " " + hora + ":00")){
					b.append(s);
					b.append("\n");
					TEXT = b.toString();
					
					}
					
				 }
				if (TEXT == null){
					nohaydatos = 1;
				}else
				nohaydatos = 0;
								
				
			} else {
				nohaydatos = 1;
			}
			
			
			
			if (nohaydatos == 0 ){
						 String tag_numero="";
						 String tag_nombre="";
						if (tag.equals("00001")){
							 tag_numero = "62499";
							 tag_nombre = "GT1";
						}
						if (tag.equals("00002")){
							 tag_numero = "62500";
							 tag_nombre = "GT2";
						}
						if (tag.equals("00003")){
							 tag_numero = "62501";
							 tag_nombre = "TV";
						}
						if (tag.equals("00004")){
							 tag_numero = "62502";
							 tag_nombre = "GATIKA";
						}
						if (tag.equals("00005")){
							 tag_numero = "62503";
							 tag_nombre = "ITXASO";
						}
						if (tag.equals("00006")){
							 tag_numero = "62504";
							 tag_nombre = "30KV";
						}
						
						
						String [] strings = TEXT.split(",");
						
						String value1 = "1";
						String fechahora = strings[0];
						String value2 = "1";
						String value3 = "1";		
						String IMPORTED = strings[2];
						String value5 = strings[3];
						String EXPORTED = strings[4];
						String value7 = strings[5];
						String RQ1_IMPORTED =strings[6];
						String value9 = strings[7];
						String RQ2_EXPORTED  =strings[8];
						String value11 = strings[9];
						String RQ3_EXPORTED =strings[10];
						String value13 = strings[11];
						String RQ4_IMPORTED =strings[12];
						String value15 = strings[13];
						String value16 = strings[14];
						String texto_fichero_salida= tag_numero + "\t"+ value1 +"\t" + fechahora + ":00" + "\t" + value2 +"\t"+ value3 + "\t"+ IMPORTED + "\t"+ value5 +
								"\t"+ EXPORTED +"\t" + value7 +"\t" + RQ1_IMPORTED + "\t"+ value9+ "\t"+ RQ2_EXPORTED +"\t"+ value11 +"\t"+ RQ3_EXPORTED +
								"\t"+ value13 + "\t"+RQ4_IMPORTED + "\t" + value15 + "\t"+ value16;
								
						//System.out.println(texto_fichero_salida );
						String nombre_fichero_salida = tag_nombre + "-" + año + mes + dia + hora + minutos +".TXT";
						System.out.println("Fichero salida para PI...");
						System.out.println(ruta_archivo_pi_ufl + nombre_fichero_salida);
						File f = new File(ruta_archivo_pi_ufl + nombre_fichero_salida);
						FileWriter fw;
						fw = new FileWriter(f);
						fw.write(texto_fichero_salida);
						fw.close();
						
								
						datos_fichero = tag_nombre + "-" + año + mes + dia + hora + minutos;
			}else
				datos_fichero = "No hay datos";
			 
		}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
		
	
		
		
		
		
		return datos_fichero;
	}
	
	
}
