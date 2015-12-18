package fr.cmm.tags;

import static org.apache.commons.lang3.StringEscapeUtils.escapeXml10;
import static org.apache.commons.lang3.StringUtils.replace;

/**
 * Created by pomme on 17/12/2015.
 */
public class Functions {

    public static String transformText(String input){
            return replace(escapeXml10(input == null ? "" : input), "\n", "<br>");
    }

}
