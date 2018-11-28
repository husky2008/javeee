package com.zk;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZkthreadApplicationTests {


	/**
	 *   类的继承结构
	 *                                   <-- i:ScheduledExecutorService <-- c:ScheduledThreadPoolExecutor
	    i:Executor <-- i:ExecutorService <-- i：AbstractExecutorService <-- c:ThreadPoolExecutor
	 *
	 *  1、线程池管理器（ThreadPool）：用于创建并管理线程池，包括 创建线程池，销毁线程池，添加新任务；
	    2、工作线程（PoolWorker）：线程池中线程，在没有任务时处于等待状态，可以循环的执行任务；
	    3、任务接口（Task）：每个任务必须实现的接口，以供工作线程调度任务的执行，它主要规定了任务的入口，任务执行完后的收尾工作，任务的执行状态等；
	    4、任务队列（taskQueue）：用于存放没有处理的任务。提供一种缓冲机制。

	 RejectedExecutionHandler 拒绝任务的策略

	 CallerRunsPolicy: 这个策略显然不想放弃执行任务。但是由于池中已经没有任何资源了，那么就直接使用调用该execute的线程本身来执行。
	 AbortPolicy: 直接抛弃任务,处理程序遭到拒绝将抛出运行时RejectedExecutionException
	 DiscardPolicy:不能执行的任务将被删除,这种策略和AbortPolicy几乎一样，也是丢弃任务，只不过他不抛出异常。
	 DiscardOldestPolicy:该策略就稍微复杂一些，在pool没有关闭的前提下首先丢掉缓存在队列中的最早的任务，然后重新尝试运行该任务。这个策略需要适当小心。
	 设想:如果其他线程都还在运行，那么新来任务踢掉旧任务，缓存在queue中，再来一个任务又会踢掉queue中最老任务。
	 */


	@Before
	public  void createThreadPools(){

	}


	/**
	 * 单个线程的线程池，即线程池中每次只有一个线程工作，单线程串行执行任务
	 */
	@Test
	public void contextLoads() {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		for(int i=0;i<=10;i++){
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());
				}
			});
		}
	}


	/**
	 *  因为默认的maximumPoolSize = Integer.MAX_VALUE 所以会一直创建新的线程
	 *
	 * 可缓存线程池，当线程池大小超过了处理任务所需的线程，那么就会回收部分空闲（一般是60秒无执行）的线程，当有任务来时，又智能的添加新线程来执行
	 * 底层workqueue有 SynchronousQueue 没有数据缓存的阻塞队列。一个插入操作必定对应着一个移除操作(在某次添加元素后必须等待其他线程取走后才能继续添加)
	 * SynchronousQueue的作用jdk中写的很清楚：此策略可以避免在处理可能具有内部依赖性的请求集时出现锁。
	  什么意思？如果你的任务A1，A2有内部关联，A1需要先运行，那么先提交A1，再提交A2，当使用SynchronousQueue我们可以保证，A1必定先被执行，在A1么有被执行前，A2不可能添加入queue中。
	 */
	@Test
	public void cachedPool() throws IOException {
		ThreadPoolExecutor executorService = (ThreadPoolExecutor)Executors.newCachedThreadPool();
		executorService.setCorePoolSize(3);
		//executorService.setMaximumPoolSize(5);

		for(int i=0;i<=10;i++){
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					try {
						TimeUnit.SECONDS.sleep(5);
						System.out.println(Thread.currentThread().getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		System.out.println("活动线程的数量："+executorService.getActiveCount());
		System.in.read();
	}


	/**
	 * 因为底层是通过 LinkedBlockingQueue(无界限队列),所以新来的任务超过maximumPoolSize会一直放到队列中去
	 * 固定数量的线程池，没提交一个任务就是一个线程，直到达到线程池的最大数量，然后后面进入等待队列直到前面的任务完成才继续执行
	 * 空闲线程会一直保留
	 */
	@Test
	public void fiexdPool() throws IOException {

		ThreadPoolExecutor executorService = (ThreadPoolExecutor)Executors.newFixedThreadPool(5);
		executorService.setCorePoolSize(2);
		executorService.setMaximumPoolSize(3);
		executorService.setKeepAliveTime(50000, TimeUnit.MILLISECONDS);
		for(int i=0;i<=10;i++){
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println(Thread.currentThread().getName()+"----进入线程");
						TimeUnit.SECONDS.sleep(4);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"----线程执行完毕");
				}
			});
		}
		System.out.println("活动线程的数量："+executorService.getActiveCount());
		System.in.read();
	}

	/**
	 * 创建一个大小无限的线程池。此线程池支持定时以及周期性执行任务的需求。
	 */
	@Test
	public void scheduledPool() throws IOException {
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
		for(int i=0;i<=10;i++){
			executorService.scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread());
				}
			},4,4,TimeUnit.SECONDS);
		}
		System.in.read();
	}







}
