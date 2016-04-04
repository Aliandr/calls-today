package net.zenkevich.alexander.callstoday.dao;


import net.zenkevich.alexander.callstoday.Application;
import net.zenkevich.alexander.callstoday.model.Call;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class CallFileRepositoryTest {

  private static final String FIRST_NAME = "first";
  private static final String LAST_NAME = "last";
  private static final String FILE_NAME = "LAST_FIRST.txt";
  private static final String TELEPHONE = "00420 111 222 333";

  @Autowired
  private CallDao dao;

  @Before
  public void setUp() throws Exception {
    File file = new File(CallFileRepository.FOLDER + File.separator +FILE_NAME);
    file.deleteOnExit();
  }

  @Test
  public void addTest() throws IOException {
    Call call = new Call(FIRST_NAME, LAST_NAME, TELEPHONE);

    dao.add(call);
    File file = new File(CallFileRepository.FOLDER + File.separator + FILE_NAME);
    assertTrue(file.exists());

    List<String> strings = IOUtils.readLines(new FileInputStream(file));
    assertEquals("File should contain 2 lines", strings.size(), 2);
    assertEquals("First line should be telephone number", strings.get(0), TELEPHONE);
    SimpleDateFormat timeFormat = new SimpleDateFormat(CallFileRepository.TIME_FORMAT);
    assertEquals("Second line should be time in format " + CallFileRepository.TIME_FORMAT, strings.get(1), timeFormat.format(call.getTime()));
  }

  @After
  public void tearDown() throws Exception {
    File file = new File(CallFileRepository.FOLDER + File.separator +FILE_NAME);
    file.deleteOnExit();
  }
}
