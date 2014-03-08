package cz.kinst.jakub.lib;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.net.Uri;

public class FileUtils {
	public static File saveUriToFile(Context context, Uri uri, File file) {
		InputStream input;
		try {
			input = context.getContentResolver().openInputStream(uri);
			final OutputStream output = new FileOutputStream(file);

			final byte[] buffer = new byte[1024];
			int read;

			while ((read = input.read(buffer)) != -1)
				output.write(buffer, 0, read);

			output.flush();

			output.close();

			input.close();
			return file;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
