package com.yl.soft.common.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.UUID;

public class IOUtil extends IOUtils {

	/**
	 * 文件下载
	 * @param response
	 * @param filePath
	 */
	public static void download(HttpServletResponse response, String filePath) throws Exception{
		File file=new File(filePath);
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName="+file.getName());
		response.setContentLength((int)file.length());
		InputStream inputStream = null;
		OutputStream os = null;
		try {
			inputStream = new FileInputStream(file);
			os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}finally {
			// 这里主要关闭。
			if(os!=null && inputStream !=null) {
				os.flush();
				os.close();
				inputStream.close();
			}
		}
	}

    /**
     * url下载
     * @param httpUrl
     * @param id
     * @param suffix
     * @param resp
     * @throws Exception
     */
    public static void download(String httpUrl,String id,String suffix,HttpServletResponse resp) throws Exception{
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("multipart/form-data");
        resp.setHeader("Content-Disposition", "attachment;fileName="
                +(StringUtils.isEmpty(id)?UUID.randomUUID().toString():id)+suffix);
        InputStream is = null;
        OutputStream os = null;
        try {
            URL url = new URL(httpUrl);
            is = url.openStream();
            os = resp.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = is.read(b)) > 0) {
                os.write(b, 0, length);
            }
        }catch (Exception e) {
            throw e;
        }finally {
            if(os != null && is != null) {
                os.flush();
                os.close();
                is.close();
            }
        }
    }


	/**
	 * 在线浏览文件
	 * @param resp
	 * @param filePath
	 * @param contentType
	 * @throws Exception
	 */
	public static void browseOnline(HttpServletResponse resp, String filePath, String httpUrl,String contentType) throws Exception{
		resp.setCharacterEncoding("utf-8");
		resp.setContentType(contentType);//设置浏览器预览的格式
		InputStream is = null;
		OutputStream os = null;
		try {
		    if(!StringUtils.isEmpty(filePath)){
                File file = new File(filePath);
                is = new FileInputStream(file);
            }else if(!StringUtils.isEmpty(httpUrl)){
                URL url = new URL(httpUrl);
                is = url.openStream();
            }
			os = resp.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = is.read(b)) > 0) {
				os.write(b, 0, length);
			}
		}catch (Exception e) {
			throw e;
		}finally {
			if(os != null && is != null) {
				os.flush();
				os.close();
				is.close();
			}
		}
	}

	/**
	 * 调用http请求返回字符串
	 *
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String requestHttpStr(String url, Map<String, String> params, Connection.Method method) throws Exception {
		Connection.Response response = reptileRequest(url,params,method);
		String strResult = new String(response.bodyAsBytes(), "UTF-8");
		return strResult;
	}

	/**
	 * 调用http请求返回doc
	 * @param url
	 * @param params
	 * @param method
	 * @return
	 * @throws Exception
	 */
	public static Document requestHttpDoc(String url, Map<String, String> params, Connection.Method method) throws Exception {
		Connection.Response response = reptileRequest(url,params,method);
		Document document = response.parse();
		return document;
	}

	/**
	 *爬虫请求
	 * @param url
	 * @param params
	 * @param method
	 * @return
	 * @throws Exception
	 */
	private static Connection.Response reptileRequest(String url, Map<String, String> params, Connection.Method method) throws Exception{
		Connection connection = Jsoup.connect(url);
		connection.ignoreContentType(true);
		connection.method(method);// 根据服务端的请求方式来判断是POST、GET
		connection.data(params);
		connection.timeout(1000 * 10);// 设置10秒超时
		Connection.Response response = connection.execute();
		return response;
	}

    /**
     * 向页面打印json字符串
     * @param response
     * @param o
     */
    public static void printJsonToPage(HttpServletResponse response, Object o) {
        try {
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.print(JSON.toJSONString(o));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * 尝试访问url,如果url服务关闭则返回-1
	 * 否则则返回状态码
	 * @param strURL
	 * @return
	 */
	public static Integer tryConnect(String strURL) {
		URL url;
		HttpURLConnection connection = null;
		try {
			try {
				url = new URL(strURL);
				connection = (HttpURLConnection) url.openConnection();
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setUseCaches(false);
				connection.setInstanceFollowRedirects(true);
				connection.setRequestMethod("POST"); // 设置请求方式
				connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
				connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
				connection.connect();
				// 读取响应
				return connection.getResponseCode();
			} catch (Exception e) {
				return -1;
			} // 创建连接
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	/**
	 * 获取图片宽度
	 *
	 * @param url 图片文件
	 * @return 宽度
	 */
	public static int getImgWidth(URL url) {
		InputStream is = null;
		BufferedImage src = null;
		int ret = -1;
		try {
			is = url.openStream();
			src = javax.imageio.ImageIO.read(is);
			ret = src.getWidth(null); // 得到源图宽
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 获取图片宽度
	 *
	 * @param file 图片文件
	 * @return 宽度
	 */
	public static int getImgWidth(File file) {
		InputStream is = null;
		BufferedImage src = null;
		int ret = -1;
		try {
			is = new FileInputStream(file);
			src = javax.imageio.ImageIO.read(is);
			ret = src.getWidth(null); // 得到源图宽
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}


	/**
	 * 获取图片高度
	 *
	 * @param url 图片文件
	 * @return 高度
	 */
	public static int getImgHeight(URL url) {
		InputStream is = null;
		BufferedImage src = null;
		int ret = -1;
		try {
			is = url.openStream();
			src = javax.imageio.ImageIO.read(is);
			ret = src.getHeight(null); // 得到源图高
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 获取图片高度
	 *
	 * @param file 图片文件
	 * @return 高度
	 */
	public static int getImgHeight(File file) {
		InputStream is = null;
		BufferedImage src = null;
		int ret = -1;
		try {
			is = new FileInputStream(file);
			src = javax.imageio.ImageIO.read(is);
			ret = src.getHeight(null); // 得到源图高
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
}