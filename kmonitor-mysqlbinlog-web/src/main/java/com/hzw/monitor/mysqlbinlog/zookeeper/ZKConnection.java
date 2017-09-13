package com.hzw.monitor.mysqlbinlog.zookeeper;

import java.util.List;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.utils.EnsurePath;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * @author gqliu 2016年1月18日
 *
 */

public class ZKConnection {

	private CuratorFramework client;

	private String taskDir;
	
	public ZKConnection(String zkconnectStr, RetryPolicy retryPolicy,String namespace, String taskDir) {
		client = CuratorFrameworkFactory.builder().namespace(namespace).connectString(zkconnectStr)
				.retryPolicy(retryPolicy).build();
		client.start();

		this.taskDir = taskDir;

		EnsurePath ensurePath = new EnsurePath("/"+namespace + taskDir);
		try {
			ensurePath.ensure(client.getZookeeperClient());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public List<String> getChildren(String path) throws Exception {
		return client.getChildren().forPath(path);
	}
	
	public String create(String path, CreateMode mode) throws Exception {
		return client.create().withMode(mode).forPath(path);
	}

	public Stat setData(String path, String data) throws Exception {
		return client.setData().forPath(path, data.getBytes("utf-8"));
	}
	
	public String getData(String path) throws Exception {
		return new String(client.getData().forPath(path), "utf-8");
	}
	
	public void deletePath(String path) throws Exception {
		client.delete().deletingChildrenIfNeeded().forPath(path);
	}
	
	public boolean exists(String path) throws Exception {
		return client.checkExists().forPath(path) != null;
	}
	
	public void close() {
		client.close();
	}

	public String getTaskDir() {
		return taskDir;
	}
	
	public void setTaskDir(String prefix) {
		this.taskDir = prefix;
	}

}
