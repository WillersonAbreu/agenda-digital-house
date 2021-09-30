package br.com.santander.agenda.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class Base64Utils {

  public String getBase64(String base64Str) {
    String path = Paths.get("src/main/resources/images").toString();
    String fileName = UUID.randomUUID().toString().replaceAll("-", "");
    if (base64Str == null) {
      return null;
    } else if (base64Str.indexOf("data:image/png;") != -1) {
      base64Str = base64Str.replace("data:image/png;base64,", "");
      fileName += ".png";
    } else if (base64Str.indexOf("data:image/jpeg;") != -1) {
      base64Str = base64Str.replace("data:image/jpeg;base64,", "");
      fileName += ".jpeg";
    } else if (base64Str.indexOf("data:image/jpg;") != -1) {
      base64Str = base64Str.replace("data:image/jpg;base64,", "");
      fileName += ".jpg";
    }
    File file = new File(path, fileName);
    byte[] fileBytes = Base64.getDecoder().decode(base64Str);
    try {
      FileUtils.writeByteArrayToFile(file, fileBytes);
    } catch (IOException e) {
      e.printStackTrace();
    }

    String fileDownloadUri = ServletUriComponentsBuilder
      .fromCurrentContextPath()
      .path("/images/")
      .path(fileName)
      .toUriString();
    return fileDownloadUri;
  }
}
