package com.amatellanes.android.examples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private static final int ABRIRFICHERO_RESULT_CODE = 1;

	private Button btnAbrir;
	private TextView txtInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		btnAbrir = (Button) findViewById(R.id.btnAbrir);
		txtInfo = (TextView) findViewById(R.id.txtInfo);

		btnAbrir.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("file/*");
		startActivityForResult(intent, ABRIRFICHERO_RESULT_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case ABRIRFICHERO_RESULT_CODE:
			if (resultCode == RESULT_OK) {
				
				// Mostramos por pantalla la ruta del archivo seleccionado.
				String ruta = data.getData().getPath();
				txtInfo.setText(ruta);
			}
		}
	}
}
