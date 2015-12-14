






import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Format;
import java.text.SimpleDateFormat;

import java.util.Date;


public class landis_pi_ufl {
	//14/12/2015 Se añade funcionalidad de parar y arrancar procesos landis en caso de fallo de comunicaciones. v 2.0.3.
	
	
	public static String ultimo_bucle_generado;
	public static String fallo;
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		System.out.println("Importación ficheros sistema Landis a PI_UFL");
		//Cogemos fecha y hora actuales.
		Integer	generar_ahora= 0;
		Integer bucle = 1;
		Boolean GT1_fallo=false;
		Boolean GT2_fallo=false;
		Boolean TV_fallo=false;
		Boolean GATIKA_fallo=false;
		Boolean ITXASO_fallo=false;
		Boolean KV_fallo=false;
		
		
		ultimo_bucle_generado = "200101010000";
		propiedades Archivopropiedades1 = new propiedades();
		String minutos_generacion = Archivopropiedades1.minutos_generacion();
		String reinicio_sin_datos = Archivopropiedades1.reinicio_sin_datos();
		String incremento_minutos = Archivopropiedades1.incremento_minutos();
		String host = Archivopropiedades1.host();
		String from = Archivopropiedades1.from();
		String to = Archivopropiedades1.to();
		String cc = Archivopropiedades1.cc();

		
		
		while (bucle == 1){	
			//añadido 18 agosto 2014 marcos para que el bucle se reinicie al pasar una hora sin datos.
			if (Integer.parseInt(minutos_generacion) > Integer.parseInt(reinicio_sin_datos)){
				minutos_generacion = Archivopropiedades1.minutos_generacion();
				//ultimo_bucle_generado = "200101010000";
			}
			
			
			System.out.println("Se generarán los ficheros a los " + minutos_generacion + " minutos de cada hora");
			do {
			//Espera un tiempo 
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//propiedades Archivopropiedades1 = new propiedades();
			//String minutos_generacion = Archivopropiedades1.minutos_generacion();
			
			Date fecha = new Date();
			Format formatter = new SimpleDateFormat("HH");
			String hora_actual= formatter.format(fecha);
			formatter = new SimpleDateFormat("mm");
			String minutos_actual= formatter.format(fecha);
			formatter = new SimpleDateFormat("dd");
			String dia_actual= formatter.format(fecha);
			formatter = new SimpleDateFormat("yyyy");
			String año_actual= formatter.format(fecha);
			formatter = new SimpleDateFormat("MM");
			String mes_actual= formatter.format(fecha);
			
			String nuevo_bucle_a_generar = año_actual + mes_actual + dia_actual + hora_actual + minutos_actual;
			
			//importante admiración delante de ultimo_bucle_generado ....
			
			if ( (Integer.parseInt(minutos_actual)-Integer.parseInt(minutos_generacion)) == 0 && !ultimo_bucle_generado.equals(nuevo_bucle_a_generar) ){
			 	generar_ahora = 1;
			 	ultimo_bucle_generado = año_actual + mes_actual + dia_actual + hora_actual + minutos_actual;
			}else{
			//	generar_ahora = 1; !!!!!!!!!!!!!!!!!!!!!ATENCIÓN SOLO PARA TEST HAY QUE DEJAR EL "1" !!!!!!!!!!!!!!!!!!!!!!
				generar_ahora = 0;
			}
			//System.out.println( año_actual + mes_actual + dia_actual + hora_actual + minutos_actual );
					
			}while(generar_ahora != 1);
		
		
		
			propiedades Archivopropiedades = new propiedades();
			String ruta_archivo_landis = Archivopropiedades.ruta_archivo_landis();
			String ruta_archivo_pi_ufl = Archivopropiedades.ruta_archivo_pi_ufl();
		//System.out.println(ruta_archivo_landis);
		//System.out.println(ruta_archivo_pi_ufl);
			
			Date Fecha = new Date();
			String tag = "00001";
			final String datos_fichero1 = crear_fichero.landis(Fecha, tag, ruta_archivo_landis, ruta_archivo_pi_ufl);
			if (datos_fichero1.equals("No hay datos")){
				 GT1_fallo = true;
			}else{
				GT1_fallo = false;
			}
			tag = "00002";
			final String datos_fichero2= crear_fichero.landis(Fecha, tag, ruta_archivo_landis, ruta_archivo_pi_ufl);
			if (datos_fichero2.equals("No hay datos")){
				 GT2_fallo = true;
			}else{
				GT2_fallo = false;
			}
			tag = "00003";
			final String datos_fichero3= crear_fichero.landis(Fecha, tag, ruta_archivo_landis, ruta_archivo_pi_ufl);
			if (datos_fichero3.equals("No hay datos")){
				 TV_fallo = true;
			}else{
				TV_fallo = false;
			}
			tag = "00004";
			final String datos_fichero4= crear_fichero.landis(Fecha, tag, ruta_archivo_landis, ruta_archivo_pi_ufl);
			if (datos_fichero4.equals("No hay datos")){
				 GATIKA_fallo = true;
			}else{
				GATIKA_fallo = false;
			}
			tag = "00005";
			final String datos_fichero5 = crear_fichero.landis(Fecha, tag, ruta_archivo_landis, ruta_archivo_pi_ufl);
			if (datos_fichero5.equals("No hay datos")){
				 ITXASO_fallo = true;
			}else{
				ITXASO_fallo = false;
			}
			tag = "00006";
			final String datos_fichero6 = crear_fichero.landis(Fecha, tag, ruta_archivo_landis, ruta_archivo_pi_ufl);
			if (datos_fichero6.equals("No hay datos")){
				 KV_fallo = true;
			}else{
				KV_fallo = false;
			}
			
			
			if (GT1_fallo.equals(true) |GT2_fallo.equals(true)|TV_fallo.equals(true)|GATIKA_fallo.equals(true)|ITXASO_fallo.equals(true)|KV_fallo.equals(true) ){
			 //if (datos_fichero1.equals("No hay datos")){
				fallo = "Hay un fallo en los contadores Landis";
				if (GT1_fallo.equals(true)){
					fallo = " GT1,";
				}
				if (GT2_fallo.equals(true)){
					fallo = fallo + " GT2,";
				}
				if (TV_fallo.equals(true)){
					fallo = fallo + " TV,";
				}
				if (GATIKA_fallo.equals(true)){
					fallo = fallo + " Gatika,";
				}
				if (ITXASO_fallo.equals(true)){
					fallo = fallo + " Itxaso,";
				}
				if (KV_fallo.equals(true)){
					fallo = fallo + " 30KV";
				}
				
				
				Integer  nuevos_minutos_generacion = Integer.parseInt(minutos_generacion)+ Integer.parseInt(incremento_minutos);
				//Añadido marcos 19/ago/2014 para controlar el mensaje de salida al sobrepasarse el numero de intentos por hora.
				if (nuevos_minutos_generacion >  Integer.parseInt(reinicio_sin_datos)){
					System.out.println("No hay datos se va a intentar de nuevo en la hora siguiente");
					//String fallo = ("Fallo en "+"GT1="+ GT1_fallo+" GT2="+GT2_fallo+" TV="+TV_fallo+" GATIKA="+GATIKA_fallo+" ITXASO="+ITXASO_fallo+" 30KV="+KV_fallo);
					System.out.println(fallo);
					SendEmail.main(fallo,host,from,to,cc);
					//Se debe haber colgado el proceso de LANDIS.
					//Se intenta matar el proceso.PAdqAutT2k4700.exe y AdqAut.exe
					 String processName1 = "PAdqAutT2k4700.exe";
					 String processName2 = "AdqAut.exe";
					 //System.out.print(isProcessRunning(processName));
					 if (KillProcess.isProcessRunning(processName1)) {
							 KillProcess.killProcess(processName1);
					 }
					 if (KillProcess.isProcessRunning(processName2)) {
						 KillProcess.killProcess(processName2);
					 }
					 Thread.sleep(5000);
					 if (KillProcess.isProcessRunning(processName1) == false && KillProcess.isProcessRunning(processName2)== false){
						 System.out.println("Se han matado los procesos");
						 //arrancar el programa"
						 Runtime rt_landis = Runtime.getRuntime();
						 Process pr1_landis = rt_landis.exec("cmd /c E://Landis+Gyr//TarSys//AdqAut.exe 1");
						 System.out.println("cmd /c E://Landis+Gyr//TarSys//AdqAut.exe 1");
						 BufferedReader input_landis = new BufferedReader(new InputStreamReader(pr1_landis.getInputStream()));
							String line_landis=null;
							 while((line_landis=input_landis.readLine()) != null) {
				                    System.out.println(line_landis); 
							 }
							 int exitVal_landis = pr1_landis.waitFor();
							 System.out.println("Exited with error code "+exitVal_landis); 
						
						 
					 }else{
						 System.out.println("NO Se han matado los procesos");
						 SendEmail.main("NO Se han matado los procesos",host,from,to,cc);
					 }
					 
					 					 
					 minutos_generacion = Archivopropiedades1.minutos_generacion();
					
				}else{
				ultimo_bucle_generado = "200101010000";
				System.out.println("No hay datos se va a intentar de nuevo en el minuto " + nuevos_minutos_generacion + " de esta hora");
				//String fallo = ("Fallo en "+"GT1="+ GT1_fallo+" GT2="+GT2_fallo+" TV="+TV_fallo+" GATIKA="+GATIKA_fallo+" ITXASO="+ITXASO_fallo+" 30KV="+KV_fallo);
				SendEmail.main(fallo,host,from,to,cc);
				System.out.println(fallo);
				minutos_generacion = String.valueOf(nuevos_minutos_generacion);
				}
			 }else{
				 minutos_generacion = Archivopropiedades1.minutos_generacion();	
				 //System.out.println(datos_fichero1 + ", " + datos_fichero2 + ", " + datos_fichero3 + ", "+ datos_fichero4 + ", "+ datos_fichero5 + ", "+ datos_fichero6 );
					try {
					Thread.sleep(30000);
					
					File dir = new File(ruta_archivo_pi_ufl);
					String[] files = dir.list();
					/*for (int i=0; i<files.length; i++) {
				        // Get filename of file or directory
				        String filename = files[i];
					}
					*/
					FilenameFilter filter = new FilenameFilter() {
					    public boolean accept(File dir, String name) {
					        return name.contains(ultimo_bucle_generado) && name.endsWith("._OK");
					        //return name.contains(ultimo_bucle_generado) && name.endsWith(".TXT");
					    }
					};
					files = dir.list(filter);
					  for (int i=0;i<(files.length);i++) {  	
					System.out.println(files[i].toString());
					
					  }
					boolean exists = files.length != 0; 
				
					if (exists) {
					System.out.println("Se han importado en PI todos los archivos con extensión _OK");
					
					Runtime rt = Runtime.getRuntime();
					Process pr1 = rt.exec("cmd /c D://PI//bin//pirecalc /ex=10CJA00DE100.XQ20,*-3h,*");
					System.out.println("cmd /c D://PI//bin//pirecalc /ex=10CJA00DE100.XQ20,*-3h,*");
					BufferedReader input = new BufferedReader(new InputStreamReader(pr1.getInputStream()));
					String line=null;
					 while((line=input.readLine()) != null) {
		                    System.out.println(line); 
					 }
					 int exitVal = pr1.waitFor();
					 System.out.println("Exited with error code "+exitVal); 
					 
					Process pr2 = rt.exec("cmd /c D://PI//bin//pirecalc /ex=10CJA00DE100.XQ20I,*-3h,*");
					System.out.println("cmd /c D://PI//bin//pirecalc /ex=10CJA00DE100.XQ20I,*-3h,*"); 
					input = new BufferedReader(new InputStreamReader(pr2.getInputStream()));
					 line=null;
					 while((line=input.readLine()) != null) {
		                    System.out.println(line); 
					 }
					 exitVal = pr2.waitFor();
					 System.out.println("Exited with error code "+exitVal); 
					//System.out.println("b");
					Process pr3 = rt.exec("cmd /c D://PI//bin//pirecalc /ex=FiscalMeter.TAG,*-3h,*");
					System.out.println("cmd /c D://PI//bin//pirecalc /ex=FiscalMeter.TAG,*-3h,*"); 
					 input = new BufferedReader(new InputStreamReader(pr3.getInputStream()));
					 line=null;
					 while((line=input.readLine()) != null) {
		                    System.out.println(line); 
					 }
					 exitVal = pr3.waitFor();
					 System.out.println("Exited with error code "+exitVal); 
					
					
					}else{
						 System.out.println("PI no ha importado los ficheros satisfactoriamente"); 
					}
					
					
					
					}	catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 }
			 
			 
			 
			 
			 
			 
			 
		}
		
	}

	

	

}
