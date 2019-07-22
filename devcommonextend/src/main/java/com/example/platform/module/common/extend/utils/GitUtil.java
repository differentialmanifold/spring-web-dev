package com.example.platform.module.common.extend.utils;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.errors.*;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class GitUtil {
    private static final Logger logger = LoggerFactory.getLogger(GitUtil.class);

    public GitUtil(String localRepoPath, String remoteRepoPath,
                   String username, String password) {
        init(localRepoPath, remoteRepoPath, username, password);
    }

    private static Git git;
    private static CredentialsProvider cp;
    private static String localRepoPath;

    private void init(String localRepoPath, String remoteRepoPath,
                      String username, String password){
        GitUtil.localRepoPath = localRepoPath;
        File repo = new File(localRepoPath);
        if(!repo.isDirectory() && !repo.exists()) {
            repo.mkdirs();
            git = null;
        }

        if(git == null) {
            String localRepoGitConfig = localRepoPath + "/.git";
            File localRepoGitConfigFile = new File(localRepoGitConfig);
            if(cp == null) {
                cp = new UsernamePasswordCredentialsProvider(username, password);
            }
            boolean exists = localRepoGitConfigFile.exists();
            long startTime = new Date().getTime();
            if(!exists) {//Create Repo
                logger.info("#####Local repo is not exists.");
                File dir = new File(localRepoPath);
                CloneCommand cc = new CloneCommand()
                        .setCredentialsProvider(cp)
                        .setDirectory(dir)
                        .setURI(remoteRepoPath);
                try {
                    git = cc.call();
                } catch (GitAPIException e) {
                    logger.error("#####Create Git Repository error", e);
                }
                long endTime = new Date().getTime();
                logger.info("#####Local repo is inited.(used " + (endTime - startTime) + "ms)");
            }else {//Set Git
                logger.info("#####Local repo is exists.");
                Repository localRepo;
                try {
                    logger.info("#####init Git.");
                    localRepo = new FileRepository(localRepoGitConfig);
                    git = new Git(localRepo);
                    long endTime = new Date().getTime();
                    logger.info("#####init Git over.(used " + (endTime - startTime) + "ms)");
                } catch (IOException e) {
                    logger.error("#####Set Git Repository error", e);
                }

            }
        }
    }

    /**
     * sync remote file to local repo
     */
    public void pull() throws WrongRepositoryStateException, InvalidConfigurationException, InvalidRemoteException, CanceledException, RefNotFoundException, RefNotAdvertisedException, NoHeadException, TransportException, GitAPIException {
        PullCommand pc = git.pull().setCredentialsProvider(cp);
        pc.call();
    }


    public static void main(String[] args) {
        String localRepoPath = "/your/local/path/";
        String remoteRepoPath = "https://gitlab.com/group/project.git";
        String gitlabUsername = "username";
        String gitlabPassword = "password";

        try{
            long startTime = new Date().getTime();
            logger.info("#####Sync Repo start By [autoSync Engine]");

            GitUtil gitUtil = new GitUtil(localRepoPath, remoteRepoPath, gitlabUsername, gitlabPassword);

            gitUtil.pull();

            long endTime = new Date().getTime();
            logger.info("#####git pull success. use (" + (endTime - startTime) + " ms)");
        }catch(Exception e){
            logger.error("#####git pull error", e);
        }
    }




}
