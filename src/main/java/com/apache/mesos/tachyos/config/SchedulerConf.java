package com.apache.mesos.tachyos.config;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class SchedulerConf {

  private static SchedulerConf instance = null;
  private static Properties props = null;

  public static SchedulerConf getInstance() {
    if (instance == null) {
      instance = new SchedulerConf();
    }
    return instance;
  }

  public boolean usingMesosDns() {
    return Boolean.valueOf(getConf().getProperty("mesos.tachyon.mesosdns", "false"));
  }

  public String getMesosDnsDomain() {
    return getConf().getProperty("mesos.tachyon.mesosdns.domain", "mesos");
  }

  public boolean usingNativeTachyonBinaries() {
    return Boolean.valueOf(getConf().getProperty("mesos.tachyon.native-tachyon-binaries", "false"));
  }

  public String getExecutorPath() {
    return getConf().getProperty("mesos.tachyon.executor.path", "..");
  }

  public String getExecutorHeap() {
    return getConf().getProperty("mesos.tachyon.executor.heap.size", "256");
  }

  public String getJvmOverhead() {
    return getConf().getProperty("mesos.tachyon.jvm.overhead", "1.35");
  }

  public String getTachyonMasterPort() {
    return getConf().getProperty("tachyon.master.port", "19998");
  }

  public String getTachyonWebPort() {
    return getConf().getProperty("tachyon.web.port", "19999");
  }

  public String getTachyonSelectorThreads() {
    return getConf().getProperty("tachyon.master.selector.threads", "3");
  }

  public String getTachyonSelectorQueueSize() {
    return getConf().getProperty("tachyon.master.queue.size.per.selector", "3000");
  }

  public String getTachyonServerThreads() {
    return getConf().getProperty("tachyon.master.server.threads",
        2 * Runtime.getRuntime().availableProcessors() + "");
  }

  public String getMesosMasterPort() {
    return getConf().getProperty("mesos.master.port", "5050");
  }

  public String getJvmOpts() {
    return getConf().getProperty(
        "mesos.tachyon.jvm.opts", ""
            + "-XX:+UseConcMarkSweepGC "
            + "-XX:+CMSClassUnloadingEnabled "
            + "-XX:+UseTLAB "
            + "-XX:+AggressiveOpts "
            + "-XX:+UseCompressedOops "
            + "-XX:+UseFastEmptyMethods "
            + "-XX:+UseFastAccessorMethods "
            + "-Xss256k "
            + "-XX:+AlwaysPreTouch "
            + "-XX:+UseParNewGC "
            + "-Djava.library.path=/usr/lib:/usr/local/lib:lib/native");
  }

  public String getWorkerExecutorCpus() {
    return getConf().getProperty("mesos.tachyon.worker.executor.cpus", "1.0");
  }

  public String getWorkerExecutorMem() {
    return getConf().getProperty("mesos.tachyon.worker.mem", "1024");
  }

  public String getTachyonHome() {
    return getConf().getProperty("tachyon.home", "/home/mesosadm/tachyon-0.6.4");
  }

  public String getFrameworkName() {
    return getConf().getProperty("mesos.tachyon.framework.name", "TachyonMesos");
  }

  public String getFailoverTimeout() {
    return getConf().getProperty("mesos.failover.timeout.sec", "31449600");
  }

  // TODO will be changed in Mesos //for now it is mesosadm
  public String getTachyonUser() {
    return getConf().getProperty("mesos.tachyon.user", "mesosadm");
  }

  // TODO This role needs to be updated.
  public String getTachyonRole() {
    return getConf().getProperty("mesos.tachyon.role", "*");
  }

  // TODO will be changed, do it better with ZK or DNS stuff...
  public String getMesosMasterUri() {
    return getConf().getProperty("mesos.master.uri", "10.100.8.91:5050");
  }

  public String getDataDir() {
    return getConf().getProperty("mesos.tachyon.data.dir", "/tmp/tachyon/data");
  }

  public String getNativeLibrary() {
    return getConf().getProperty("mesos.native.library", "/usr/local/lib/libmesos.so");
  }

  public String getFrameworkMountPath() {
    return getConf().getProperty("mesos.tachyon.framework.mnt.path", "/opt/mesosphere");
  }

  public String getFrameworkHostAddress() {
    String hostAddress = getConf().getProperty("mesos.tachyon.framework.hostaddress");
    if (hostAddress == null) {
      try {
        hostAddress = InetAddress.getLocalHost().getHostAddress();
      } catch (UnknownHostException e) {
        throw new RuntimeException(e);
      }
    }
    return hostAddress;
  }

  public String getReconciliationTimeout() {
    return getConf().getProperty("mesos.reconciliation.timeout.seconds", "30");
  }

  public String getDeadNodeTimeout() {
    return getConf().getProperty("mesos.tachyon.deadnode.timeout.seconds", "90");
  }

  public SchedulerConf() {
    props = System.getProperties();
    props.put("tachyon.home", "/home/mesosadm/tachyon-0.6.4");
    // props.put("java.library.path", "/usr/lib:/usr/local/lib:lib/native");

  }

  public static Properties getConf() {
    return getInstance().props;
  }
}