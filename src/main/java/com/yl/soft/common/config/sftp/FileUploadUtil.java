//package com.yl.soft.common.config.sftp;
//
//import ch.ethz.ssh2.Connection;
//import ch.ethz.ssh2.SCPClient;
//import ch.ethz.ssh2.SCPOutputStream;
//import com.jcraft.jsch.*;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//import java.io.*;
//
///**
// * 类说明：文件上传工具类
// * @auther jiangxl
// * @date 2020-8-3 18:36
// */
//@Component
//public class FileUploadUtil {
//
//    @Value("${sftp.url}")
//    private String url;
//
//    @Value("${sftp.password}")
//    private String passWord;
//
//    @Value("${sftp.username}")
//    private String userName;
//
//    /**
//     * 方法说明：上传文件
//     * @param file 文件对象
//     * @param targetPath 上传地址
//     * @param remoteFileName 指定远程文件名称
//     * @return 上传结果
//     * @throws Exception
//     */
//    @Async
//    public ResultEntity uploadFile(File file, String targetPath, String remoteFileName) throws Exception{
//        ScpConnectEntity scpConnectEntity=new ScpConnectEntity();
//        scpConnectEntity.setTargetPath(targetPath);
//        scpConnectEntity.setUrl(url);
//        scpConnectEntity.setPassWord(passWord);
//        scpConnectEntity.setUserName(userName);
//
//        String code = null;
//        String message = null;
//        try {
//            if (file == null || !file.exists()) {
//                throw new IllegalArgumentException("请确保上传文件不为空且存在！");
//            }
//            if(remoteFileName==null || "".equals(remoteFileName.trim())){
//                throw new IllegalArgumentException("远程服务器新建文件名不能为空!");
//            }
//            remoteUploadFile(scpConnectEntity, file, remoteFileName);
//            code = "ok";
//            message = remoteFileName;
//        } catch (IllegalArgumentException e) {
//            code = "Exception";
//            message = e.getMessage();
//        } catch (JSchException e) {
//            code = "Exception";
//            message = e.getMessage();
//        } catch (IOException e) {
//            code = "Exception";
//            message = e.getMessage();
//        } catch (Exception e) {
//            throw e;
//        } catch (Error e) {
//            code = "Error";
//            message = e.getMessage();
//        }
//        return new ResultEntity(code, message, null);
//    }
//
//    /**
//     * 方法说明：上传文件到远程服务器
//     * @param scpConnectEntity 连接对象
//     * @param file 文件对象
//     * @param remoteFileName 远程文件名称
//     * @throws JSchException
//     * @throws IOException
//     */
//    private void remoteUploadFile(ScpConnectEntity scpConnectEntity, File file,
//                                  String remoteFileName) throws JSchException, IOException {
//
//        Connection connection = null;
//        ch.ethz.ssh2.Session session = null;
//        SCPOutputStream scpo = null;
//        FileInputStream fis = null;
//
//        try {
//            createDir(scpConnectEntity);
//        }catch (JSchException e) {
//            throw e;
//        }
//
//        try {
//            connection = new Connection(scpConnectEntity.getUrl());
//            connection.connect();
//
//            if(!connection.authenticateWithPassword(scpConnectEntity.getUserName(),scpConnectEntity.getPassWord())){
//                throw new RuntimeException("SSH连接服务器失败");
//            }
//            session = connection.openSession();
//
//            SCPClient scpClient = connection.createSCPClient();
//
//            scpo = scpClient.put(remoteFileName, file.length(), scpConnectEntity.getTargetPath(), "0666");
//            fis = new FileInputStream(file);
//
//            byte[] buf = new byte[1024];
//            int hasMore = fis.read(buf);
//
//            while(hasMore != -1){
//                scpo.write(buf);
//                hasMore = fis.read(buf);
//            }
//        } catch (IOException e) {
//            throw new IOException("SSH上传文件至服务器出错"+e.getMessage());
//        }finally {
//            if(null != fis){
//                try {
//                    fis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if(null != scpo){
//                try {
//                    scpo.flush();
////                    scpo.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if(null != session){
//                session.close();
//            }
//            if(null != connection){
//                connection.close();
//            }
//        }
//    }
//
//    /**
//     * 方法说明：连接远程服务器
//     * @param scpConnectEntity 连接信息
//     * @return ture连接成功，false连接失败
//     * @throws JSchException
//     */
//    private boolean createDir(ScpConnectEntity scpConnectEntity ) throws JSchException {
//
//        JSch jsch = new JSch();
//        com.jcraft.jsch.Session sshSession = null;
//        Channel channel= null;
//        try {
//            sshSession = jsch.getSession(scpConnectEntity.getUserName(), scpConnectEntity.getUrl(), 22);
//            sshSession.setPassword(scpConnectEntity.getPassWord());
//            sshSession.setConfig("StrictHostKeyChecking", "no");
//            sshSession.connect();
//            channel = sshSession.openChannel("sftp");
//            channel.connect();
//        } catch (JSchException e) {
//            e.printStackTrace();
//            throw new JSchException("SFTP连接服务器失败"+e.getMessage());
//        }
//        ChannelSftp channelSftp=(ChannelSftp) channel;
//        if (isDirExist(scpConnectEntity.getTargetPath(),channelSftp)) {
//            channel.disconnect();
//            channelSftp.disconnect();
//            sshSession.disconnect();
//            return true;
//        }else {
//            String pathArry[] = scpConnectEntity.getTargetPath().split("/");
//            StringBuffer filePath=new StringBuffer("/");
//            for (String path : pathArry) {
//                if (path.equals("")) {
//                    continue;
//                }
//                filePath.append(path + "/");
//                try {
//                    if (isDirExist(filePath.toString(),channelSftp)) {
//                        channelSftp.cd(filePath.toString());
//                    } else {
//                        // 建立目录
//                        channelSftp.mkdir(filePath.toString());
//                        // 进入并设置为当前目录
//                        channelSftp.cd(filePath.toString());
//                    }
//                } catch (SftpException e) {
//                    e.printStackTrace();
//                    throw new JSchException("SFTP无法正常操作服务器"+e.getMessage());
//                }
//            }
//        }
//        channel.disconnect();
//        channelSftp.disconnect();
//        sshSession.disconnect();
//        return true;
//    }
//
//    /**
//     * 方法说明：判断文件夹是否存在
//     * @param directory 文件夹名称
//     * @param channelSftp
//     * @return 通道
//     */
//    private boolean isDirExist(String directory,ChannelSftp channelSftp) {
//        boolean isDirExistFlag = false;
//        try {
//            SftpATTRS sftpATTRS = channelSftp.lstat(directory);
//            isDirExistFlag = true;
//            return sftpATTRS.isDir();
//        } catch (Exception e) {
//            if (e.getMessage().toLowerCase().equals("no such file")) {
//                isDirExistFlag = false;
//            }
//        }
//        return isDirExistFlag;
//    }
//
//    public File MultipartFileToFile(MultipartFile mFile){
//        File f = null;
//        try {
//            InputStream ins = mFile.getInputStream();
//            f = new File(mFile.getOriginalFilename());
//            try {
//                OutputStream os = new FileOutputStream(f);
//                int bytesRead = 0;
//                byte[] buffer = new byte[8192];
//                while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
//                    os.write(buffer, 0, bytesRead);
//                }
//                os.close();
//                ins.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return f;
//        }catch (Exception ex){
//            ex.printStackTrace();
//            return null;
//        }finally {
////            File del = new File(f.toURI());
////            del.delete();
//        }
//    }
//}
