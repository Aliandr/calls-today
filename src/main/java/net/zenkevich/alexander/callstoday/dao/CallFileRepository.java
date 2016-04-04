package net.zenkevich.alexander.callstoday.dao;

import net.zenkevich.alexander.callstoday.model.Call;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;

import static org.apache.commons.lang3.StringUtils.upperCase;

/**
 * Call dao implementation
 */
@Repository
public class CallFileRepository implements CallDao {

  private static final String FILE_EXTENSION = ".txt";
  private static final String UNDERSCORE = "_";

  public static final String FOLDER = "calls";
  public static final String TIME_FORMAT = "HH:mm:ss";

  /**
   * Saves Call to file
   * The file name will be capitalized: LASTNAME_FIRSTNAME.txt.
   * The telephone number will be placed in the first row.
   * The time in the 'HH:mm:ss' format will be placed in the second row.
   *
   * @param call object to save
   */
  @Override
  public void add(Call call) {
    createDir();
    File file = new File(getFileName(call));
    Writer writer = null;
    try {
      writer = new FileWriter(file);
      writer.write(call.getTelephone());
      writer.write(System.lineSeparator());
      SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT);
      writer.write(dateFormat.format(call.getTime()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      IOUtils.closeQuietly(writer);
    }

  }

  private void createDir() {
    File dir = new File("calls");
    if (!dir.exists() || !dir.isDirectory()) {
      dir.mkdir();
    }
  }

  private String getFileName(Call call) {
    StringBuilder sb = new StringBuilder(FOLDER);
    sb.append(File.separator);
    sb.append(upperCase(call.getLastName()));
    if (StringUtils.isNotBlank(call.getFirstName())) {
      sb.append(UNDERSCORE);
      sb.append(upperCase(call.getFirstName()));
    }
    sb.append(FILE_EXTENSION);
    return sb.toString();
  }
}
