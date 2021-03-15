/*
 *
 * Copyright (c) 2021 by Vassal Team.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public
 * License (LGPL) as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 *
 * You should have received a copy of the GNU Library General Public
 * License along with this library; if not, copies are available
 * at http://www.opensource.org.
 */
package VASSAL.i18n;

import java.util.SortedSet;

import VASSAL.tools.ResourcePathFinder;
import VASSAL.tools.DataArchive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Class to support lookup of resources based on language  */
public class I18nResourcePathFinder implements ResourcePathFinder {

  private static final Logger logger =
    LoggerFactory.getLogger(I18nResourcePathFinder.class);
  
  SortedSet<String> images = null;
  DataArchive archive = null;
  String language = "en";
  
  public I18nResourcePathFinder(DataArchive d, String l) {
    archive = d;
    language = l;
  }

  public void changeLanguage(String l) {
    language = l;
  }

  @Override
  public String findImagePath(String name) {
    logger.error("JAV2 " + name);
    String path;
    if (images == null) {
      images = archive.getImageNameSet(true, true);
    }
    if (images != null && !language.equals("en")) {
      path = DataArchive.IMAGE_DIR;
      path = path.substring(0, path.length() - 1);
      path = path + "_" + language + "/" + name;
      logger.error("JAV1 " + path);
      if (images.contains(path)) {
        return path;
      }
    }
    //Not found or not localized
    path = DataArchive.IMAGE_DIR + name;
    logger.error("JAV " + path);
    return path;
  }
}
