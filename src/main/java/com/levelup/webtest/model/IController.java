package com.levelup.webtest.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by SMULL on 04.02.2015.
 */
public interface IController {
    String service(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
