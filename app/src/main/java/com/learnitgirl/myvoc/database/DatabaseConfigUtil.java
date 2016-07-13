package com.learnitgirl.myvoc.database;

/**
 * Created by joanna on 13.07.16.
 */

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;
import com.learnitgirl.myvoc.utils.Word;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * DatabaseConfigUtl writes a configuration file to avoid using annotation processing in runtime which is very slow
 * under Android. This gains a noticeable performance improvement.
 *
 * The configuration file is written to /res/raw/ by default. More info at: http://ormlite.com/docs/table-config
 */
public class DatabaseConfigUtil extends OrmLiteConfigUtil {

    private static final Class<?>[] classes = new Class[] {
            Word.class,
    };

    public static void main(String[] args) throws SQLException, IOException {
        writeConfigFile(new File("ormlite_config.txt"), classes);
    }
}