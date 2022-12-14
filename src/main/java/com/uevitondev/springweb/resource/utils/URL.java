package com.uevitondev.springweb.resource.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

    public static List<Integer> decodeIntList(String s) {
        String[] vet = s.split(",");
        List<Integer> lista = new ArrayList<>();

        for (int i = 0; i < vet.length; i++) {
            lista.add(Integer.parseInt(vet[i]));
        }
        return lista;
        // return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
    }


    public static String decodeParam(String param) {

        try {
            return URLDecoder.decode(param, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }

    }
}
