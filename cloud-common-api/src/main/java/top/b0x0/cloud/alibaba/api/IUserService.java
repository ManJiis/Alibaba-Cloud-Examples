package top.b0x0.cloud.alibaba.api;

import top.b0x0.cloud.alibaba.common.vo.res.UserRes;

public interface IUserService {

    String sayHello(String name);

    UserRes findUser();
}
