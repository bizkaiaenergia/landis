
public class test {
	
	
	public static void main(String[] args) throws Exception {
		 String processName = "notepad.exe";
	 //System.out.print(isProcessRunning(processName));
	 if (KillProcess.isProcessRunning(processName)) {
			 KillProcess.killProcess(processName);
	 }
}
}