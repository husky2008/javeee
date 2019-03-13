package com.zk.shell;

import ch.ethz.ssh2.*;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * 通过Java命令将文件copy到远程linux中
 *
 * @ClassName SSHDemo
 * @Description TODO
 * @Author zhangkai
 * @Date 2019/1/24 16:29
 **/
public class SSHDemo {

    private Connection connection;

    public static void main(String[] args) throws Exception {
        SSHDemo sshDemo = new SSHDemo();
        String host = "172.18.5.245";
        sshDemo.initSession(host, "root", "111111");
        sshDemo.transferFile("D:\\download\\svn.txt", "/home/download");
    }


    public void initSession(String hostName, String userName, String passwd) throws IOException {
        connection = new Connection(hostName);
        connection.connect();
        boolean authenticateWithPassword = connection.authenticateWithPassword(userName, passwd);
        if (!authenticateWithPassword) {
            throw new RuntimeException("Authentication failed. Please check hostName, userName and passwd");
        }
    }

    /**
     * 远程传输单个文件
     * @param localFile
     * @param remoteTargetDirectory
     * @throws IOException
     */

    public void transferFile(String localFile, String remoteTargetDirectory) throws IOException {
        SCPOutputStream scpOutputStream = null;
        try {
            File file = new File(localFile);
            if (file.isDirectory()) {
                throw new RuntimeException(localFile + "  is not a file");
            }
            String fileName = file.getName();
            execCommand("cd " + remoteTargetDirectory + ";rm " + fileName + "; touch " + fileName);
            SCPClient sCPClient = connection.createSCPClient();
            scpOutputStream = sCPClient.put(fileName, file.length(), remoteTargetDirectory, "7777");
            String content = IOUtils.toString(new FileInputStream(file));
            scpOutputStream.write(content.getBytes(StandardCharsets.UTF_8));
            scpOutputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scpOutputStream != null) {
                scpOutputStream.close();
            }
        }


    }


    public String execCommand(String command) throws IOException {
        Session session = connection.openSession();
        session.execCommand(command, StandardCharsets.UTF_8.toString());
        InputStream streamGobbler = new StreamGobbler(session.getStdout());
        String result = IOUtils.toString(streamGobbler, StandardCharsets.UTF_8);
        session.waitForCondition(ChannelCondition.EXIT_SIGNAL, Long.MAX_VALUE);

        if (session.getExitStatus().intValue() == 0) {
            System.out.println("execCommand: {} success " + command);
            //log.info("execCommand: {} success ", command);
        } else {
            System.out.println("execCommand: {} fail " + command);
            //log.error("execCommand : {} fail", command);
        }

        IOUtils.closeQuietly(streamGobbler);
        session.close();
        return result;
    }

    /**
     * 传输整个目录
     * @param localDirectory
     * @param remoteTargetDirectory
     * @throws IOException
     */
    public void transferDirectory(String localDirectory, String remoteTargetDirectory) throws IOException {
        File dir = new File(localDirectory);
        if (!dir.isDirectory()) {
            throw new RuntimeException(localDirectory + " is not directory");
        }
        String[] files = dir.list();
        for (String file : files) {
            if (file.startsWith(".")) {
                continue;
            }
            String fullName = localDirectory + "/" + file;
            if (new File(fullName).isDirectory()) {
                String rdir = remoteTargetDirectory + "/" + file;
                execCommand("mkdir -p " + remoteTargetDirectory + "/" + file);
                transferDirectory(fullName, rdir);
            } else {
                transferFile(fullName, remoteTargetDirectory);
            }
        }

    }
    public void close() {
        connection.close();
    }
}
