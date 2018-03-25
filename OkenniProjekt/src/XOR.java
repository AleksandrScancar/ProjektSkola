import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;

/**
 * <h1>XOR encoder/decoder</h1>
 * @author Adam �tursa
 *
 */

public class XOR {
	/**
	 * 
	 * <h3> public static void crypt(String path, String key,String finalfile) </h3>
	 * <br>
	 * Tato metoda na�te text z vybran�ho souboru,
	 * uprav� ho tak aby aby netvo�il ne��dan� znaky
	 * (odstran� diakritiku, proto�e ta skoro v�dy vede 
	 * k takov�mto znak�m + hl�d� aby v�sledn� znak nebyl 
	 * mezera), text zak�duje a ulo�� do vybran�ho souboru
	 * <br>
	 * <p></p>
	 * @param path cesta k u�ivatelem vybran�mu souboru
	 * @param key u�ivatelem zadan� kl�� pro zakl��ov�n�/odkl��ov�n�
	 * @param finalfile cesta k u�ivatelem vybran�m souborem pro ulo�en�
	 * @throws Exception File not exists
	 * @throws Exception Key not given
	 */
    public static void crypt(String path, String key,String finalfile) throws Exception {
    	if(new File(path).exists()) {
    		if(!key.equals(null)) {
		    	ArrayList<String> lines = new ArrayList<String>();
				try {
					BufferedReader br = new BufferedReader(new FileReader(path));
					String s = "";
					while((s = br.readLine()) != null)
						lines.add(s);
					br.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				for(int j = 0; j < lines.size(); j++) {
					String normalized = Normalizer.normalize(lines.get(j), Normalizer.Form.NFD);
					String text = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
			    	String msg = "";
			    	for(int i = 0; i < text.length();i++) {
			    		int xor = text.charAt(i) ^ key.charAt(i%key.length());
			    		msg += (char)xor;
			    	}
			    	lines.set(j, msg);
				}	
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(finalfile));
					for(String s : lines) {
						bw.write(s);
						bw.newLine();
					}
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

    		}else {
    			throw new Exception("Key not given");
    		}
    	}else {
    		throw new Exception("File not exists");
    	}
    }

}
