package test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		String base = "!#@abcdefghijklmnopqrstuvwxyz0123456789";   
//	    Random random = new Random();   
//	    StringBuffer sb = new StringBuffer();   
//	    for (int i = 0; i < 10; i++) {   
//	        int number = random.nextInt(base.length());   
//	        sb.append(base.charAt(number));   
//	    }   
//	    //return sb.toString();
//	    System.out.println(sb.toString());
//
//	}
	public void setLocalProxy() {
        Properties prop = System.getProperties();
        prop.setProperty("http.proxyHost", "222.222.222.222");
        prop.setProperty("http.proxyPort", "3128");
 
    }
    // 清除proxy设置
    public void removeLocalProxy()
    {
    Properties prop = System.getProperties();
    prop.remove("http.proxyHost");
    prop.remove("http.proxyPort");
    prop.remove("http.nonProxyHosts");
    prop.remove("https.proxyHost");
    prop.remove("https.proxyPort");
    prop.remove("ftp.proxyHost");
    prop.remove("ftp.proxyPort");
    prop.remove("ftp.nonProxyHosts");
    prop.remove("socksProxyHost");
    prop.remove("socksProxyPort");
    }
    // 测试HTTP代理
    //public void showHttpProxy(Object... proxy) {
    public void showHttpProxy(Object ... proxy) {
        System.out.println("showHttpProxy start");
        URL url = null;
        try {
            url = new URL("http://www.baidu.com");
        } catch (MalformedURLException e) {
        	System.out.println("0");
        	e.printStackTrace();
            return;
        }
        try {
            URLConnection conn = null;
            switch (proxy.length) {
            case 0:
                conn = url.openConnection();
                break;
            case 1:
                conn = url.openConnection((Proxy) proxy[0]);
                break;
            default:
                break;
            }
            if (conn == null){
            	System.out.println("1");
            	return;
            }
            conn.setConnectTimeout(10000); // 设置连接超时时间
            InputStream in = conn.getInputStream();
            BufferedReader br = new BufferedReader(new java.io.InputStreamReader(in));
            String str = null;
            try {
                while ((str = br.readLine())!=null) {
                    if(str.indexOf("百度一下，你就知道")>0){
                        break;
                    }
                }
             
            } catch (IOException e1) {
            	System.out.println("2");
            	e1.printStackTrace();
            }
            System.out.println("showHttpProxy end");

        } catch (Exception e1) {
        	System.out.println("3");
        	e1.printStackTrace();
            return;
        }
    }
 
    // 得到一个proxy
    public static Proxy getProxy(String host, int port) {
        SocketAddress addr = new InetSocketAddress(host, port);
        Proxy typeProxy = new Proxy(Proxy.Type.HTTP, addr);
        return typeProxy;
    }
/*    // 得到一个proxy
    public Proxy getProxy(Proxy.Type type, String host, int port) {
        SocketAddress addr = new InetSocketAddress(host, port);
        Proxy typeProxy = new Proxy(type, addr);
        return typeProxy;
    }*/
    public void test(){
        String pathurl = "d:/temp.txt";
        try {
            File file = new File(pathurl);
            FileInputStream fin = new FileInputStream(file);
            BufferedInputStream bin = new BufferedInputStream(fin);
            BufferedReader ir = new BufferedReader(new InputStreamReader(bin));
            String str = null;
            int count = 0;
            @SuppressWarnings("unused")
			StringBuffer ip = new StringBuffer();
            while ((str = ir.readLine()) != null) {
             
                String ipfilter = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
 
                Pattern p = Pattern.compile(ipfilter);
                Matcher m = p.matcher(str);
                if (m.find()) {
                    count++;
                    String port = str.substring(str.indexOf(":")+1,str.length());
                    if(port.indexOf("0")==0){
                        port = port.substring(2, port.length());
                    }
                    String host = m.group();
                    int po = Integer.parseInt(port);
                    //System.out.println(host+"--"+port);
                    System.out.println("----------"+count);
                    showHttpProxy(getProxy(host,po));
                }
                // 获取端口号
 
            }
        /*    System.out.println(ip);
            java.io.FileWriter fileWriter = new java.io.FileWriter(pathurl);
            fileWriter.write(ip.toString());
            fileWriter.close();
            bin.close();
            fin.close();*/
        } catch (Exception e) {
 
        }
        //showHttpProxy(getProxy("58.211.78.143",80));
    }
    public static void main(String[] args) {
        Test proxy = new Test();
        //proxy.setLocalProxy();
//        proxy.showHttpProxy(getProxy("203.162.2.138",80));
        proxy.showHttpProxy();
         
         
    }

}
