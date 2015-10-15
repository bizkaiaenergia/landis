


	import java.io.*;
import java.util.Properties;
public class propiedades {
	
	
	String archivo_properties="landis.properties";
	
	
	String ruta_archivo_landis;
	String ruta_archivo_pi_ufl;
	String minutos_generacion;
	String reinicio_sin_datos;
	String incremento_minutos;
	String host;
	String from;
	String to;
	String cc;
	
	Properties prop  = new Properties();
	
	String ruta_archivo_landis(){
			try{
				prop.load(new FileInputStream(archivo_properties));
				ruta_archivo_landis = prop.getProperty("ruta_archivo_landis");
			}catch(IOException e){
			}
			return ruta_archivo_landis;
			}
	String ruta_archivo_pi_ufl(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			ruta_archivo_pi_ufl = prop.getProperty("ruta_archivo_pi_ufl");
		}catch(IOException e){
		}
		return ruta_archivo_pi_ufl;
		}
	
	String minutos_generacion(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			minutos_generacion = prop.getProperty("minutos_generacion");
		}catch(IOException e){
		}
		return minutos_generacion;
		}
	
	String  reinicio_sin_datos(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			reinicio_sin_datos = prop.getProperty("reinicio_sin_datos");
		}catch(IOException e){
		}
		return reinicio_sin_datos;
		}
	String  incremento_minutos(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			incremento_minutos = prop.getProperty("incremento_minutos");
		}catch(IOException e){
		}
		return incremento_minutos;
		}
	String host(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			host = prop.getProperty("host");
		}catch(IOException e){
		}
		return host;
		}
	String from(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			from = prop.getProperty("from");
		}catch(IOException e){
		}
		return from;
		}
	String to(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			to = prop.getProperty("to");
		}catch(IOException e){
		}
		return to;
		}
	String cc(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			cc = prop.getProperty("cc");
		}catch(IOException e){
		}
		return cc;
		}
	
	
}