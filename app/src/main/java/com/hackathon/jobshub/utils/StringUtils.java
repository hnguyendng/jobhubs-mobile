package com.hackathon.jobshub.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * Created by Nguyen on 11/21/2015.
 */
public class StringUtils {

    public static final String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
}
