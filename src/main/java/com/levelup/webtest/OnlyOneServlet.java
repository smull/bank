package com.levelup.webtest;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;



import com.levelup.webtest.model.Controller;
import com.levelup.webtest.model.IController;
import com.levelup.webtest.myController.LogOut;
import com.levelup.webtest.myController.Login;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

/**
 * Created by user on 03.02.2015.
 */
public class OnlyOneServlet extends HttpServlet {

    private String milk = "";
    private Set<Class<? extends Object>> clazzSet;
    private List<String> listClasses = new ArrayList<String>();

    private Map<String,Object> mapClassesName = new HashMap<String,Object>();

    private static  int count = 0;

    private String pkg = "com.levelup.webtest.myController";

    @Override
    public void init() throws ServletException {
       milk = "dsdsds";

       clazzSet = getClazzSet(pkg);
       for (Class<? extends Object> clazz : clazzSet) {
           int n = clazz.getName().lastIndexOf('.');
           //System.out.println(clazz.getName());
           String needName = clazz.getName();
           //System.out.println(clazz.getName().substring(++n));
           String nameClass = clazz.getName().substring(++n);
           //Controller c = clazz.getAnnotation(Controller.class);
           if(clazz.isAnnotationPresent(Controller.class) &&
                 clazz.getAnnotation(Controller.class).nameController().equalsIgnoreCase("controller")) {
                //listClasses.add(nameClass);
                //Controller c = clazz.getAnnotation(Controller.class);
                //System.out.println(nameClass);
               if(nameClass.equals("Login")){

                       //clazz = Class.forName(needName);
                       //Login login = (Login)clazz.newInstance();
                       mapClassesName.put("login",new Login());

               }
               if(nameClass.equals("LogOut")){

                       //clazz = Class.forName(needName);
                       //Login logOut = (Login)clazz.newInstance();
                       mapClassesName.put("logout",new LogOut());

               }

           }

       }

    }

    public static Set<Class<? extends Object>> getClazzSet(String pkg) {
        // prepare reflection, include direct subclass of Object.class
        Reflections reflections = new Reflections(new ConfigurationBuilder().setScanners(new SubTypesScanner(false), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(ClasspathHelper.classLoaders(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().includePackage(pkg)));

        return reflections.getSubTypesOf(Object.class);
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //HttpServletRequest request = (HttpServletRequest) servletRequest;

        //StringBuffer url = request.getRequestURL();
        String justTry = request.getRequestURI().substring(1);
        //String urlMy = String.valueOf(url);
        //String[] myUrls = urlMy.split("/");
        //String nameUrl = myUrls[1];

        //System.out.println(justTry);
        //if(justTry.endsWith(".jsp")){

        /*RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        if (dispatcher != null){
            dispatcher.forward(request, response);

        }*/

        IController iController = (IController) mapClassesName.get(justTry);
        String link =  iController.service(request, response);
        getServletContext().getRequestDispatcher("/" + link).forward( request, response );
        System.out.println(link);

        System.out.println(count++);


        /*for (String key : mapClassesName.keySet()) {
            System.out.println(mapClassesName.get(key));
        }*/


        //getServletContext().getRequestDispatcher("/newlogin.jsp").forward( request, response );
        //request.getRequestDispatcher("/newlogin.jsp").forward(request, response);


        //RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin.jsp");

          //  dispatcher.forward( request, response );
        /*if(justTry.endsWith(".jsp")) {
            //System.out.println(justTry);
            String nameClass = checkByList(justTry);
            Class clazz = null;
            try {
                clazz = Class.forName(nameClass);
                Login objLog = (Login)clazz.newInstance();
                objLog.serviceControllerIn(request,response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }*/
        //String pkg = "com.levelup.webtest.myController";

        /*clazzSet = getClazzSet(pkg);
        for (Class<? extends Object> clazz : clazzSet) {
            int n = clazz.getName().lastIndexOf('.');

            //System.out.println(clazz.getName().substring(++n));
            String nameClass = clazz.getName().substring(++n);
            //Controller c = clazz.getAnnotation(Controller.class);
            if(clazz.isAnnotationPresent(Controller.class) &&
                    clazz.getAnnotation(Controller.class).nameController().equalsIgnoreCase("controller")) {
                listClasses.add(nameClass);
                //Controller c = clazz.getAnnotation(Controller.class);
                //System.out.println("YES");
            }

        }*/


        //System.out.println(milk);


    }


    /*public String checkByList(String urlName) {
        switch (urlName) {
            case "newlogin.jsp":
                for(String s : listClasses) {
                    if(s.equalsIgnoreCase(Constants.LOGIN)){
                        return pkg + "." + Constants.LOGIN;
                    }
                }
                break;
            case "content.jsp":
                for(String s : listClasses) {
                    if(s.equalsIgnoreCase(Constants.LOGIN)){
                        return pkg + "." + Constants.LOGOUT;
                    }
                }
                break;
        }
        return "";
    }*/


}
