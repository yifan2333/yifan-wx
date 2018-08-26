package com.yifan.wx.threadlocal;

/**
 * <p>Title: 序列Sequence</p>
 * <p>Description: 测试ThreadLocal类。
 * 每个线程所产生的序号虽然都共享同一个SequenceNumber实例，
 * 但它们并没有发生相互干扰的情况，而是各自产生独立的序列号，
 * 这是因为我们通过ThreadLocal为每一个线程提供了单独的副本。
 * </p>
 * @author: wuyifan
 * @date: 2018年05月14日 14:11
 */
public class SequenceNumber {
    // 通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值
    private static final ThreadLocal<Integer> seqNum = ThreadLocal.withInitial(() -> 0);
    /**
     * @Title 获取下一个序列值
     * @Description
     * @return int
     * @author wuyifan
     * @date 2018年5月14日 14:14
     */
    public int getNextNum(){
        seqNum.set(seqNum.get()+1);
        return seqNum.get();
    }

    public static void main(String[] args) {
        SequenceNumber sn = new SequenceNumber();
        // 3个线程共享sn，各自产生序列号
        TestClient t1 = new TestClient(sn);
        TestClient t2 = new TestClient(sn);
        TestClient t3 = new TestClient(sn);
        t1.start();
        t2.start();
        t3.start();
    }

    private static class TestClient extends Thread {

        private SequenceNumber sn;

        TestClient(SequenceNumber sn) {
            this.sn = sn;
        }
        public void run() {
            // 每个线程打出3个序列值
            for (int i = 0; i < 3; i++) {
                System.out.println("thread["+Thread.currentThread().getName()+ "] sn["+sn.getNextNum()+"]");
            }
        }

    }
}

