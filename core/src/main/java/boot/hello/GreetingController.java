package boot.hello;


import boot.model.User1;
import boot.model.User2;
import boot.service.GreetingService;
import boot.service.User1Service;
import boot.service.User2Service;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Resource
    GreetingService greetingService;


    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        //通过这两个方法我们证明了在外围方法未开启事务的情况下Propagation.REQUIRED修饰的内部方法会新开启自己的事务，且开启的事务相互独立，互不干扰。
        /**
         * 外围方法未开启事务，插入“张三”、“李四”方法在自己的事务中独立运行，外围方法异常不影响内部插入“张三”、“李四”方法独立的事务。
         */
        //greetingService.notransaction_exception_required_required();

        /**
         * 外围方法没有事务，插入“张三”、“李四”方法都在自己的事务中独立运行,所以插入“李四”方法抛出异常只会回滚插入“李四”方法，插入“张三”方法不受影响。
         */
        //greetingService.notransaction_required_required_exception();


        /**
         * 试验结果我们证明在外围方法开启事务的情况下Propagation.REQUIRED修饰的内部方法会加入到外围方法的事务中，所有Propagation.REQUIRED修饰的内部方法和外围方法均属于同一事务，只要一个方法回滚，整个事务均回滚。
         */
        //greetingService.notransaction_exception_required_required_new();
        //greetingService.notransaction_exception_required_required_new1();
        //greetingService.transaction_required_required_exception_try();


        /**
         * 通过这两个方法我们证明了在外围方法未开启事务的情况下Propagation.REQUIRES_NEW修饰的内部方法会新开启自己的事务，且开启的事务相互独立，互不干扰。
         */
        //greetingService.notransaction_exception_requiresNew_requiresNew();
        //greetingService.notransaction_requiresNew_requiresNew_exception();

        /**
         * 在外围方法开启事务的情况下Propagation.REQUIRES_NEW修饰的内部方法依然会单独开启独立事务，且与外部方法事务也独立，内部方法之间、内部方法和外部方法事务均相互独立，互不干扰
         */
        //greetingService.transaction_exception_required_requiresNew_requiresNew();
        //greetingService.transaction_required_requiresNew_requiresNew_exception();
        //greetingService.transaction_required_requiresNew_requiresNew_exception_try();


        /**
         * 通过这两个方法我们证明了在外围方法未开启事务的情况下Propagation.NESTED和Propagation.REQUIRED作用相同，修饰的内部方法都会新开启自己的事务，且开启的事务相互独立，互不干扰。
         */
        //greetingService.notransaction_exception_nested_nested();
        //greetingService.notransaction_nested_nested_exception();

        /**
         * 以上试验结果我们证明在外围方法开启事务的情况下Propagation.NESTED修饰的内部方法属于外部事务的子事务，外围主事务回滚，子事务一定回滚，而内部子事务可以单独回滚而不影响外围主事务和其他子事务
         */
        //greetingService.transaction_exception_nested_nested();
        //greetingService.transaction_nested_nested_exception();
        //greetingService.transaction_nested_nested_exception_try();
        return null;
    }


}
