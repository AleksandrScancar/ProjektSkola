import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;

public class XOR {

    public static File encrypt(String path, String key,String finalfile) throws Exception {
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
				String[] pathSplitted = path.split("\\.");
				File f= new File(pathSplitted[0] + "Encrypted." + pathSplitted[1]);	
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
				return f;
    		}else {
    			throw new Exception("Key not given");
    		}
    	}else {
    		throw new Exception("File not exists");
    	}
    }

}
