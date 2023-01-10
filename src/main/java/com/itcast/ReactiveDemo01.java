package com.itcast;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * Description ==> TODO
 * BelongsProject ==> spring_webflux_basic
 * BelongsPackage ==> com.itcast
 * Version ==> 1.0
 * CreateTime ==> 2023-01-10 14:34:41
 * Author ==> _02雪乃赤瞳楪祈校条祭_艾米丽可锦木千束木更七草荠_制作委员会_start
 */
public class ReactiveDemo01 {
    public static void main(String[] args) {
//      发布者
        SubmissionPublisher publisher = new SubmissionPublisher<>();
//      订阅者
        Flow.Subscriber subscriber = new Flow.Subscriber() {
            Flow.Subscription fsb;
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println("01开始订阅...");
                this.fsb = subscription;
                fsb.request(1);
            }

            @Override
            public void onNext(Object item) {
                System.out.println("02开始数据调用...");
                fsb.request(10);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("03发生错误...");
            }

            @Override
            public void onComplete() {
                System.out.println("04任务完成...");
            }
        };
//      发布者订阅订阅者
        publisher.subscribe(subscriber);
//      信息提交
        publisher.submit("Hello!reactive stream...");
//      阻塞
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
