package com.example.androidhive;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;



public class ServicioAudio extends Activity implements OnCompletionListener
{

	TextView tv1,tv2,tv3,Resul;
	MediaRecorder recorder;
	MediaPlayer player;
	File archivo;
	File archivo2;
	ImageButton b1, b2, b3;
	Button btnguardar;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.audio);

		tv1 = (TextView) this.findViewById(R.id.textView1);
		tv2= (TextView) findViewById(R.id.edtitulo);
		tv3 = (TextView) findViewById(R.id.edtcategoria);
		b1 = (ImageButton) findViewById(R.id.button1);
		b2 = (ImageButton) findViewById(R.id.button2);
		b3 = (ImageButton) findViewById(R.id.button3);
		btnguardar = (Button) findViewById(R.id.Guardar);
		
		
	}

	
	
	
	
	
	

	public void grabar(View v) throws IOException {
		
		String ruta = (String) getText(R.id.tvtitulo);
		
		
		recorder = new MediaRecorder();
		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		File path = new File(Environment.getExternalStorageDirectory()
				.getPath());
		
		
		try {
			archivo = File.createTempFile(ruta, ".3gp", path);
			
		} catch (IOException e) {
		}
		recorder.setOutputFile(archivo.getAbsolutePath());
		try {
			recorder.prepare();
		} catch (IOException e) {
		}
		recorder.start();
		tv1.setText("Grabando");
		b1.setEnabled(false);
		b2.setEnabled(true);
	}

	@SuppressWarnings("resource")
	public byte[] getBytesFromFile(java.io.File path) throws IOException {
		InputStream is = new FileInputStream(path);
        long length = path.length();
        if (length > Integer.MAX_VALUE)
                         {

        }
        byte[] bytes = new byte[(int)length];
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+path.getName());
        }
        is.close();
        return bytes;
	}





	public void detener(View v) {
		recorder.stop();
		recorder.release();
		player = new MediaPlayer();
		player.setOnCompletionListener(this);
		try {
			player.setDataSource(archivo.getAbsolutePath());
		} catch (IOException e) {
		}
		try {
			player.prepare();
		} catch (IOException e) {
		}
		b1.setEnabled(true);
		b2.setEnabled(false);
		b3.setEnabled(true);
		tv1.setText("Listo para reproducir");
	}

	public void reproducir(View v) {
		player.start();
		b1.setEnabled(false);
		b2.setEnabled(false);
		b3.setEnabled(false);
		tv1.setText("Reproduciendo");
	}

	public void onCompletion(MediaPlayer mp) {
		b1.setEnabled(true);
		b2.setEnabled(true);
		b3.setEnabled(true);
		tv1.setText("Listo");
	}
	
	
	public void Guardar(View v) throws IOException{
	
        String fechahora = getDatePhone();
		
		Resul.setText(fechahora);
		
		
		String titulo = (String) getText(R.id.tvtitulo);
		
		
		String categoria = (String) getText(R.id.tvcategoria);
		
		byte[] FileBytes =getBytesFromFile(archivo.getAbsoluteFile());
		
		String base64 = Base64.encodeToString(FileBytes, Base64.NO_WRAP).toString();
		
		
		
		
		
	}
	private String getDatePhone()

	{

	    Calendar cal = new GregorianCalendar();

	    Date date = cal.getTime();

	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	    String formatteDate = df.format(date);

	    return formatteDate;

	}
	
	

	





	
}
